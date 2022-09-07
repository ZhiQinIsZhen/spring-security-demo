package com.lyz.security.api.open.vo.auth;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/6 9:46
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginVO extends AuthUserInfoVO {
    private static final long serialVersionUID = -3975986868462563739L;

    @ApiModelProperty(value = "鉴权token")
    private String token;

    @ApiModelProperty(value = "Token失效时间戳(ms)")
    private Long expiration;
}
