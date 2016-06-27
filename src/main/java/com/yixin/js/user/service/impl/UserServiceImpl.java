package com.yixin.js.user.service.impl;

import com.yixin.js.user.dao.UserRepository;
import com.yixin.js.user.model.UserEntity;
import com.yixin.js.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 201603090214 on 2016/6/15.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userDao;
    @Override
    public UserEntity findByid(Long id) {
        return userDao.findOne(id);
    }
}
