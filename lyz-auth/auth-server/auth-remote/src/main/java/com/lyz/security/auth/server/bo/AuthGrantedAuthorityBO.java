package com.lyz.security.auth.server.bo;

import lombok.*;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/7 15:30
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthGrantedAuthorityBO implements Serializable {
    private static final long serialVersionUID = -3634017060827033090L;

    private String applicationName;

    private String authority;
}
