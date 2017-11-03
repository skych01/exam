package sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.entity.DResource;

import java.util.List;


public interface DResourceRepository extends JpaRepository<DResource, String> {
    List<DResource> findByResourceUrl(String url);
}
