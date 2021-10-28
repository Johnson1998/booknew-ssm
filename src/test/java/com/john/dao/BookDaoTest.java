package com.john.dao;

import com.john.pojo.Book;
import com.john.pojo.User;
import com.john.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author John
 * @create 2021-10-289:16
 */
public class BookDaoTest {
    @Test
    public void addBook(){
        SqlSession session = MyBatisUtil.getSqlSession();

        //2.获取dao的代理
        BookDao dao = session.getMapper(BookDao.class);
        Book book = new Book();
        book.setName("没有书");
        book.setAuthor("john");
        book.setPrice(new BigDecimal(31));
        book.setStock(212);
        int i = dao.addBook(book);
        session.commit();
        System.out.println(i);
        session.close();
    }

    @Test
    public void deleteBookById(){
        SqlSession session = MyBatisUtil.getSqlSession();

        //2.获取dao的代理
        BookDao dao = session.getMapper(BookDao.class);
        dao.deleteBookById(75);
        session.commit();

        session.close();
    }
    @Test
    public void updateBook(){
        SqlSession session = MyBatisUtil.getSqlSession();

        //2.获取dao的代理
        BookDao dao = session.getMapper(BookDao.class);
        Book book = new Book();
        book.setId(76);
        book.setName("有书");
        book.setAuthor("johnson");
        book.setPrice(new BigDecimal(41));
        book.setStock(232);
        int i = dao.updateBook(book);
        session.commit();
        System.out.println(i);
        session.close();
    }
}
