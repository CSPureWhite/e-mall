<%@ page import="java.util.List" %>
<%@ page import="com.demo.entity.Goods" %>
<%@ page import="com.demo.service.GoodsService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--判断是否需要展示所有商品--%>
<%
    if(request.getAttribute("list")==null) {
        List<Goods> list = new GoodsService().showAllGoods();
        request.setAttribute("list", list);
    }
%>

<html>
<head>
    <title>商城主页</title>
    <style>
        .search{
            position: relative;
            left: 550px;
        }
    </style>
</head>
<%@include file="header.jsp"%>
<body>
    <div class="search">
        <form name="input" action="SearchServlet" method="post">
            <input type="text" name="name" placeholder="请输入商品名称">
            <input type="submit" value="搜索商品">
        </form>
    </div>
    <dl>
    <c:forEach items="${list}" var="goods">
            <dt><h4>${goods.goods_name}</h4></dt>
            <p><dd>描述:${goods.description}</dd></p>
            <p><dd>价格:${goods.price}元</dd></p>
            <p><dd>库存:${goods.stock}</dd></p>
        <c:if test="${not empty sessionScope.user}">
            <c:if test="${sessionScope.status.equals('customer')}">
            <p><button onclick="location.href='HomePage_CartAddServlet?customer_id=${sessionScope.user.user_id}&goods_id=${goods.goods_id}&name=${sessionScope.name}'">加入购物车</button></p>
            </c:if>
            <c:if test="${sessionScope.status.equals('admin')}">
                <p> <button onclick="location.href='GoodsDelServlet?goods_id=${goods.goods_id}'">删除该商品</button>
                    <button onclick="location.href='GoodsInfoServlet?goods_id=${goods.goods_id}'">修改该商品信息</button>
                </p>
            </c:if>
        </c:if>
    </c:forEach>
    </dl>
</body>
</html>