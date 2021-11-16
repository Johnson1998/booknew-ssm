package com.john.controller;

import com.john.pojo.Book;
import com.john.pojo.Cart;
import com.john.pojo.CartItem;
import com.john.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author John
 * @create 2021-10-3021:07
 */
@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private BookService bookService;

    @RequestMapping("/cart")
    public String toCart() {
        return "/cart/cart";
    }

    @RequestMapping("/addItem/{id}")
    public String addItem(@PathVariable("id") Integer id,
                          HttpSession session,
                          @RequestHeader(value = "referer", required = false) final String referer){

        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) session.getAttribute("cart");
        if(cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        cart.addItem(cartItem);

        session.setAttribute("lastName", cartItem.getName());
        System.out.println("这里是referer" + referer);

    return "redirect:" + referer;
    }



    @RequestMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable("id") Integer id,
                             HttpSession session,
                             @RequestHeader(value = "referer", required = false) final String referer) {

        Cart cart = (Cart)session.getAttribute("cart");
        if (cart != null) {
            cart.deleteItem(id);
        }
        return "redirect:" + referer;
    }

    @RequestMapping("updateCount/{count}/{id}")
    public String updateCount(@PathVariable("id") Integer id,
                              @PathVariable("count") Integer count,
                              HttpSession session,
                              @RequestHeader(value = "referer", required = false) final String referer) {

        Cart cart = (Cart) session.getAttribute("cart");

        if (cart != null) {
            cart.updateCount(id, count);

        }
        return "redirect:" + referer;
    }

    @RequestMapping("clear")
    public String clearCart(HttpSession session,
                            @RequestHeader(value = "referer", required = false) final String referer) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart != null){
            cart.clear();

        }
        return "redirect:" + referer;
    }

    @RequestMapping("/checkout")
    public String checkout() {
        return "/cart/checkout";
    }



}
