package com.john.controller;

import com.john.pojo.Book;
import com.john.pojo.Page;
import com.john.service.BookService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;

/**
 * @author John
 * @create 2021-10-2814:25
 */
@Controller
@RequestMapping("/clientBook")
public class ClientBookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = {"/page/{pageNo}/{pageSize}", "/page/{pageNo}", "/page"})
    public String page(@PathVariable(value = "pageNo", required = false) Integer pageNo,
                       @PathVariable(value = "pageSize", required = false) Integer pageSize,
                       Model model
    ){
        System.out.println("pageNo+++"+pageNo+"pageSize"+pageSize);
        if (pageNo == null || pageNo <= 0){
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0){
            pageSize = Page.PAGE_SIZE;
        }
        Page<Book> page =  this.bookService.page(pageNo, pageSize);
        page.setUrl("clientBook/page");
        model.addAttribute("page", page);
        System.out.println(page);
        return "client/index";



    }

    @RequestMapping("/pageByPrice")
    public String pageByPrice(
                              @RequestParam("min")String minStr, @RequestParam("max") String maxStr, Model model){
        int min = 0;
        int max = Integer.MAX_VALUE;
        if(minStr != null){
            min = Integer.parseInt(minStr);
        }
        if (maxStr != null){
            max = Integer.parseInt(maxStr);
        }
        System.out.println("min:"+min+"----->max:"+max);
        Page<Book> page = this.bookService.pageByPrice(1, Page.PAGE_SIZE, min, max);
        StringBuilder sb = new StringBuilder("clientBook/pageByPrice?min="+min+"&max="+max);
        System.out.println(sb.toString());
        page.setUrl(sb.toString());

        model.addAttribute("page", page);
        System.out.println("page++++++++++++>" + page);

        return  "client/index";
    }

}
