package sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sys.entity.Answer;

import java.util.List;

/**
 * Created by Administrator on 2017/10/16.
 */
public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    List<Answer> findByQuestionId(int questionId);
}
