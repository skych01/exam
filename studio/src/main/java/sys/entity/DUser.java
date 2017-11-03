package sys.entity;

/**
 * Created by xiaoq on 2017-06-06.
 */

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="d_user")
public class DUser {

    @Id
    private int userId;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String mobile;
    private boolean enabled;
    private boolean expired;
    private boolean locked;
    private Boolean loginWayAccount;
    private Boolean loginWayQrcode;
    private Date createDate;
    private Date updateDate;
    private Integer creatorId;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "d_user_roles",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "roleId")}
    )
    private List<DRole> roles;

    public DUser(){
        super();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public List<DRole> getRoles() {
        return roles;
    }

    public void setRoles(List<DRole> roles) {
        this.roles = roles;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Boolean isLoginWayAccount() {
        return loginWayAccount;
    }

    public void setLoginWayAccount(Boolean loginWayAccount) {
        this.loginWayAccount = loginWayAccount;
    }

    public Boolean isLoginWayQrcode() {
        return loginWayQrcode;
    }

    public void setLoginWayQrcode(Boolean loginWayQrcode) {
        this.loginWayQrcode = loginWayQrcode;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * 特殊账号ID
     * @author xiaoqb
     *
     */
    public enum IDs {
        SYSTEM("系统", 0), ADMIN("超级管理员", 1);

        private String name;
        private int index;

        private IDs(String name, int index) {
            this.setName(name);
            this.setIndex(index);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
    }

    @Override
    public String toString() {
        return "DUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", enabled=" + enabled +
                ", expired=" + expired +
                ", locked=" + locked +
                ", loginWayAccount=" + loginWayAccount +
                ", loginWayQrcode=" + loginWayQrcode +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", creatorId=" + creatorId +
                ", roles=" + roles +
                '}';
    }
}
