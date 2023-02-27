<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<html>
<head>
    <title>商品信息修改页</title>
    <style>
        .form{
            position: relative;
            left: 400px;
        }
    </style>
</head>
<body>
    <p><h2 style="text-align: center">修改商品信息</h2>

    </p>
    <div class="form">
        <form action="GoodsUpdateServlet?goods_id=${requestScope.goods.goods_id}" method="post" style="text-align: left;">
            <p>商品名称(不得与已有名称重复且不得为空):<input type="text" name="goods_name" value="${requestScope.goods.goods_name}"></p>
            <p>商品描述:<textarea type="text" name="description" rows="3" cols="60">${requestScope.goods.description}</textarea></p>
            <p>商品价格(不能小于0,空白则默认为0):<input type="text" name="price" value="${requestScope.goods.price}" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')"></p>
            <p>商品库存(不能小于0,空白则默认为0):<input type="text" name="stock" value="${requestScope.goods.stock}" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')"></p>
            <p><input type="submit" value="提交" style="margin-left:300px;">
            </p>
        </form>
    </div>
    <button onclick="location.href='SearchServlet?name=${sessionScope.name}'" style="position: relative; left: 700px">返回</button>
</body>
</html>
