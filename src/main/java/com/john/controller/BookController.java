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
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

//    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int pageNo = WebUtil.parseInt(req.getParameter("pageNo"), 1);
//        int pageSize = WebUtil.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
//        Page<Book> page = this.bookService.page(pageNo, pageSize);
//        page.setUrl("Client/bookServlet?action=page");
//        req.setAttribute("page", page);
//        System.out.println(page);
//        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
//    }

    @RequestMapping(value="/page/{pageNo}/{pageSize}")
    public String page(@PathVariable("pageNo") Integer pageNo, @PathVariable("pageSize") Integer pageSize, Model model
    ){
        System.out.println("pageNo+++"+pageNo+"pageSize"+pageSize);
        if (pageNo == null || pageNo <= 0){
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0){
            pageSize = Page.PAGE_SIZE;
        }
        Page<Book> page =  this.bookService.page(pageNo, pageSize);
        page.setUrl("book/page");
//        model.addAttribute("page", page);
        model.addAttribute("page", page);
        System.out.println(page);
        return "client/index";



    }

    @RequestMapping("/pageByPrice")
    public String pageByPrice(@RequestParam("min")String minStr, @RequestParam("max") String maxStr, Model model){
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

        model.addAttribute("page", page);
        System.out.println("page++++++++++++>" + page);

        return  "client/index";
    }



//    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int pageNo = WebUtil.parseInt(req.getParameter("pageNo"), 1);
//        int pageSize = WebUtil.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
//        int min = WebUtil.parseInt(req.getParameter("min"), 0);
//        int max = WebUtil.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
//        Page<Book> page = this.bookService.pageByPrice(pageNo, pageSize, min, max);
//        StringBuilder sb = new StringBuilder("Client/bookServlet?action=pageByPrice");
//        if (req.getParameter("min" )!= null){
//            sb.append("&min=").append(min);
//        }
//        if (req.getParameter("max" )!= null){
//            sb.append("&max=").append(max);
//        }
//        page.setUrl(sb.toString());
//        req.setAttribute("page", page);
//
//        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
//
//    }
}
