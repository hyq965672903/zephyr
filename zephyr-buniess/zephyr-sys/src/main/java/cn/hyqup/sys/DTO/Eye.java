package cn.hyqup.sys.DTO;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/6
 * @description:
 */
@Data
public class Eye {
    private String color;
    private boolean big;
    @Min(2)
    private int length;

}
