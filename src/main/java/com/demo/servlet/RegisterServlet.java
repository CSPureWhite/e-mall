package com.demo.servlet;

import com.demo.entity.User;
import com.demo.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String status = request.getParameter("status");
        //设定表名
        userService.setTablename(status);
        //注册操作
        boolean success = userService.register(new User(username,password,email));
        //判断注册结果跳转
        if(success&&!username.equals("")){
            response.getWriter().write("注册成功！即将跳转至登录页面......");
            response.setHeader("refresh","3,url=/e-mall/login.jsp");
        }
        else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('用户名已存在！');");
            out.write("location.href='register.jsp'");
            out.write("</script>");
            out.close();
        }
    }
}
