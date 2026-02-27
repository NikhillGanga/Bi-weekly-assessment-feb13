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
import com.training.web.entity.Customer;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerDao dao = context.getBean(CustomerDao.class);

        Customer user = dao.checkLogin(username, password);

        context.close();

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedUser", user.getUsername());
            
            System.out.println("Login successful for user: " + user.getUsername());
            request.setAttribute("greet", user.getUsername());
            request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
        } else {
            request.setAttribute("msg", "Invalid Username or Password!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}