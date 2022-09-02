package com.lyz.security.api.open.controller.auth;

import com.lyz.security.api.open.dto.auth.LoginDTO;
import com.lyz.security.common.controller.config.HttpServletContext;
import com.lyz.security.common.controller.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @ApiOperation("登陆")
    @PostMapping("/login")
    public Result<Boolean> login(@Validated({LoginDTO.Login.class}) @RequestBody LoginDTO loginDTO) {
        HttpServletRequest request = HttpServletContext.getRequest();
        String ip = HttpServletContext.getIpAddress(request);

        return Result.success(Boolean.TRUE);
    }
}
