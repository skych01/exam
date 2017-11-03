package sys.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sys.entity.Answer;
import sys.entity.Questions;
import sys.repository.QuestionsRepository;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageServiceTest {
    @Autowired
    private ManageService manageService;
    private Date now;
    @org.junit.Test
    public void addQuestion() throws Exception {
        for (int i=0;i<500;i++){
            manageService.addQuestion("question : " + i);
        }
    }

    @org.junit.Test
    public void delQuestion() throws Exception {
            manageService.delQuestion(-1);
            manageService.delQuestion(0);
            manageService.delQuestion(1);
            manageService.delQuestion(500);
            manageService.delQuestion(5000);


    }


    @org.junit.Test
    public void findQuestion() throws Exception {
        Date now = new Date();
        for (int i =0;i<500;i++){
            for(int ii=0;ii<3;ii++){
            Answer answer = new Answer();
            answer.setCreateTime(now);
            answer.setUpdateTime(now);
            answer.setAnswerName("错误答案 "+ ii);
                answer.setCorrect(false);
            answer.setQuestionId(i);
            manageService.addAnswer(answer);
            }
            Answer answer = new Answer();
            answer.setCreateTime(now);
            answer.setUpdateTime(now);
            answer.setAnswerName("正确答案 ");
            answer.setCorrect(true);
            answer.setQuestionId(i);
            manageService.addAnswer(answer);
        }
/*        List<Questions> list= manageService.findQuestion(1, 500, null).getContent();
        list.stream().forEach(q -> manageService.);*/
    }



}
