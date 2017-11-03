package sys.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by xiaoq on 2017-06-06.
 */
@Entity
@Table(name = "wechat_role")
public class WechatRole {
    @Id
    private int roleId;
    private String roleName;

    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "wechat_role_resources",
            joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "resourceUrl", referencedColumnName = "resourceUrl")}
    )
    private List<WechatResource> resources;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<WechatResource> getResources() {
        return resources;
    }

    public void setResources(List<WechatResource> resources) {
        this.resources = resources;
    }
}
