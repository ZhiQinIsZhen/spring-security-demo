package com.lyz.security.service.user.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lyz.security.common.dao.model.BaseDO;
import lombok.*;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_login_log")
public class UserLoginLogDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 3070437801653890936L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long userId;

    /**
     * 登陆方式：1:手机;2:邮箱
     */
    private Integer type;

    private Integer device;

    private Date loginTime;

    private String loginIp;
}
