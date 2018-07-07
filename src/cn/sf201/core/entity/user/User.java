package cn.sf201.core.entity.user;

import cn.sf201.core.entity.BaseObject;
import cn.sf201.core.utils.StringUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="t_system_user")
public class User extends BaseObject {
    private String userName;
    private String name;
    private String phone;
    private String password;

    public User() {
        this.id = StringUtil.generator();
    }

    @Column(name="user_name")
    public String getUserName() {
        return userName;
    }

    @Column(name="`name`")
    public String getName() {
        return name;
    }

    @Column(name="phone")
    public String getPhone() {
        return phone;
    }

    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
