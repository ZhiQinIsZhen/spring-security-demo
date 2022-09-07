package com.lyz.security.api.open.vo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/6 9:43
 */
@Getter
@Setter
public class AuthUserInfoVO implements Serializable {
    private static final long serialVersionUID = 5914758395624007943L;

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

    @ApiModelProperty(value = "用户角色")
    private List<Integer> roleIds;
}
