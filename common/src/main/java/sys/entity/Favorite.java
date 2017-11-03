package sys.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "favorite")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //由底层添加
    private Integer favoriteId;
    private String favoriteName;
    private String openId;
    private Date createTime;
    private Date updateTime;

    public Favorite() {
    }

    public Favorite( String favoriteName, String openId, Date createTime, Date updateTime) {
        this.favoriteName = favoriteName;
        this.openId = openId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getFavoriteName() {
        return favoriteName;
    }

    public void setFavoriteName(String favoriteName) {
        this.favoriteName = favoriteName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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
        return "Favorite{" +
                "favoriteId=" + favoriteId +
                ", favoriteName='" + favoriteName + '\'' +
                ", openId='" + openId + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
