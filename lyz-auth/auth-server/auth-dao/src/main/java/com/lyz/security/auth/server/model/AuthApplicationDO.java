package com.lyz.security.auth.server.model;

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
 * @date 2022/9/2 14:38
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("auth_application")
public class AuthApplicationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -290413527244490532L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String applicationName;

    private String dubboGroup;

    private Integer dubboTimeout;

    private String dubboVersion;
}
