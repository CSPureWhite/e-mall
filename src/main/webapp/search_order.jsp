<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.demo.entity.Orders" %>
<%@ page import="com.demo.service.OrdersService" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<%--判断是否需要展示所有购买记录--%>
<%
    if(request.getAttribute("list")==null) {
        List<Orders> list = new OrdersService().showAll();
        request.setAttribute("list", list);
    }
%>
<html>
<head>
    <title>客户购买记录查询页</title>
    <style>
        .search{
            position: relative;
            left: 550px;
        }
        .table{
            position: relative;
            left: 400px;
        }
    </style>
</head>
<body>
    <p><h2 style="text-align: center">查询客户购买记录</h2>
    <div class="search">
        <form name="input" action="OrderViewServlet" method="post">
            <input type="text" name="customer_id" placeholder="请输入客户id" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')">
            <input type="submit" value="搜索客户购买记录">
        </form>
    </div>
    <div class="table">
        <table border="2">
            <tr>
                <td>订单id</td>
                <td>客户id</td>
                <td>商品id</td>
                <td>商品名称</td>
                <td>商品数量</td>
                <td>单价</td>
                <td>总价</td>
                <td>下单时间</td>
            </tr>
            <c:forEach items="${list}" var="orders">
            <tr>
                <td>${orders.order_id}</td>
                <td>${orders.customer_id}</td>
                <td>${orders.goods_id}</td>
                <td>${orders.goods_name}</td>
                <td>${orders.amount}</td>
                <td>${orders.price}元</td>
                <td>${orders.total_price}元</td>
                <td><fmt:formatDate value="${orders.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
