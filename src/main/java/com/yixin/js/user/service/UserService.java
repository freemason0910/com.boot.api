package com.yixin.js.user.service;


import com.yixin.js.user.model.UserEntity;

/**
 * Created by 201603090214 on 2016/6/15.
 */
public interface UserService {
    public UserEntity findByid(Long id);
}
