package com.john.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author John
 * @create 2021-10-258:11
 */
public class UserServiceTest {

    @Test
    public void queryAllUser(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = classPathXmlApplicationContext.getBean("userService", UserService.class);
        userService.queryAllUser();
    }
}
