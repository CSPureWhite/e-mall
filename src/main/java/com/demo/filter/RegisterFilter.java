package com.demo.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注册页面 输入过滤器
 */
@WebFilter(filterName = "RegisterFilter", urlPatterns = "/RegisterServlet")
public class RegisterFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        PrintWriter out = resp.getWriter();
        //设置编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String re_password = req.getParameter("re_password");
        System.out.println(username);
        //判断用户名和密码是否为空
        if(username.equals("")){
            out.write("<script>");
            out.write("alert('用户名称不能为空');");
            out.write("location.href='register.jsp';");
            out.write("</script>");
            out.close();
            return;
        }
        //判断密码是否为空
        if(password.equals("")){
            out.write("<script>");
            out.write("alert('密码不能为空');");
            out.write("location.href='register.jsp';");
            out.write("</script>");
            out.close();
            return;
        }
        //判断两次输入的密码是否一致
        if(!password.equals(re_password)){
            out.write("<script>");
            out.write("alert('两次输入的密码不一致');");
            out.write("location.href='register.jsp';");
            out.write("</script>");
            out.close();
            return;
        }
        //无非法输入则放行
        chain.doFilter(request, response);
    }
}
