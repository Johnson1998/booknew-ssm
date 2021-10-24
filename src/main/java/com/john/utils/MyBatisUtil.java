package com.john.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author John
 * @create 2021-10-2420:21
 */
public class MyBatisUtil {

        private static SqlSessionFactory factory = null;
        static {
            String config ="mybatis-config.xml";
            try {
                InputStream inputStream = Resources.getResourceAsStream(config);
                factory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 创建方法：获取sqlSession对象
        public static SqlSession getSqlSession(){
            SqlSession session = null;
            if (factory != null){
                session = factory.openSession();
            }
            return session;
        }

    }

