package sys.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sys.entity.Answer;
import sys.entity.Favorite;
import sys.entity.QuestionFavorite;
import sys.repository.AnswerRepository;
import sys.repository.FavoriteRepository;
import sys.repository.QuestionFavoriteRepository;
import sys.repository.util.SqlUtilRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class ManageService {
    private Logger logger = LoggerFactory.getLogger(ManageService.class);
    private final AnswerRepository answerRepository;
    private final SqlUtilRepository utilReposiory;
    private final FavoriteRepository favoriteRepository;
    private final QuestionFavoriteRepository questionFavoriteRepository;

    @Value("${my.num}")
    private   int questionNum;

    @Autowired
    public ManageService(AnswerRepository answerRepository,
                         SqlUtilRepository sqlUtilRepository, FavoriteRepository favoriteRepository,
                         QuestionFavoriteRepository questionFavoriteRepository) {
        this.answerRepository = answerRepository;
        this.utilReposiory = sqlUtilRepository;
        this.favoriteRepository = favoriteRepository;
        this.questionFavoriteRepository = questionFavoriteRepository;
    }

    /**
     * 模拟测试
     * 随机选择 题目
     * 随机数量 配置文件里修改 questionNum
     */
    @Transactional
    public List test(){
        StringBuilder sql = new StringBuilder();
        sql.append("select a.question_name as questionName ,  b.answer_id as answerId, " +
                " group_concat(b.answer_name) as answerName from questions a " +
                "left join answer b on a.question_id=b.question_id " +
                "group by a.question_id  ORDER BY RAND() limit ?");
        return utilReposiory.query(sql.toString(),questionNum);
    }
    /**
     * 交卷算分数
     */
    public double getPoints(List<Integer> answerIds){
        int correctNum = 0;
        for (int answerId:answerIds){
            Answer answer = answerRepository.findOne(answerId);
            if(answer.getCorrect()){
                correctNum++;
            }
        }
        return 100 * correctNum / questionNum;
    }

    /**
     * 创建收藏夹
     */
    public void createCollect(String name,String openId){
        Date now = new Date();
        Favorite favorite = new Favorite(name,openId,now,now);
        favoriteRepository.save(favorite);
    }

    /**
     * 收藏试题
     */
    public void collectTest(int favoriteId, int questionsId){
        Date now = new Date();
        QuestionFavorite questionFavorite = new QuestionFavorite(questionsId, favoriteId, now, now);
        questionFavoriteRepository.save(questionFavorite);
    }
}
