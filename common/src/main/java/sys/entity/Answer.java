package sys.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "answer")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;
    private String answerName;
    private boolean isCorrect;
    private Integer questionId;
    private Date createTime;
    private Date updateTime;

    public Answer(Integer answerId, String answerName, Boolean isCorrect, Integer questionId, Date createTime, Date updateTime) {
        this.answerId = answerId;
        this.answerName = answerName;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Answer() {
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", answerName='" + answerName + '\'' +
                ", isCorrect=" + isCorrect +
                ", questionId=" + questionId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
