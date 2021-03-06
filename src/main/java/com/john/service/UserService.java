package com.john.service;

import com.john.dao.UserDao;
import com.john.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author John
 * @create 2021-10-2419:30
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> queryAllUser(){
        return this.userDao.queryAllUser();
    }

    public void register(User user){
        this.userDao.saveUser(user);
    }

    public boolean existsUsername(String username){
        return (this.userDao.queryUserByUsername(username) != null);
    }

    public User login(User user){
        return this.userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }




}
