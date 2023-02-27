package com.demo.servlet;

import com.demo.entity.User;
import com.demo.service.UserService;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String status = request.getParameter("status");
        //设定表名
        userService.setTablename(status);
        //登录操作
        User user = userService.login(username, password);
        //判断登录结果跳转
        if (user != null) { //登录成功
            HttpSession session = request.getSession();
            session.setAttribute("user", user);  //将用户信息传入至session中
            session.setAttribute("status", status); //将用户身份表示传入至session中
            response.sendRedirect("/e-mall/homepage.jsp");
        } else {            //登录失败
            PrintWriter out = response.getWriter();

            out.write("<script>");
            out.write("alert('用户名或密码错误！');");
            out.write("location.href='login.jsp'");
            out.write("</script>");
            out.close();
        }
    }
}
