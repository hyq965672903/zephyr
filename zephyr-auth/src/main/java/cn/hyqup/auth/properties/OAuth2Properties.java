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
public class OAuth2Properties {

    private String jwtSigningKey = "zephyr";

    private OAuth2ClientProperties[] clients = {};


}
