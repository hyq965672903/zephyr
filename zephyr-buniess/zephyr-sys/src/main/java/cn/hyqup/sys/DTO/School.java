package cn.hyqup.sys.DTO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/6
 * @description:
 */
@Slf4j
@Data
public class School {
    //    @ValidatedPlus
    @NotBlank(message = "不能为空")
    private String name;

    private Integer studentNum;
}
