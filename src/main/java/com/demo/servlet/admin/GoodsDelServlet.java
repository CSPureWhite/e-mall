package com.demo.servlet.admin;

import com.demo.service.GoodsService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GoodsDelServlet", value = "/GoodsDelServlet")
public class GoodsDelServlet extends HttpServlet {
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
        int goods_id=Integer.parseInt(request.getParameter("goods_id"));
        HttpSession session=request.getSession();
        String name =(String) session.getAttribute("name");
        //删除操作
        boolean success =goodsService.deleteGoods(goods_id);
        PrintWriter out = response.getWriter();
        if(success){
            out.write("<script>");
            out.write("alert('删除成功！');");
            out.write("location.href='SearchServlet?name="+name+"'");
            out.write("</script>");
            out.close();
        }
        else{
            out.write("<script>");
            out.write("alert('删除失败！');");
            out.write("location.href='SearchServlet?name="+name+"'");
            out.write("</script>");
            out.close();
        }
    }
}
