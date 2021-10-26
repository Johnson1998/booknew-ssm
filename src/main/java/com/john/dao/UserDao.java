package com.john.dao;

import com.john.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author John
 * @create 2021-10-2419:23
 */
@Repository
public interface UserDao {
    public List<User> queryAllUser();

    User queryUserByUsername(@Param("username") String username);

    User queryUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    int saveUser(User user);

    int updateUser(User user);
}
