package sys.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "questions_favorite")
public class QuestionFavorite {
    @Id
    private Integer questionId;
    private Integer favoriteId;

    private Date createTime;
    private Date updateTime;

    public QuestionFavorite() {
    }

    public QuestionFavorite(Integer questionId, Integer favoriteId, Date createTime, Date updateTime) {
        this.questionId = questionId;
        this.favoriteId = favoriteId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
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
        return "QuestionFavorite{" +
                "questionId=" + questionId +
                ", favoriteId=" + favoriteId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
