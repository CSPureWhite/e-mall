<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>结算成功页</title>
    <style>
        .title{
            text-align: center;
        }
    </style>
</head>
<body>
<div class="title">
    <h2>下单成功!总金额:${requestScope.total_amount}元</h2>
    <h3>以下为下单成功的商品（若购物车中有商品下单失败，原因为库存不足）。</h3>
</div>
<c:forEach items="${list}" var="orders">
    <dt><h4>${orders.goods_name}</h4></dt>
    <p><dd>单价:${orders.price}元</dd></p>
    <P><dd>数量:${orders.amount}</P></dd>
    <P><dd>总价:${orders.total_price}元</dd></P>
</c:forEach>
</body>
</html>
