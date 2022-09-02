package com.lyz.security.service.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyz.security.common.dao.model.BaseDO;
import lombok.*;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 14:19
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_auth_mobile")
public class UserAuthMobileDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 5417331084800908347L;

    @TableId(type = IdType.INPUT)
    private Long userId;

    private String mobile;

    private String password;
}
