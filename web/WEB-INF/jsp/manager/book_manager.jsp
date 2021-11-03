<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/11/1
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图书管理</title>
    <%--	静态包含base标签、css样式 jQuer文件--%>
    <%@include file="/WEB-INF/jsp/common/head.jsp"%>
    <script>
        $(function () {
            // 给删除的a标签绑定单击事件，用于删除的确认提示操作
            $("a.deleteClass").click(function () {
                // 在事件的function函数中，有一个this对象，是当前正在响应事件的dom对象
                /**
                 * confirm是确认提示框函数
                 * 参数是他的提示内容
                 * 它有两个按钮，一个确认，一个取消
                 * 返回true表示点击了确认，返回false表示点击了取消
                 */
                return confirm("你确实要删除[" + $(this).parent().parent().find("td:first").text()+ "]");
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif" >
    <span class="wel_word">图书管理系统</span>
    <%@include file="/WEB-INF/jsp/common/manager.jsp"%>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>价格</td>
            <td>作者</td>
            <td>销量</td>
            <td>库存</td>
            <td colspan="2">操作</td>
        </tr>
        <c:forEach items="${page.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="book/getBook/${book.id}">修改</a></td>
                <td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${book.id}">删除</a></td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.page.pageTotal}">添加图书</a></td>
        </tr>
    </table>
    <%@include file="/WEB-INF/jsp/common/page_nav.jsp"%>
</div>

<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
