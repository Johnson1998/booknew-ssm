package com.john.controller;

import com.john.pojo.Cart;
import com.john.pojo.Order;
import com.john.pojo.User;
import com.john.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: John
 * @Date: 2021/11/16/19:21
 * @Description:
 */
@RequestMapping("/order")
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/createOrder")
    public String createOrder(HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/user/login";
        }
        Integer id = user.getId();
        String orderId = orderService.createOrder(cart, id);
        session.setAttribute("orderId", orderId);

        return "redirect:/cart/checkout";
    }

    @RequestMapping("/showAllOrders")
    public String showAllOrders(Model model) {
        List<Order> orders = orderService.showAllOrders();
        model.addAttribute("orderList", orders);

        return "manager/order_manager";
    }

    @RequestMapping("/sendOrder/{orderId}")
    public String sendOrder(@PathVariable("orderId") String orderId,
                            @RequestHeader(value = "referer", required = false) final String referer) {
        orderService.sendOrder(orderId);
        return "redirect:" + referer;
    }
}
