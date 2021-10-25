package com.john.controller;

import com.john.pojo.User;
import com.john.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author John
 * @create 2021-10-2419:32
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/showAllUser")
    public String showAllUser(Model model){
        List<User> users = userService.queryAllUser();
        model.addAttribute("users",users);

        return "showAllUser";
    }
}
