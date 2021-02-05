package cn.hyqup.sys.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/5
 * @description:
 */
@Builder
@Data
public class UserVO {
    private String userName;
    private String age;
    private List<String> girlFriends;
}
