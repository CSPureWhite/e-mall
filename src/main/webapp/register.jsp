<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>商城注册页面</title>
</head>
<body>
<a href="homepage.jsp"><input type="submit" value="返回首页" ></a>
<div style="text-align: center">
  <br><br>
  <h2>&emsp;&emsp;&ensp;注册 </h2>
  <form name="user" action="RegisterServlet" method="post">
    <div style="text-align: right;display: inline-block;">
      <p>用户名: <input type="text" name="username" placeholder="长度为1-20个字符"></p>
      <p>电子邮箱: <input type="text" name="email"></p>
      <p>密码: <input type="password" name="password" placeholder="长度为1-20个字符"></p>
      <p>请确认密码: <input type="password" name="re_password" placeholder="长度为1-20个字符"></p>
      <input type="radio" name="status" value="customer" checked>顾客注册
      <input type="radio" name="status" value="admin">管理员注册
      <br>
      <p><input type="submit" value="注册" style="margin-right:95px;"></p>
      <p>已有账号？<a href="login.jsp">请点此登录</a>&emsp;</p>
    </div>
  </form>
</div>
</body>
</html>
