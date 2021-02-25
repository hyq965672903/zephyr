
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
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 60;

    private String url;


}
