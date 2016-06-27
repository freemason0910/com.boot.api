package com.yixin.js.user.model;


import com.yixin.js.common.model.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "sys_user")
public class UserEntity extends BaseEntity {
    @Column(name="user_id")
    private String userId;
    @Column(name="password")
    private String passWord;
    @Column(name="user_name")
    private String userName;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }




}
