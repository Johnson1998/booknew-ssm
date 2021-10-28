package com.john.dao;

import com.john.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author John
 * @create 2021-10-289:10
 */
public interface BookDao {

    int addBook(Book book);

    int deleteBookById(Integer id);

    int updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(Map<String, Integer> map);

    Integer queryForPageTotalCountByPrice(@Param("min") Integer min, @Param("max") Integer max);

    List<Book> queryForPageItemsByPrice(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("min") int min,
                                        @Param("max") int max);
}
