package sys.security;


import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sys.entity.WechatRole;
import sys.entity.WechatUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by xiaoq on 2017-06-25.
 */
public class WechatSecurityUser extends WechatUser implements UserDetails {
    public WechatSecurityUser() {
        super();
    }

    public WechatSecurityUser(WechatUser wechatUser) {
        BeanUtils.copyProperties(wechatUser, this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        List<WechatRole> roles = this.getRoles();
        for (WechatRole role:roles) {
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return auths;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return super.getOpenId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
