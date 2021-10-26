<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/10/26
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>智慧书城会员注册页面</title>
    <%--	静态包含base标签、css样式 jQuer文件--%>
    <%@include file="/WEB-INF/jsp/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color:red;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word"></span>
    <%@ include file="/WEB-INF/jsp/common/login_success_menu.jsp"%>
</div>

<div id="main">

    <h1>注册成功! <a href="index.jsp">转到主页</a></h1>

</div>

<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
