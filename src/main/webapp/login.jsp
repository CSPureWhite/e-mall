<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商城登录界面</title>
</head>
<body>
<a href="homepage.jsp"><input type="submit" value="返回首页" ></a>
<div style="text-align: center">
    <br><br>
    <h2>&ensp;&ensp;&ensp;登录</h2>
    <form name="user" action="LoginServlet" method="post">
        <div style="text-align: right;display: inline-block;">
            用户名: <input type="text" name="username">
            <br><br>
            密&emsp;码: <input type="password" name="password">
            <br><br>
            <input type="radio" name="status" value="customer" checked>顾客登录
            <input type="radio" name="status" value="admin">管理员登录
            <br>
            <p><input type="submit" value="登录" style="margin-right:90px;"></p>
            <p>没有账号？<a href="register.jsp">请点此注册</a></p>
        </div>
        <br><br>
    </form>
</div>
</body>
</html>
