package com.demo.servlet;

import com.demo.entity.CartInfo;
import com.demo.entity.Orders;
import com.demo.entity.User;
import com.demo.service.CartService;
import com.demo.service.OrdersService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "PlaceOrderServlet", value = "/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
    private CartService cartService=new CartService();
    private OrdersService ordersService = new OrdersService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取顾客id
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        //获取成功下单的商品列表
        List<CartInfo> cartInfoList=ordersService.place_order(user.getUser_id());
        if(cartInfoList.isEmpty()){
            PrintWriter out = response.getWriter();

            out.write("<script>");
            out.write("alert('购物车中所有商品的数量均超过库存，下单失败！');");
            out.write("location.href='CartViewServlet'");
            out.write("</script>");
            out.close();
        }
        //将列表放入request中
        request.setAttribute("list",cartInfoList);
        //获取合计金额
        double total_amount= cartService.TotalAmount(cartInfoList);
        request.setAttribute("total_amount",total_amount);
        //跳转
        request.getRequestDispatcher("order_success.jsp").forward(request,response);
    }
}
