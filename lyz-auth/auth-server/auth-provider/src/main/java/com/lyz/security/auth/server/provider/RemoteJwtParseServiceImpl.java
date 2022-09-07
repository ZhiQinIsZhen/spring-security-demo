package com.lyz.security.auth.server.provider;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.lyz.security.auth.server.bo.AuthUser;
import com.lyz.security.auth.server.exception.AuthExceptionCodeEnum;
import com.lyz.security.auth.server.exception.RemoteAuthServiceException;
import com.lyz.security.auth.server.model.AuthJwtDO;
import com.lyz.security.auth.server.remote.RemoteAuthService;
import com.lyz.security.auth.server.remote.RemoteJwtParseService;
import com.lyz.security.auth.server.service.IAuthJwtService;
import com.lyz.security.common.core.constant.CommonCoreConstant;
import com.lyz.security.common.util.DateUtil;
import com.lyz.security.common.util.JsonMapperUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.security.jwt.JwtHelper;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 14:38
 */
@Slf4j
@DubboService
public class RemoteJwtParseServiceImpl implements RemoteJwtParseService {

    private final static String CLAIM_LOGIN_TYPE = "loginType";
    private final static String CLAIM_DEVICE = "device";
    private final static String CLAIM_USER_ID = "userId";
    private final static String CLAIM_REAL_NAME = "realName";
    private final static String CLAIM_NICK_NAME = "nickName";
    private final static String CLAIM_APPLICATION_NAME = "applicationName";

    @Resource
    private IAuthJwtService authJwtService;
    @Resource
    private RemoteAuthService remoteAuthService;

    /**
     * 解析token
     *
     * @param token
     * @param applicationName
     * @return
     */
    @Override
    public AuthUser parseToken(final String token, final String applicationName) {
        AuthJwtDO authJwtDO = authJwtService.getOne(Wrappers.lambdaQuery(AuthJwtDO.builder().applicationName(applicationName).build()));
        if (Objects.isNull(authJwtDO)) {
            log.warn("解析token失败, 没有找到该应用下jwt配置信息，applicationName：{}", applicationName);
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.AUTHORIZATION_FAIL);
        }
        if (StringUtils.isBlank(token) || !token.startsWith(authJwtDO.getJwtPrefix())) {
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.AUTHORIZATION_FAIL);
        }
        final String authToken = token.substring(authJwtDO.getJwtPrefix().length()).trim();
        Claims unSignClaims = parseClaimsJws(authToken);
        AuthUser authUser = remoteAuthService.loadByUsername(
                Joiner.on(CommonCoreConstant.DEFAULT_JOINER).join(applicationName, unSignClaims.getSubject()),
                unSignClaims.get(CLAIM_DEVICE, Integer.class)
        );
        if (Objects.isNull(authUser)) {
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.AUTHORIZATION_FAIL);
        }
        Claims claims = parseClaimsJws(authToken, Joiner.on(CommonCoreConstant.DEFAULT_JOINER).join(authJwtDO.getSigningKey(), authUser.getSalt()));
        if (authJwtDO.getOneOnline() && Objects.nonNull(authUser.getCheckTime()) && claims.getNotBefore().compareTo(authUser.getCheckTime()) < 0) {
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.OTHERS_LOGIN);
        }
        if (!applicationName.equals(claims.get(CLAIM_APPLICATION_NAME, String.class))) {
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.AUTHORIZATION_FAIL);
        }
        if (DateUtil.currentDate().compareTo(claims.getExpiration()) > 0) {
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.AUTHORIZATION_TIMEOUT);
        }
        return AuthUser.builder()
                .username(claims.getSubject())
                .password(StringUtils.EMPTY)
                .loginType(claims.get(CLAIM_LOGIN_TYPE, Integer.class))
                .device(claims.get(CLAIM_DEVICE, Integer.class))
                .userId(claims.get(CLAIM_USER_ID, Long.class))
                .realName(claims.get(CLAIM_REAL_NAME, String.class))
                .nickName(claims.get(CLAIM_NICK_NAME, String.class))
                .checkTime(claims.getNotBefore())
                .roleIds(Lists.newArrayList())
                .token(authToken)
                .expiration(claims.getExpiration().getTime())
                .applicationName(applicationName)
                .build();
    }

    /**
     * 生成token
     *
     * @param authUser
     * @return
     */
    @Override
    public String generateToken(final AuthUser authUser) {
        if (StringUtils.isBlank(authUser.getApplicationName())) {
            log.warn("创建token失败，原因 : applicationName is blank");
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.LOGIN_ERROR);
        }
        AuthJwtDO authJwtDO = authJwtService.getOne(Wrappers.lambdaQuery(AuthJwtDO.builder().applicationName(authUser.getApplicationName()).build()));
        if (Objects.isNull(authJwtDO)) {
            log.warn("生成token失败, 没有找到该应用下jwt配置信息，applicationName : {}", authUser.getApplicationName());
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.LOGIN_ERROR);
        }
        Claims claims = new DefaultClaims();
        claims.put(CLAIM_USER_ID, authUser.getUserId());
        claims.put(CLAIM_LOGIN_TYPE, authUser.getLoginType());
        claims.put(CLAIM_DEVICE, authUser.getDevice());
        claims.put(CLAIM_APPLICATION_NAME, authUser.getApplicationName());
        claims.put(CLAIM_REAL_NAME, authUser.getRealName());
        claims.put(CLAIM_NICK_NAME, authUser.getNickName());
        claims.setSubject(authUser.getUsername());
        claims.setExpiration(new Date(System.currentTimeMillis() + authJwtDO.getExpiration() * 1000));
        claims.setNotBefore(authUser.getCheckTime());
        return Jwts.builder()
                .setClaims(claims)
                .signWith(
                        SignatureAlgorithm.forName(authJwtDO.getSignatureAlgorithm()),
                        Joiner.on(CommonCoreConstant.DEFAULT_JOINER).join(authJwtDO.getSigningKey(), authUser.getSalt())
                )
                .compact();
    }

    /**
     * 获取失效时间
     *
     * @param token
     * @return
     */
    @Override
    public Long getExpiration(String token) {
        return parseClaimsJws(token).getExpiration().getTime();
    }

    /**
     * 解析token
     *
     * @param token
     * @param signingKey
     * @return
     */
    private Claims parseClaimsJws(final String token, final String signingKey) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new RemoteAuthServiceException(AuthExceptionCodeEnum.AUTHORIZATION_FAIL);
        }
        return claims;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     */
    private Claims parseClaimsJws(final String token) {
        return JsonMapperUtil.readValue(JwtHelper.decode(token).getClaims(), DefaultClaims.class);
    }
}
