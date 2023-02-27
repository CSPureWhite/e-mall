package com.demo.servlet;

import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    GoodsService goodsService =new GoodsService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取请求参数
        String name=request.getParameter("name");
        HttpSession session=request.getSession();
        session.setAttribute("name",name); //记录当前输入框中的名字
        //返回结果
        List<Goods> goodsList = goodsService.searchGoods(name);
        request.setAttribute("list",goodsList);
        request.getRequestDispatcher("homepage.jsp").forward(request,response);
    }
}
