package sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sys.entity.Answer;
import sys.entity.Questions;
import sys.repository.AnswerRepository;
import sys.repository.QuestionsRepository;
import sys.repository.util.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManageService {

    private Logger logger = LoggerFactory.getLogger(ManageService.class);
    private final QuestionsRepository questionsRepository;
    private final AnswerRepository answerRepository;
    private final SqlUtilRepository utilReposiory;

    @Autowired
    public ManageService(QuestionsRepository questionsRepository, AnswerRepository answerRepository, SqlUtilRepository utilReposiory) {
        this.questionsRepository = questionsRepository;
        this.answerRepository = answerRepository;
        this.utilReposiory = utilReposiory;
    }

    public void addQuestion(String questionName) {
        Date now = new Date();
        Questions questions = new Questions(questionName, now, now);
        questionsRepository.save(questions);
    }

    public void delQuestion(int questionId) {
        questionsRepository.delete(questionId);
    }

    public void updateQuestion(Questions questions) {
        Date now = new Date();
        questions.setUpdateTime(now);
        questionsRepository.save(questions);
    }

    public Questions findOneQuestion(int questionId) {
        return questionsRepository.findOne(questionId);
    }

    public Page<Questions> findQuestion(int pageNo, int pageSize, String name) {
        Questions questions = new Questions();
        if (name != null && !name.isEmpty()) {
            questions.setQuestionName(name);
        }
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        PageRequest pageRequest = new PageRequest(pageNo - 1, pageSize, sort);

        //设置查询条件
        ExampleMatcher matcher = ExampleMatcher.matching()
                //模糊查询
                .withMatcher("questionName", ExampleMatcher.GenericPropertyMatchers.contains());
        Example<Questions> ex = Example.of(questions, matcher);
        return questionsRepository.findAll(ex, pageRequest);
    }

    public sys.repository.util.Page findQuestionAndAnswer(int pageNo, int pageSize, String name) {
        StringBuilder sql = new StringBuilder();
        List<Object> objects = new ArrayList<Object>();
        sql.append("select a.question_id as questionId ," +
                "a.question_name as questionName,a.update_time as updateTime" +
                ",group_concat(b.answer_name) as answerName from questions a  ")
                .append("left join answer b on a.question_id=b.question_id and b.is_correct=1 where 1=1 ");
        if (name != null && !name.isEmpty()) {
            objects.add(name);
            sql.append("and a.question_name like '%?%'");
        }
       sql.append("GROUP BY a.question_id");
        return utilReposiory.pageSQLQuery(sql.toString(), pageNo, pageSize, -1, objects.toArray());
    }


    public void addAnswer(Answer answer) {
        if (findOneQuestion(answer.getQuestionId()) != null) {
            Date now = new Date();
            answer.setCreateTime(now);
            answer.setUpdateTime(now);
            answerRepository.save(answer);
        } else {
            logger.error("ManageService add Answer 方法 answer里的question 不存在！");
        }
    }

    public void UpdateAnswer(Answer answer) {
        Date now = new Date();
        answer.setUpdateTime(now);
        answerRepository.save(answer);
    }

    public void delAnswer(int answer) {
        answerRepository.delete(answer);
    }

    public List<Answer> findAnswerByQuestionId(int questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public Answer findOneAnswer(int answerId) {
        return answerRepository.findOne(answerId);

    }

}
