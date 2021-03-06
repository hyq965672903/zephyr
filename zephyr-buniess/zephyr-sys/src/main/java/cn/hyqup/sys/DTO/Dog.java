package cn.hyqup.sys.DTO;

import cn.hyqup.common.web.validator.annations.ParamCheck;
import cn.hyqup.common.web.validator.enums.CheckType;
import cn.hyqup.sys.validate.UserNameCheck;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
    @ParamCheck(checkType = CheckType.Custom,message = "自定义校验异常",clazz = UserNameCheck.class)
    private String name;
    private String age;
    //    @Check(type = CheckType.MOBILE, message = "手机后不准确")
    @NotEmpty(message = "不能为空")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    private String phone;
    @Valid
    private Eye eye;
}
