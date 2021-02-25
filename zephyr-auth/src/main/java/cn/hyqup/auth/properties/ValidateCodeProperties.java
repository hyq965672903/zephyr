package cn.hyqup.auth.properties;

import lombok.Data;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/23
 * @description:
 */
@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image=new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}
