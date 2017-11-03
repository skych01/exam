package sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sys.entity.DUser;


public interface DUserRepository extends JpaRepository<DUser, Integer> {
    DUser findByUsername(String username);

    @Query("select max (DUser.userId) from DUser ")
    int findMaxId();
}
