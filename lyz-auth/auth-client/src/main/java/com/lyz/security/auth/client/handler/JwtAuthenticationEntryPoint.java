package com.lyz.security.auth.client.handler;

import com.lyz.security.auth.server.exception.AuthExceptionCodeEnum;
import com.lyz.security.common.controller.result.Result;
import com.lyz.security.common.util.JsonMapperUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 13:39
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = 3634556457230918609L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JsonMapperUtil.toJSONString(Result.error(AuthExceptionCodeEnum.AUTHORIZATION_FAIL)));
    }
}
