package com.yixin.js.user.dao;

import com.yixin.js.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by 201603090214 on 2016/6/15.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>  {
    @Query("select userEntity from UserEntity userEntity where userEntity.id =?1")
    UserEntity findUser(Long ID);
}
