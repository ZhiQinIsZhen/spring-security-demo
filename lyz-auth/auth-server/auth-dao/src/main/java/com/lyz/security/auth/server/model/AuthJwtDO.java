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
@TableName("auth_jwt")
public class AuthJwtDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -290413527244490532L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 应用名
     */
    private String applicationName;

    /**
     * jwt前缀
     */
    private String jwtPrefix;

    /**
     * 签名key
     */
    private String signingKey;

    /**
     * 是否权限控制(0:没有;1:有)
     */
    private Boolean isAuthority;

    /**
     * token失效时间
     */
    private Long expiration;

    /**
     * 签名算法
     */
    private String signatureAlgorithm;

    /**
     * 是否同设备一个在线(0:没有限制;1:同设备只有一个)
     */
    private Boolean oneOnline;
}
