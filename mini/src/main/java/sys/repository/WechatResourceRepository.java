package sys.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sys.entity.WechatResource;

import java.util.List;


public interface WechatResourceRepository extends JpaRepository<WechatResource, String> {
    List<WechatResource> findByResourceUrl(String url);

    List<WechatResource> findByResourceUrlLike(String likeUrl);


}
