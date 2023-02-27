package com.demo.servlet;

import com.demo.service.CartService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 在首页添加商品至购物车
 */
@WebServlet(name = "HomePage_CartAddServlet", value = "/HomePage_CartAddServlet")
public class HomePage_CartAddServlet extends HttpServlet {
    private CartService cartService =new CartService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取请求参数
        int customer_id = Integer.parseInt(request.getParameter("customer_id"));
        int goods_id=Integer.parseInt(request.getParameter("goods_id"));
        String name = request.getParameter("name");
        // add操作
        boolean success =cartService.add(customer_id,goods_id);
        PrintWriter out = response.getWriter();
        if(success){
            out.write("<script>");
            out.write("alert('添加成功！');");
            out.write("location.href='SearchServlet?name="+name+"'");
            out.write("</script>");
            out.close();
        }
        else{
            out.write("<script>");
            out.write("alert('添加失败！');");
            out.write("location.href='SearchServlet?name="+name+"'");
            out.write("</script>");
            out.close();
        }
    }
}
