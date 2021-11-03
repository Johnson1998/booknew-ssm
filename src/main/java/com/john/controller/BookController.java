package com.john.controller;

import com.john.pojo.Book;
import com.john.pojo.Page;
import com.john.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author John
 * @create 2021-11-0118:03
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/page/{pageNo}/{pageSize}")
    public String page(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, Model model) {
        if (pageNo == null || pageNo <=0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = Page.PAGE_SIZE;
        }
        Page<Book> page = this.bookService.page(pageNo, pageSize);
        System.out.println(page);
        page.setUrl("book/page");

        model.addAttribute("page", page);

        return "manager/book_manager";

    }
    @RequestMapping("/getBook/{id}")
    public String getBook(@PathVariable("id") Integer id, Model model){
        Book book = this.bookService.queryBookById(id);

        model.addAttribute("book", book);
        return "manager/book_edit";

    }

    public String add(Book book, String pageNo) {
        this.bookService.addBook(book);
        return "redirect:/book/page/"+pageNo+"/4";
    }


}
