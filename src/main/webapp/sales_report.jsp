<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>销售报表页</title>
    <style>
        .search{
            position: relative;
            left: 550px;
        }
        .table{
        position: relative;
        left: 520px;
        }
    </style>
</head>
<body>
    <p><h2 style="text-align: center">查询商品销售报表</h2>
    <div class="search">
        <form name="input" action="SalesReportServlet" method="post">
            <input type="text" name="goods_id" placeholder="请输入商品id" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')">
            <input type="submit" value="搜索商品销售报表">
        </form>
    </div>
    <div class="table">
        <table border="2">
            <tr>
                <td>商品id</td>
                <td>商品名称</td>
                <td>单价</td>
                <td>销量</td>
            </tr>
            <c:forEach items="${list}" var="goods">
                <tr>
                    <td>${goods.goods_id}</td>
                    <td>${goods.goods_name}</td>
                    <td>${goods.price}元</td>
                    <td>${goods.sales_volume}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
