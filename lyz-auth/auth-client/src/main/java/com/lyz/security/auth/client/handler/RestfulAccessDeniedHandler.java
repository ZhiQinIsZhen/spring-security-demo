package com.lyz.security.auth.client.handler;

import com.google.common.base.Charsets;
import com.lyz.security.auth.server.exception.AuthExceptionCodeEnum;
import com.lyz.security.common.controller.result.Result;
import com.lyz.security.common.util.JsonMapperUtil;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/5 13:32
 */
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setCharacterEncoding(String.valueOf(Charsets.UTF_8));
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().println(JsonMapperUtil.toJSONString(Result.error(AuthExceptionCodeEnum.NO_RIGHT)));
        response.getWriter().flush();
    }
}
