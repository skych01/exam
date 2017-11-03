package sys.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sys.entity.WechatRole;


public interface WechatRoleRepository extends JpaRepository<WechatRole, Integer> {
}
