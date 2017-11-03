package sys.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 主要用来加载JPA相关Entity、DAO、Service
 */
@Configuration
@EnableJpaRepositories(basePackages = {"sys.repository"})
@EntityScan({"sys.entity"})
@ComponentScan({"sys"})
public class JPAConfig {
    
}
