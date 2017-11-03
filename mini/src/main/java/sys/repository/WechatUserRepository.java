package sys.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sys.entity.WechatUser;


public interface WechatUserRepository extends JpaRepository<WechatUser, String> {
}
