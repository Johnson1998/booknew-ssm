<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/10/26
  Time: 19:20
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
    <%--				静态包含维护一个页面--%>
    <%@ include file="/WEB-INF/jsp/common/login_success_menu.jsp"%>
</div>

<div id="main">

    <h1>欢迎回来 <a href="index.jsp"></a></h1>

</div>

<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
