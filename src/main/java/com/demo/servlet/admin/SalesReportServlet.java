package com.demo.servlet.admin;

import com.demo.entity.Goods;
import com.demo.entity.Orders;
import com.demo.service.GoodsService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SalesReportServlet", value = "/SalesReportServlet")
public class SalesReportServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取参数
        String id_string = request.getParameter("goods_id");
        //获取包含销量的商品列表
        List<Goods> goodsList = new ArrayList<>();
        int goods_id;
        if(id_string==null||id_string.equals("")){  //查询所有商品的销量
            goods_id=0;
        }
        else {                                     //查询所选商品的销量
            goods_id=Integer.parseInt(id_string);
        }
        goodsList=goodsService.getSales(goods_id);
        request.setAttribute("list",goodsList);
        request.getRequestDispatcher("sales_report.jsp").forward(request,response);
    }
}
