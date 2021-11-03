package com.john.controller;

import com.john.pojo.User;
import com.john.service.UserService;
import com.john.utils.CodeRandomUtil;
import com.john.utils.HTMLText;
import com.john.utils.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author John
 * @create 2021-10-2419:32
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/logout")
    public String logout(HttpSession sess){
        sess.removeAttribute("user");

        return "redirect:/user/login";
    }

    @RequestMapping("/showAllUser")
    public String showAllUser(Model model){
        List<User> users = userService.queryAllUser();
        model.addAttribute("users",users);

        return "showAllUser";
    }

//    @RequestMapping("/toRegister")
//    public String toRegister(){
//        return "user/register";
//    }

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
                return "user/register";

            }else {
                this.userService.register(user);
                return "user/register_success";

            }

        }else {
            model.addAttribute("msg", "验证码错误");
            model.addAttribute("username", username);
            model.addAttribute("email", email);
            model.addAttribute("msg", "验证码错误");
            return "user/register";

        }
    }

    @RequestMapping("/login")
    public String login(User user, Model model, HttpSession session){
        User loginUser = this.userService.login(user);
        if (this.userService.login(user) == null){
            model.addAttribute("msg", "账号密码错误");
            System.out.println(model.getAttribute("msg"));
            model.addAttribute("username",user.getUsername());
            return "user/login";
        }else {
            session.setAttribute("user", loginUser);
            return "user/login_success";
        }

    }

    @RequestMapping("/sendEmailCode")
    public void sendEmailCode(String email, HttpSession sess) {
        try {
            System.out.println(email);
            SendMailUtil.receiveMailAccount = email;
//             1.创建参数配置，用于连接邮箱服务器的参数配置
            Properties props = new Properties();
            props.setProperty("mail.debug", "true");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.host", "smtp.qq.com");
            props.setProperty("mail.transport.protocol", "smtp");

//        2.根据配置创造会话对象，用于和邮件服务器交互
            Session session = Session.getInstance(props);

            session.setDebug(true);
            //3.创建一封邮件 导入activation后解决报错
            String code = CodeRandomUtil.getRandom(6);
            String html = HTMLText.html(code);
            MimeMessage message = SendMailUtil.createMimeMessage(session, SendMailUtil.emailAccount,
                    SendMailUtil.receiveMailAccount, html);

//        4.根据session获取邮件传输对象
            Transport transport = session.getTransport();
//        5.使用邮箱账号和密码连接邮件服务器
            transport.connect(SendMailUtil.emailAccount, SendMailUtil.emailPassword);
//        6.发送邮件，发送所有收件人地址
            transport.sendMessage(message, message.getAllRecipients());
            //7.关闭连接
            transport.close();
            sess.setAttribute("emailCode", code);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败" + e);
        }
    }


}
