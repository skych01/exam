package sys.security.passwordAuthentication;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import sys.entity.DRole;
import sys.entity.DUser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SecurityUser extends DUser implements UserDetails {

    public SecurityUser(DUser dUser) {
        BeanUtils.copyProperties(dUser, this);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        List<DRole> roles = this.getRoles();
        for (DRole role:roles) {
            auths.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return auths;
    }




    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
