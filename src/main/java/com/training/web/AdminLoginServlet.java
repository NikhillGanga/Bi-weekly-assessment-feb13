package com.training.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.training.web.config.AppConfig;
import com.training.web.dao.CustomerDao;
import com.training.web.entity.Admin;

@WebServlet("/adminLogin")
public class AdminLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerDao dao = context.getBean(CustomerDao.class);

        Admin admin = dao.checkAdminLogin(username, password);

        context.close();

        if (admin != null) {
            HttpSession session = request.getSession();
            session.setAttribute("adminUser", admin.getUsername());

            response.sendRedirect("adminDashboard");
        } else {
            request.setAttribute("msg", "Invalid Admin Credentials!");
            request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
        }
    }
}