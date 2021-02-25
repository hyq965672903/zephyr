package cn.hyqup.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * Copyright © 2021灼华. All rights reserved.
 *
 * @author create by hyq
 * @version 1.0
 * @date 2021/2/25
 * @description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAppUser implements UserDetails {
    private String userName;
    private String nickName;
    private String mobile;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    /**
     * 账户是否过期
     *
     * @return true 未过期
     * false 过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否锁定
     *
     * @return true 未锁定
     * false 锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 凭证是否过期
     *
     * @return true  未过期
     * false 过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否启用
     *
     * @return true  启用
     * false 禁用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
