package sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sys.entity.WechatUser;
import sys.repository.WechatUserRepository;
import sys.security.WechatSecurityUser;

import java.util.Collection;

@Service
public class WecharService  implements UserDetailsService {

   private WechatUserRepository wechatUserRepository;

   @Autowired
    public void setWechatUserRepository(WechatUserRepository wechatUserRepository) {
        this.wechatUserRepository = wechatUserRepository;
    }


    public WechatUser saveWecharUser(WechatUser wechatUser){
        return wechatUserRepository.save(wechatUser);
    }

    public WechatUser findOne(String openId){
        return wechatUserRepository.findOne(openId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new WechatSecurityUser(findOne(username));
    }
}
