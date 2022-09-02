package com.lyz.security.api.open.vo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 16:28
 */
@Getter
@Setter
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = 477985923412638468L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户真实名称")
    private String realName;

    @ApiModelProperty(value = "用户昵称")
    private String nickName;

    @ApiModelProperty(value = "用户手机号码")
    private String mobile;

    @ApiModelProperty(value = "用户邮箱地址")
    private String email;

    @ApiModelProperty(value = "用户加密盐")
    private String salt;

    @ApiModelProperty(value = "用户注册时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registryTime;
}
