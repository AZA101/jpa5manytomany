package cn.itcast5.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;

    /*@ManyToMany(targetEntity = User.class)
    //name 中间表的名称
    @JoinTable(name = "sys_user_role",
            //joinColumns,当前对象在中间表的外键
            joinColumns = {@JoinColumn(name = " sys_role_id", referencedColumnName = "role_id")},
            //inverseJoinColumns 对方对象在中间表的外键
            inverseJoinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")}
    )*/
    @ManyToMany(mappedBy = "roleSet")
    private Set<User> users=new HashSet<User>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
