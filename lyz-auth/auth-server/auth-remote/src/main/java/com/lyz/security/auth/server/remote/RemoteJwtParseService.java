package com.lyz.security.auth.server.remote;

import com.lyz.security.auth.server.bo.AuthUser;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 14:37
 */
public interface RemoteJwtParseService {

    /**
     * 解析token
     *
     * @param token
     * @param applicationName
     * @return
     */
    AuthUser parseToken(final String token, final String applicationName);

    /**
     * 生成token
     *
     * @param authUser
     * @return
     */
    String generateToken(final AuthUser authUser);

    /**
     * 获取失效时间
     *
     * @param token
     * @return
     */
    Long getExpiration(final String token);
}
