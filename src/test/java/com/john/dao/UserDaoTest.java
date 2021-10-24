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
}
