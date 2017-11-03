package sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.entity.Answer;
import sys.entity.QuestionFavorite;

/**
 * Created by Administrator on 2017/10/16.
 */
public interface QuestionFavoriteRepository extends JpaRepository<QuestionFavorite,Integer> {
}
