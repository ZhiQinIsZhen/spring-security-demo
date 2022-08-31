package com.lyz.security.common.remote.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/8/31 16:49
 */
@Getter
@Setter
public class PageBO implements Serializable {
    private static final long serialVersionUID = -4593438488153493420L;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
