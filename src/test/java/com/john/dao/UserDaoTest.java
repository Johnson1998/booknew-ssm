package com.john.dao;

import com.john.pojo.User;
import com.john.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author John
 * @create 2021-10-2420:23
 */
public class UserDaoTest {
    @Test
    public void queryAllUser(){
        SqlSession session = MyBatisUtil.getSqlSession();

        //2.获取dao的代理
        UserDao dao = session.getMapper(UserDao.class);
        List<User> users = dao.queryAllUser();
        System.out.println(users);
        session.close();
    }

    @Test
    public void queryUserByUsername(){
        SqlSession session = MyBatisUtil.getSqlSession();

        //2.获取dao的代理
        UserDao dao = session.getMapper(UserDao.class);
        User admin41 = dao.queryUserByUsername("admin41");
        System.out.println(admin41);
        session.close();

    }
    @Test
    public void queryUserByUsernameAndPassword(){
        SqlSession session = MyBatisUtil.getSqlSession();

        //2.获取dao的代理
        UserDao dao = session.getMapper(UserDao.class);
        User admin41 = dao.queryUserByUsernameAndPassword("admin41", "123456");
        System.out.println(admin41);
        session.close();

    }

    @Test
    public void saveUser(){
        SqlSession session = MyBatisUtil.getSqlSession();

        //2.获取dao的代理
        UserDao dao = session.getMapper(UserDao.class);
        User user = new User();
        user.setUsername("gogogogogo");
        user.setPassword("123444321");
        user.setEmail("asdas@qq.com");
        dao.saveUser(user);
        session.commit();
        session.close();

    }
}
