<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/10/26
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智慧书城会员登录页面</title>
    <%--	静态包含base标签、css样式 jQuer文件--%>
    <%@include file="/WEB-INF/jsp/common/head.jsp"%>

</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>智慧书城会员</h1>
                    <a href="user/toRegister">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
<%--									<%=request.getAttribute("msg")==null?"请输入账号密码":request.getAttribute("msg")%>--%>
									${ empty msg ? "请输入用户名和密码":msg }
								</span>
                </div>
                <div class="form">
                    <form action="user/login" method="post">
                        <input type="hidden" name="action" value="login">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" value="${username}"/>
                        <br />
                        <br />
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" />
                        <br />
                        <br />
                        <input type="submit" value="登录" id="sub_btn" />
                        <div class="bott">
                            <a href="pages/user/forget_password.jsp">忘记密码</a>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
