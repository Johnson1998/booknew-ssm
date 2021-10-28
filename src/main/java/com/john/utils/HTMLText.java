package com.john.utils;

/**
 * @author John
 * @create 2021-10-2721:09
 */
public class HTMLText {
    public static String html(String code){
        String html = "<h1 style='text-align:center'>Email地址验证</h1>"+
                "<div style='text-align:center'>这封邮件是由【智慧书城】发送的。</div>"+
                "<div style='text-align:center'>请将下面的<label style='color:red;'>红色</label>验证码输入到提示框即可：<h3 " +
                "style='color:red;'>" + code + "</h3></div>";
        return html;

    }
}