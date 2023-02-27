package com.demo.servlet;

import com.demo.entity.CartInfo;
import com.demo.entity.User;
import com.demo.service.CartService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "CartViewServlet", value = "/CartViewServlet")
public class CartViewServlet extends HttpServlet {
    private CartService cartService=new CartService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取用户id
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
        int customer_id = user.getUser_id();
        //查询该用户购物车中的所有商品
        List<CartInfo> cartInfoList=cartService.showAllItems(customer_id);
        //计算合计金额
        double total_amount=cartService.TotalAmount(cartInfoList);
        request.setAttribute("list",cartInfoList);
        request.setAttribute("total_amount",total_amount);
        request.getRequestDispatcher("cart.jsp").forward(request,response);
    }
}
