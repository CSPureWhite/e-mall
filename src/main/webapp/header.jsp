<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        header{
            display: flex;
            justify-content: center;
            position: relative;
        }
        header h1{
            position: relative;
            left: 100px;

        }
        header button{
            position: relative;
            left: 350px;
            top: 40px;
            margin-left: 10px;
        }
    </style>
</head>
<header>
    <h1>e-mall商城</h1>
    <div>
        <button onclick="location.href='HomePageServlet'">首页</button>
        <c:if test="${empty sessionScope.user}">
            <button onclick="location.href='/e-mall/login.jsp'">登录</button>
            <button onclick="location.href='/e-mall/register.jsp'">顾客注册</button>
        </c:if>
        <c:if test="${not empty sessionScope.user}">
            <c:if test="${sessionScope.status.equals('customer')}">
                <button onclick="location.href='CartViewServlet'">购物车</button>
            </c:if>
            <c:if test="${sessionScope.status.equals('admin')}">
                <button onclick="location.href='goods_add.jsp'">添加商品</button>
                <button onclick="location.href='SalesReportServlet'">查看商品销售报表</button>
                <button onclick="location.href='OrderViewServlet'">查看客户购买记录</button>
            </c:if>
            <button onclick="location.href='LogoutServlet'">注销</button>
        </c:if>
    </div>
</header>
<body>

</body>
</html>
