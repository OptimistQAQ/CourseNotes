package com.optimist.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {

    // 全栈： 后端 + 前端 + 数据库 + 运维
    // 前端： 后台Node.js + 前端

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取前端参数
        String method = req.getParameter("method");
        if (method.equals("add")) {
            req.getSession().setAttribute("msg", "执行了add方法");
        }
        if (method.equals("delete")) {
            req.getSession().setAttribute("msg", "执行了delete方法");
        }

        //2.调用业务层

        //3.试图转发或者重定向
        req.getRequestDispatcher("/WEB-INF/jsp/test.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
