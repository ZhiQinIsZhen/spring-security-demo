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
 * @date 2022/9/1 14:07
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_authority")
public class UserAuthorityDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 3056945666918696574L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    private String authority;

    private String applicationName;
}
