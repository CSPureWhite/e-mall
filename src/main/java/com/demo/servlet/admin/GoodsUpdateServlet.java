package com.demo.servlet.admin;

import com.demo.entity.Goods;
import com.demo.service.GoodsService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GoodsUpdateServlet", value = "/GoodsUpdateServlet")
public class GoodsUpdateServlet extends HttpServlet {
    private GoodsService goodsService =new GoodsService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //获取参数
        int goods_id=Integer.parseInt(request.getParameter("goods_id"));
        String goods_name=request.getParameter("goods_name");
        String description=request.getParameter("description");
        String price_string=request.getParameter("price");
        String stock_string=request.getParameter("stock");
        HttpSession session=request.getSession();
        String name =(String) session.getAttribute("name");
        //判断输入是否符合条件
        Goods goods=goodsService.searchById(goods_id);

        if(!goods_name.equals("")) {
            if (goodsService.searchGoods(goods_name).isEmpty()||goods_name.equals(goods.getGoods_name())) {
                goods.setGoods_name(goods_name);
            }
            else{
                PrintWriter out = response.getWriter();
                out.write("<script>");
                out.write("alert('修改失败，商品名已存在！');");
                out.write("location.href='GoodsInfoServlet?goods_id="+goods_id+"'");
                out.write("</script>");
                out.close();
            }
        }
        else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('修改失败，商品名不能为空！');");
            out.write("location.href='GoodsInfoServlet?goods_id="+goods_id+"'");
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
                out.write("alert('修改失败，价格字段不能填小于0的数字！');");
                out.write("location.href='GoodsInfoServlet?goods_id="+goods_id+"'");
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
                out.write("alert('修改失败，库存字段不能填小于0的数字！');");
                out.write("location.href='GoodsInfoServlet?goods_id="+goods_id+"'");
                out.write("</script>");
                out.close();
            }
        }
        goods.setStock(stock);

        //修改操作
        if(goodsService.updateGoods(goods)){
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('修改成功！');");
            out.write("location.href='SearchServlet?name="+name+"'");
            out.write("</script>");
            out.close();
        }
        else{
            PrintWriter out = response.getWriter();
            out.write("<script>");
            out.write("alert('修改失败！');");
            out.write("location.href='SearchServlet?name="+name+"'");
            out.write("</script>");
            out.close();
        }
    }
}
