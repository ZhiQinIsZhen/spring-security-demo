package com.lyz.security.api.open.controller.auth;

import com.lyz.security.api.open.dto.auth.LoginDTO;
import com.lyz.security.api.open.dto.auth.UserRegisterDTO;
import com.lyz.security.auth.client.AuthUser;
import com.lyz.security.auth.client.annotation.Anonymous;
import com.lyz.security.auth.client.context.AuthContext;
import com.lyz.security.auth.client.context.DeviceContext;
import com.lyz.security.auth.server.constant.Device;
import com.lyz.security.common.controller.result.Result;
import com.lyz.security.common.core.util.HttpServletContext;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 注释:鉴权网关
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 19:34
 */
@Api(tags = "用户鉴权")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Anonymous
    @ApiOperation("注册")
    @PostMapping("/register")
    public Result<Boolean> register(@Validated({UserRegisterDTO.Register.class}) @RequestBody UserRegisterDTO userRegisterDTO) {
        AuthUser authUser = AuthContext.getAuthUser();
        if (Objects.isNull(authUser)) {
            return Result.success(Boolean.FALSE);
        }
        Device device = DeviceContext.getDevice(HttpServletContext.getRequest());

        return Result.success(Boolean.TRUE);
    }

    @Anonymous
    @ApiOperation("登陆")
    @PostMapping("/login")
    public Result<Boolean> login(@Validated({LoginDTO.Login.class}) @RequestBody LoginDTO loginDTO) {
        HttpServletRequest request = HttpServletContext.getRequest();
        String ip = HttpServletContext.getIpAddress(request);

        return Result.success(Boolean.TRUE);
    }

    @Anonymous
    @ApiOperation("登出")
    @PostMapping("/logout")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    public Result<Boolean> logout() {
        return Result.success(AuthContext.logout());
    }
}
