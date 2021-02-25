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
public class ImageCodeProperties {

    private int width = 67;
    private int height = 23;
    private int length = 4;
    private int expireIn = 60;

    private String url;

}
