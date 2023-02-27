package com.demo.servlet;

import com.demo.entity.User;
import com.demo.service.CartService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CartDelServlet", value = "/CartDelServlet")
public class CartDelServlet extends HttpServlet {
    private CartService cartService=new CartService();
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
        int cart_id = Integer.parseInt(request.getParameter("cart_id"));
        // del操作
        boolean success =cartService.delete(cart_id);
        PrintWriter out = response.getWriter();
        if(success){
            out.write("<script>");
            out.write("alert('删减成功！');");
            out.write("location.href='CartViewServlet?customer_id="+customer_id+"'");
            out.write("</script>");
            out.close();
        }
        else{
            out.write("<script>");
            out.write("alert('删减失败！');");
            out.write("location.href='CartViewServlet?customer_id="+customer_id+"'");
            out.write("</script>");
            out.close();
        }
    }
}
