<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp"%>
<html>
<head>
    <title>新增商品页</title>
    <style>
        .form{
            position: relative;
            left: 400px;
        }
    </style>
</head>
<body>
    <p><h2 style="text-align: center">添加商品</h2>
    <div class="form">
        <form action="GoodsAddServlet" method="post" style="text-align: left;">
            <p>商品名称():<input type="text" name="goods_name" placeholder="不得与已有名称重复且不得为空,字数限制在50字以内" size="50"></p>
            <p>商品描述:<textarea type="text" name="description" rows="3" cols="60" placeholder="字数限制在1024字以内"></textarea></p>
            <p>商品价格:<input type="text" name="price" placeholder="请输入数字，数字不能小于0,空白则默认为0" size="35" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')"></p>
            <p>商品库存:<input type="text" name="stock" placeholder="请输入数字，数字不能小于0,空白则默认为0" size="35" onkeyup="this.value=this.value.replace(/^0(0+|\d+)|[^\d]+/g,'')"></p>
            <p><input type="submit" value="提交" style="margin-left:300px;">
            </p>
        </form>
    </div>
</body>
</html>
