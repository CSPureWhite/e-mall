package com.demo.filter;

import com.demo.entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 检查用户有无登录
 */
@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //设置编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //设置与登录注册相关的访问资源路径
        String[] url_list = {"login.jsp", "LoginServlet", "register.jsp", "RegisterServlet", "homepage.jsp", "HomePageServlet", "SearchServlet"};
        //获取当前访问的资源路径
        String ser_path= req.getServletPath();
        //判断当前路径是否为登录、注册、首页相关
        for (String u : url_list) {
            if (ser_path.contains(u)) {  //放行
                chain.doFilter(request, response);
                return;
            }
        }

        //当前路径与登录、注册、首页无关
        //判断session中有无user
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {  //已登录，放行
            chain.doFilter(request, response);
        }
        else {
            //没有登录，弹出提示页面并跳转至登录页面
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('您尚未登录,点击确定跳转至登陆页面！');");
            out.write("location.href='login.jsp'");
            out.write("</script>");
            out.close();
        }
    }
}
