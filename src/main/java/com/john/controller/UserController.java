package com.john.controller;

import com.john.pojo.User;
import com.john.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

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

    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @RequestMapping("/register")
    public String register(User user, String emailCode, String code, HttpSession session, Model model) {

//        System.out.println(user);
//        System.out.println(emailCode);
//        System.out.println(code);
        String token = (String)session.getAttribute(KAPTCHA_SESSION_KEY);
//        System.out.println("token------>"+token);
        session.removeAttribute(KAPTCHA_SESSION_KEY);
        String username = user.getUsername();
        String email = user.getEmail();
        if (token != null && token.equalsIgnoreCase(code)){
            if (userService.existsUsername(username)){
                model.addAttribute("msg","用户名【 " + username + " 】已存在");
                model.addAttribute("username", username);
                model.addAttribute("email", email);
                return "register";

            }else {
                this.userService.register(user);
                return "register_success";

            }

        }else {
            model.addAttribute("msg", "验证码错误");
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            model.addAttribute("msg", "验证码错误");
            return "register";

        }
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(User user, Model model, HttpSession session){
        User loginUser = this.userService.login(user);
        if (this.userService.login(user) == null){
            model.addAttribute("msg", "账号密码错误");
            System.out.println(model.getAttribute("msg"));
            model.addAttribute("username",user.getUsername());
            return "login";
        }else {
            session.setAttribute("user", loginUser);
            return "login_success";
        }

    }


}
