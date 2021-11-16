<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%--	静态包含base标签、css样式 jQuer文件--%>
	<%@include file="/WEB-INF/jsp/common/head.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>
		<%@include file="/WEB-INF/jsp/common/manager.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>详情</td>
				<td>发货</td>
				
			</tr>
			<c:forEach items="${requestScope.orderList}" var="order">
				<tr>
					<td>${order.createTime}</td>
					<td>${order.price}</td>
					<td><a href="${basePath}OrderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
					<c:choose>
						<c:when test="${order.status == 0}">
							<td><a href="order/sendOrder/${order.orderId}">点击发货</a></td>
						</c:when>
						<c:when test="${order.status == 1}">
							<td>已发货</td>
						</c:when>
						<c:when test="${order.status == 2}">
							<td>已签收</td>
						</c:when>

					</c:choose>
				</tr>
			</c:forEach>

		</table>
	</div>

	<%@include file="/WEB-INF/jsp/common/footer.jsp"%>
</body>
</html>