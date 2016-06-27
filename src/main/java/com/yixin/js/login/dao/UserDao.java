package com.yixin.js.login.dao;

import com.yixin.js.login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by 201603090214 on 2016/6/23.
 */
@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    @Query("select user from User user where user.username =?1")
    User findByName(String loginName);
}
