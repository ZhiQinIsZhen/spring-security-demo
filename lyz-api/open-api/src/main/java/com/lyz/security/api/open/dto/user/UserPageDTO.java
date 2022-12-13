package com.lyz.security.api.open.dto.user;

import com.lyz.security.common.controller.dto.BasePageDTO;
import lombok.Getter;
import lombok.Setter;

/**
 * 注释:
 *
 * @author liyangzhen
 * @version 1.0.0
 * @date 2022/12/13 13:43
 */
@Getter
@Setter
public class UserPageDTO extends BasePageDTO {

    private Long userId;
}
