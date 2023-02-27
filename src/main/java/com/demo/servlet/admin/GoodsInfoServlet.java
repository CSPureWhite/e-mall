package com.demo.servlet.admin;

import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "GoodsInfoServlet", value = "/GoodsInfoServlet")
public class GoodsInfoServlet extends HttpServlet {
    private GoodsService goodsService = new GoodsService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取参数
        int goods_id=Integer.parseInt(request.getParameter("goods_id"));
        //获取商品信息
        Goods goods=goodsService.searchById(goods_id);
        //传递商品信息
        request.setAttribute("goods",goods);
        request.getRequestDispatcher("goods_update.jsp").forward(request,response);
    }
}
