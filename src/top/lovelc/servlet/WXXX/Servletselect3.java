package top.lovelc.servlet.WXXX;

import top.lovelc.test.Login;
import top.lovelc.test.WXXX.Select3;
import top.lovelc.util.Time;
import top.lovelc.www.SJXX;
import top.lovelc.www.WXXX;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Servletselect3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("*************试图下载表格*************");
        System.out.println(Time.t());
        System.out.println("请求方式->"+req.getMethod());
        req.setCharacterEncoding("UTF-8");
        String type = req.getParameter("type");
        String select = req.getParameter("select");
        String ID = req.getParameter("id");
        System.out.println("请求ID："+ID);
        System.out.println("查询类型："+type);
        System.out.println("查询内容："+select);

        Login li = new Login();
        ArrayList<String> l = li.userloginid(ID);
        if (l.size() < 1){
            System.out.println("登陆失败");
            resp.sendRedirect("http://localhost:8080/QYCLGL/error.html");
        }else {
            Select3 se = new Select3(type,select);
            ArrayList<WXXX> s = se.query();
            System.out.println(s);
            if (s.size() <1){
                System.out.println("查不到数据！");
                req.setAttribute("l",l);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/admin3.jsp");
                requestDispatcher.forward(req,resp);
            }else {
                System.out.println("查询到数据");
                req.setAttribute("type",type);
                req.setAttribute("select",select);
                req.setAttribute("l",l);
                req.setAttribute("s",s);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/excel3.jsp");
                requestDispatcher.forward(req,resp);
                System.out.println("*************下载表格成功*************");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("*************试图查询数据*************");
        System.out.println(Time.t());
        System.out.println("请求方式->"+request.getMethod());
        request.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        String select = request.getParameter("select");
        String ID = request.getParameter("id");
        System.out.println("请求ID："+ID);
        System.out.println("查询类型："+type);
        System.out.println("查询内容："+select);

        Login li = new Login();
        ArrayList<String> l = li.userloginid(ID);
        if (l.size() < 1){
            System.out.println("登陆失败");
            response.sendRedirect("http://localhost:8080/QYCLGL/error.html");
        }else {
            Select3 se = new Select3(type,select);
            ArrayList<WXXX> s = se.query();
            System.out.println(s);
            if (s.size() <1){
                System.out.println("查不到数据！");
                request.setAttribute("l",l);
                String cx = "查询";
                request.setAttribute("cx",cx);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin3.jsp");
                requestDispatcher.forward(request,response);
            }else {
                System.out.println("查询到数据");
                request.setAttribute("type",type);
                request.setAttribute("select",select);
                request.setAttribute("l",l);
                request.setAttribute("s",s);
                String cx = "查询";
                request.setAttribute("cx",cx);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/admin3.jsp");
                requestDispatcher.forward(request,response);
                System.out.println("*************数据查询成功*************");
            }
        }
    }
}
