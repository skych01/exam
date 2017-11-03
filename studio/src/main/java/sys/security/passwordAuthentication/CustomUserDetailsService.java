package sys.security.passwordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import sys.entity.DUser;
import sys.repository.DUserRepository;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DUserRepository dUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DUser dUser= dUserRepository.findByUsername(username);
        if (dUser == null) {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
        // SecurityUser实现UserDetails并将SysUser的name映射为username
        return new SecurityUser(dUser);
    }
}
