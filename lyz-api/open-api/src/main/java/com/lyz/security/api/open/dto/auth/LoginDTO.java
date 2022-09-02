package com.lyz.security.api.open.dto.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 19:35
 */
@Getter
@Setter
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = -4549684973189435107L;

    @ApiModelProperty(value = "登录名", example = "159887859", required = true)
    @NotBlank(message = "登录名不能为空", groups = {Login.class})
    private String loginName;

    @ApiModelProperty(value = "密码，8-20位数字或字母组成", example = "123456789", required = true)
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$", groups = {Login.class}, message = "请输入8到20位数字和字母组合")
    private String loginPwd;

    public interface Login {}
}
