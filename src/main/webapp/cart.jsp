<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>购物车页面</title>
    <style>
        .buttom {
            text-align: center;
        }
    </style>
</head>
<body>
    <c:forEach items="${list}" var="cartInfo">
        <dt><h4>${cartInfo.goods_name}</h4></dt>
        <p><dd>单价:${cartInfo.price}元</dd></p>
        <P><dd>数量:${cartInfo.amount}
                <button onclick="location.href='CartAddServlet?customer_id=${sessionScope.user.user_id}&goods_id=${cartInfo.goods_id}'">+</button>
                <button onclick="location.href='CartDelServlet?customer_id=${sessionScope.user.user_id}&cart_id=${cartInfo.cart_id}'">-</button>
        </P></dd>
        <P><dd>总价:${cartInfo.total_price}元</dd></P>
    <%--    <p><dd>--%>
    <%--    </dd></p>--%>
    </c:forEach>
    <div class="buttom">
        <p><h3>合计金额:${requestScope.total_amount}元</h3>
            <button onclick="location.href='PlaceOrderServlet'">结算</button>
        </p>
    </div>
</body>
</html>
