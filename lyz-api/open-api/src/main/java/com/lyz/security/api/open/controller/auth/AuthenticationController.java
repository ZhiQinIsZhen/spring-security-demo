package com.lyz.security.api.open.controller.auth;

import com.lyz.security.api.open.dto.auth.LoginDTO;
import com.lyz.security.api.open.dto.auth.UserRegisterDTO;
import com.lyz.security.api.open.vo.auth.AuthLoginVO;
import com.lyz.security.auth.client.annotation.Anonymous;
import com.lyz.security.auth.client.context.AuthContext;
import com.lyz.security.auth.server.bo.AuthUserLoginBO;
import com.lyz.security.auth.server.bo.AuthUserRegisterBO;
import com.lyz.security.common.controller.result.Result;
import com.lyz.security.common.core.util.CommonCloneUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return Result.success(
                AuthContext.AuthService.registry(CommonCloneUtil.objectClone(userRegisterDTO, AuthUserRegisterBO.class))
        );
    }

    @Anonymous
    @ApiOperation("登陆")
    @PostMapping("/login")
    public Result<AuthLoginVO> login(@Validated({LoginDTO.Login.class}) @RequestBody LoginDTO loginDTO) {
        return Result.success(
                CommonCloneUtil.objectClone(
                        AuthContext.AuthService.login(CommonCloneUtil.objectClone(loginDTO, AuthUserLoginBO.class)),
                        AuthLoginVO.class
                )
        );
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    public Result<Boolean> logout() {
        return Result.success(AuthContext.AuthService.logout());
    }
}
