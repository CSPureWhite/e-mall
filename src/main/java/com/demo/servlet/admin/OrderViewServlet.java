package com.demo.servlet.admin;

import com.demo.entity.Goods;
import com.demo.entity.Orders;
import com.demo.service.OrdersService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderViewServlet", value = "/OrderViewServlet")
public class OrderViewServlet extends HttpServlet {
    private OrdersService ordersService=new OrdersService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取参数
        String id = request.getParameter("customer_id");
        List<Orders> ordersList = new ArrayList<>();

        if(id==null||id.equals("")){      //不输入id则展示所有购买记录
            ordersList = ordersService.showAll();
        }
        else{
            int customer_id=Integer.parseInt(request.getParameter("customer_id"));
            ordersList = ordersService.searchByCustomerId(customer_id);
        }
        request.setAttribute("list",ordersList);
        request.getRequestDispatcher("search_order.jsp").forward(request,response);
    }
}
