<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/10/29
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理</title>
    <%--	静态包含base标签、css样式 jQuer文件--%>
    <%@include file="/WEB-INF/jsp/common/head.jsp"%>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }
    </style>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">后台管理系统</span>
    <%@include file="/WEB-INF/jsp/common/manager.jsp"%>
</div>

<div id="main">
    <h1>欢迎管理员进入后台管理系统</h1>
</div>

<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
