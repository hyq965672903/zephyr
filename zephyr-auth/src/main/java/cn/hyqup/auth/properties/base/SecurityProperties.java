
package cn.hyqup.auth.properties.base;


import cn.hyqup.auth.properties.OAuth2Properties;
import cn.hyqup.auth.properties.ValidateCodeProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/23
 * @description:
 */
@Data
@ConfigurationProperties(prefix = "zephyr.security")
public class SecurityProperties {

    private ValidateCodeProperties code = new ValidateCodeProperties();

    private OAuth2Properties oauth2 = new OAuth2Properties();

}

