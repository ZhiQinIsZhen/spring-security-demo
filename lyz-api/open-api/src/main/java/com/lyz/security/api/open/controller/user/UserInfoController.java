package com.lyz.security.api.open.controller.user;

import com.lyz.security.api.open.vo.user.UserInfoVO;
import com.lyz.security.api.open.vo.user.UserLoginLogVO;
import com.lyz.security.common.controller.dto.BasePageDTO;
import com.lyz.security.common.controller.result.PageResult;
import com.lyz.security.common.controller.result.Result;
import com.lyz.security.common.core.util.CommonCloneUtil;
import com.lyz.security.common.remote.page.BasePageBO;
import com.lyz.security.service.user.remote.RemoteUserInfoService;
import com.lyz.security.service.user.remote.RemoteUserLoginLogService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 17:37
 */
@Api(tags = "用户信息")
@ApiResponses(value = {
        @ApiResponse(code = 0, message = "成功"),
        @ApiResponse(code = 1, message = "失败")
})
@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @DubboReference
    private RemoteUserInfoService remoteUserInfoService;
    @DubboReference
    private RemoteUserLoginLogService remoteUserLoginLogService;

    @ApiOperation("根据用户ID查询用户信息")
    @GetMapping("/userId")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    public Result<UserInfoVO> userId(@RequestParam("userId") Long userId) {
        return Result.success(CommonCloneUtil.objectClone(remoteUserInfoService.getByUserId(userId), UserInfoVO.class));
    }

    @ApiOperation("根据用户ID查询用户登陆日志")
    @GetMapping("/page/userLoginLog")
    @ApiImplicitParam(name = "Authorization", value = "认证token", required = true, dataType = "String",
            paramType = "header", defaultValue = "Bearer ")
    public PageResult<UserLoginLogVO> userLoginLog(@RequestParam("userId") Long userId, BasePageDTO pageDTO) {
        return PageResult.success(
                CommonCloneUtil.remotePageClone(
                        remoteUserLoginLogService.pageByUserId(userId, CommonCloneUtil.objectClone(pageDTO, BasePageBO.class)),
                        UserLoginLogVO.class
                )
        );
    }
}
