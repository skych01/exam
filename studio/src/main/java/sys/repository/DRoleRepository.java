package sys.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sys.entity.DRole;


public interface DRoleRepository extends JpaRepository<DRole, Integer> {
    DRole findByRoleName(String roleName);
}
