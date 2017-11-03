package sys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sys.entity.Answer;
import sys.entity.Questions;

import java.util.List;


public interface QuestionsRepository extends JpaRepository<Questions,Integer> {



/*    @Query("select a.question_name as questionName ,b.answer_id as answerId ," +
            "b.answer_name as answerName from questions a" +
            " left join answer b on a.questiong_id=b.question_id group by rand() ")
    List<Object> getRandData();*/
}
