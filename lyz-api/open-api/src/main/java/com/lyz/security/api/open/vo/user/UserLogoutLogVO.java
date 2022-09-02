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
 * @date 2022/9/1 13:47
 */
@Getter
@Setter
public class UserLogoutLogVO implements Serializable {
    private static final long serialVersionUID = 3070437801653890936L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "登陆方式(1:手机;2:邮箱)")
    private Integer type;

    @ApiModelProperty(value = "登陆设备(1移动端:;2:网页端)")
    private Integer device;

    @ApiModelProperty(value = "登陆时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logoutTime;

    private String logoutIp;
}
