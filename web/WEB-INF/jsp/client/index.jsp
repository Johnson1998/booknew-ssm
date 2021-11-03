<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/10/28
  Time: 8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>书城首页</title>
    <%--	静态包含base标签、css样式 jQuer文件--%>
    <%@include file="/WEB-INF/jsp/common/head.jsp"%>
    <Script>
        $(function () {
            $("button.addToCart").click(function () {
                var bookId = $(this).attr("bookId");
                <%--if(${empty sessionScope.cart}){--%>
                //使用表单实现购物车功能
                location.href = "${basePath}CartServlet?action=addItem&id=" + bookId;
                <%--}else{--%>
                <%--&lt;%&ndash;location.href = "${basePath}CartServlet?action=addItem&id=" + bookId;&ndash;%&gt;--%>
                <%--// 发ajax请求，添加商品到购物车--%>
                <%--$.getJSON("${basePath}CartServlet", "action=ajaxAddItem&id=" + bookId, function (data) {--%>
                <%--  $("#cartTotalCount").text("您的购物车中有" + data.totalCount + "件商品")--%>
                <%--  $("#cartLastName").text(data.lastname)--%>

                <%--})--%>
                // }
            });
        });
    </Script>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <div>
        <c:if test="${empty sessionScope.user}">
            <a href="user/toLogin">登录</a>
            <a href="user/toRegister">注册</a>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临智慧书城书城</span>
            <a href="OrderServlet?action=showMyOrders&userId=${sessionScope.user.id}">我的订单</a>
            <a href="user/logout">注销</a>&nbsp;&nbsp;
        </c:if>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="manager/manager">后台管理</a>

    </div>
    <span class="wel_word">智慧书城</span>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="clientBook/pageByPrice" method="get">
                价格：<input id="min" type="text" name="min" value="${param.min}"> 元 -
                <input id="max" type="text" name="max" value="${param.max}"> 元
                <input type="submit" value="查询" />
            </form>
        </div>
        <div style="text-align: center">
            <c:if test="${empty sessionScope.cart.items}">
                <span></span>
                <div>
                    <span></span>
                    <span style="color: red">当前购物车为空</span>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.cart.items}">
                <span id="cartTotalCount">您的购物车中有${sessionScope.cart.totalCount}件商品</span>
                <div>
                    您刚刚将<span style="color: red " id="cartLastName">${sessionScope.lastName}</span>加入到了购物车中
                </div>
            </c:if>
        </div>


        <c:forEach items="${page.items}" var="Book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${Book.imgPath}" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${Book.name}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${Book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">${Book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${Book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${Book.stock}</span>
                    </div>
                    <div class="book_add">
                        <button bookId="${book.id}" class="addToCart">加入购物车</button>
                    </div>
                </div>
            </div>

        </c:forEach>
<%--        <c:if test="${not empty page.items}">--%>
<%--            <div>${page.items}</div>--%>
<%--        </c:if>--%>
    </div>

    <%@include file="/WEB-INF/jsp/common/page_nav.jsp"%>

</div>

<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>
