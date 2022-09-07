package com.lyz.security.auth.client.user;

import com.google.common.base.Joiner;
import com.lyz.security.common.core.constant.CommonCoreConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/7 15:23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthGrantedAuthority implements GrantedAuthority {

    private String applicationName;

    private String authority;

    @Override
    public String getAuthority() {
        return Joiner.on(CommonCoreConstant.DEFAULT_JOINER).join(applicationName, authority);
    }
}
