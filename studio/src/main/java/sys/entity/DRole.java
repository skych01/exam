package sys.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by xiaoq on 2017-06-06.
 */
@Entity
@Table(name = "d_role")
public class DRole {
    @Id
    private int roleId;
    private String roleName;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "d_role_resources",
            joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "roleId")},
            inverseJoinColumns = {@JoinColumn(name = "resourceUrl", referencedColumnName = "resourceUrl")}
    )
    private List<DResource> resources;

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

    public List<DResource> getResources() {
        return resources;
    }

    public void setResources(List<DResource> resources) {
        this.resources = resources;
    }
}
