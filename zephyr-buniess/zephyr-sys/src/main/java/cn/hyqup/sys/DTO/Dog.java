package cn.hyqup.sys.DTO;

import cn.hyqup.common.web.validator.Check;
import cn.hyqup.common.web.validator.CheckType;
import lombok.Data;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/6
 * @description:
 */
@Data
public class Dog {
    private String name;
    private String age;
    @Check(type = CheckType.MOBILE, message = "手机后不准确")
    private String phone;
}
