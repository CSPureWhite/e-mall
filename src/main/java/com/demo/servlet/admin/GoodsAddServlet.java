package com.demo.servlet.admin;

import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GoodsAddServlet", value = "/GoodsAddServlet")
public class GoodsAddServlet extends HttpServlet {
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
        String goods_name=request.getParameter("goods_name");
        String description=request.getParameter("description");
        String price_string=request.getParameter("price");
        String stock_string=request.getParameter("stock");
        HttpSession session=request.getSession();

        Goods goods = new Goods();
        //判断输入条件
        if(!goods_name.equals("")) {
            if (goodsService.searchGoods(goods_name).isEmpty()) {
                goods.setGoods_name(goods_name);
            } else {
                PrintWriter out = response.getWriter();
                out.write("<script>");
                out.write("alert('提交失败，商品名已存在！');");
                out.write("location.href='goods_add.jsp'");
                out.write("</script>");
                out.close();
            }
        }
        else {
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('提交失败，商品名不能为空！');");
            out.write("location.href='goods_add.jsp'");
            out.write("</script>");
            out.close();
        }

        goods.setDescription(description);

        double price=0;
        if (!price_string.equals("")){
            price=(Double.parseDouble(price_string));
            if(price<0){
                PrintWriter out = response.getWriter();
                out.write("<script>");
                out.write("alert('提交失败，价格不能小于0！');");
                out.write("location.href='goods_add.jsp'");
                out.write("</script>");
                out.close();
            }
        }
        goods.setPrice(price);

        int stock=0;
        if(!stock_string.equals("")){
            stock=Integer.parseInt(stock_string);
            if(stock<0){
                PrintWriter out = response.getWriter();
                out.write("<script>");
                out.write("alert('提交失败，库存不能小于0！');");
                out.write("location.href='goods_add.jsp'");
                out.write("</script>");
                out.close();
            }
        }
        goods.setStock(stock);

        //添加操作
        if(goodsService.addGoods(goods)){
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('提交成功！');");
            out.write("location.href='homepage.jsp'");
            out.write("</script>");
            out.close();
        }
        else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('提交失败！');");
            out.write("location.href='homepage.jsp'");
            out.write("</script>");
            out.close();
        }
    }
}
