<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/11/12
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购物车</title>
    <%--	静态包含base标签、css样式 jQuer文件--%>
    <%@include file="/WEB-INF/jsp/common/head.jsp"%>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">购物车</span>
    <%@ include file="/WEB-INF/jsp/common/login_success_menu.jsp"%>
</div>

<script>
    $(function () {
        $("a.deleteItem").click(function () {
            return confirm("你确定要删除【"+ $(this).parent().parent().find("td:first").text()+ "】吗？")
        });
        // 给清空购物车绑定事件
        $("#clearCart").click(function () {
            return confirm("你确定要情况购物车吗？")
        });
        $(".updateCount").change(function () {
            var goodsName = $(this).parent().parent().find("td:first").text();
            var id = $(this).attr("bookId");
            // var isChange = confirm("你确定要将【"+ goodsName+ "】商品数量修改为"+ this.value+"吗？");
            if (confirm("你确定要将【"+ goodsName+ "】商品数量修改为"+ this.value+"吗？")){

                location.href = "cart/updateCount/"+this.value+"/"+id;
            }else{
                this.value = this.defaultValue;
            }
        });
    });

</script>

<div id="main">

    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty sessionScope.cart.items}">
            <tr>
                <td colspan="5"><a href="index.jsp"> 亲，当前的购物车为空</a></td>
            </tr>
        </c:if>
        <c:forEach items="${sessionScope.cart.items}" var="entry">
            <tr>
                <td>${entry.value.name}</td>
                <td><input class="updateCount" bookId="${entry.value.id}" style="width: 50px" type="text"
                           value="${entry.value
				.count}"></td>
                <td>${entry.value.price}</td>
                <td>${entry.value.totalPrice}</td>
                <td><a class="deleteItem" href="cart/deleteItem/${entry.value.id}">删除</a></td>
            </tr>
        </c:forEach>

    </table>
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clearCart" href="cart/clear">清空购物车</a></span>
            <span class="cart_span"><a href="order/createOrder">去结账</a></span>
        </div>
    </c:if>

</div>

<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>