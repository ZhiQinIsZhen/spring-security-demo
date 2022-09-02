package com.lyz.security.common.controller.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/9/1 17:57
 */
@Getter
@Setter
public class BasePageDTO implements Serializable {
    private static final long serialVersionUID = 2196813863945998003L;

    @ApiModelProperty(value = "页码")
    private Long pageNum = 1L;

    @ApiModelProperty(value = "每页数量")
    private Long pageSize = 10L;
}
