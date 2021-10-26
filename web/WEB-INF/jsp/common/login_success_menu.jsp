<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/10/26
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临智慧书城书城</span>
    <a href="OrderServlet?action=showMyOrders&userId=${sessionScope.user.id}">我的订单</a>
    <a href="userServlet?action=logout">注销</a>&nbsp;&nbsp;
    <a href="index.jsp">返回</a>
</div>
