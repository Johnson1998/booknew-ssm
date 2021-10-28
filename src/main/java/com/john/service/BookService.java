package com.john.service;

import com.john.dao.BookDao;
import com.john.pojo.Book;
import com.john.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author John
 * @create 2021-10-2814:23
 */
@Service
public class BookService {
    @Autowired
    public BookDao bookDao;

    public BookService() {
    }

    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    public void deleteBookById(Integer id) {
        this.bookDao.deleteBookById(id);
    }

    public void updateBook(Book book) {
        this.bookDao.updateBook(book);
    }

    public Book queryBookById(Integer id) {
        return this.bookDao.queryBookById(id);
    }

    public List<Book> queryBooks() {
        return this.bookDao.queryBooks();
    }

    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page();
        page.setPageSize(pageSize);
        Integer pageTotalCount = this.bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageNo < 1) {
            pageNo = 1;
        }

        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }

        page.setPageNo(pageNo);
        if (pageTotalCount % pageSize > 0) {
            pageTotal = pageTotal + 1;
        }

        page.setPageTotal(pageTotal);
        int begin = (page.getPageNo() - 1) * pageSize;
        Map<String, Integer> map = new HashMap<>();
        map.put("begin", begin);
        map.put("end", pageSize);

        List<Book> items = this.bookDao.queryForPageItems(map);
        page.setItems(items);
        return page;
    }

    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<Book>();
        page.setPageSize(pageSize);
        Integer pageTotalCount = this.bookDao.queryForPageTotalCountByPrice(min, max);
        page.setPageTotalCount(pageTotalCount);
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageNo < 1) {
            pageNo = 1;
        }

        if (pageNo > pageTotal) {
            pageNo = pageTotal;
        }

        page.setPageNo(pageNo);
        if (pageTotalCount % pageSize > 0) {
            pageTotal = pageTotal + 1;
        }

        page.setPageTotal(pageTotal);
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = this.bookDao.queryForPageItemsByPrice(begin, pageSize, min, max);
        System.out.println("begin:"+begin+"--->pageSize:"+pageSize+"---->min:"+min+"----->max:"+max);
        page.setItems(items);
        return page;
    }
}