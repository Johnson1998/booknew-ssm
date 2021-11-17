<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 2021/10/29
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="page_nav">
    <c:if test="${page.pageNo > 1}">
        <a href="${page.url}">首页</a>
        <a href="${page.url}/${page.pageNo-1}">上一页</a>
    </c:if>


    <%--			页码输出的开始 --%>
    <c:choose>
        <%--				总页码小于五的情况--%>
        <c:when test="${page.pageTotal<=5}">
            <c:forEach begin="1" end="${page.pageTotal}" var="i">
                <c:if test="${i == page.pageNo}">
                    【${i}】
                </c:if>
                <c:if test="${i != page.pageNo}">
                    <a href="${page.url}/${i}">
                            ${i}</a>
                </c:if>
            </c:forEach>
        </c:when>
        <%--				情况2大于5的情况，假设一共是10页--%>
        <c:when test="${page.pageTotal > 5}">
            <c:choose>
                <%--						小情况1：当前页码为1 , 2,3的情况--%>
                <c:when test="${page.pageNo <= 3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${i == page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != page.pageNo}">
                            <a href="${page.url}/${i}">
                                    ${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:when test="${page.pageNo > page.pageTotal-3}">
                    <c:forEach begin="${page.pageTotal - 4}" end="${page.pageTotal}"
                               var="i">
                        <c:if test="${i == page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != page.pageNo}">
                            <a href="${page.url}/${i}">
                                    ${i}</a>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach begin="${page.pageNo - 2}" end="${page.pageNo + 2}"
                               var="i">
                        <c:if test="${i == page.pageNo}">
                            【${i}】
                        </c:if>
                        <c:if test="${i != page.pageNo}">
                            <a href="${page.url}/${i}">
                                    ${i}</a>
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <%--			页码输出的结束 --%>

    <c:if test="${page.pageNo < page.pageTotal}">
        <a href="${page.url}/
					${page.pageNo+1}/4">下一页</a>
        <a href="${page.url}/${ page.pageTotal }">末页</a>
    </c:if>

    共${ page.pageTotal}页，${ page.pageTotalCount}条记录
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script>
        $(function () {
            // 跳到指定页码
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                if (pageNo < 1 || pageNo >${page.pageTotal}){
                    alert("非法下标");
                    return false;
                }
                // javasrcipt语言中提供了一个location地址栏对象
                // 它有一个属性叫href。它可以获取浏览器地址中的地址
                location.href = "${page.url}/" +
                    pageNo;
            });
        });
    </script>
</div>
