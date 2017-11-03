package sys.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 微信用户
 */
@Entity
@Table(name = "wechat_user")
public class WechatUser {
    @Id
    private String openId;
    private String nickName;
    private Short gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String appid;
    private Date createTime;
    private Date updateTime;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "wechat_user_roles",
            joinColumns = {@JoinColumn(name = "openId", referencedColumnName = "openId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "roleId")}
    )
    private List<WechatRole> roles;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 昵称
     */
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 性别 0：未知、1：男、2：女
     */
    public Short getGender() {
        return gender;
    }

    public void setGender(Short gender) {
        this.gender = gender;
    }

    /**
     * 语言
     */
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * 城市
     */
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 省份
     */
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 国家
     */
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 头像
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
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

    public List<WechatRole> getRoles() {
        return roles;
    }

    public void setRoles(List<WechatRole> roles) {
        this.roles = roles;
    }
}
