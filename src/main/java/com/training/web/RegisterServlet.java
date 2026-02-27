package com.training.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.training.web.config.AppConfig;
import com.training.web.dao.CustomerDao;
import com.training.web.entity.Customer;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Customer customer = new Customer();
      
        customer.setUsername(username);
        customer.setPassword(password);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerDao dao = context.getBean(CustomerDao.class);

        dao.registerCustomer(customer);

        context.close();

        request.setAttribute("msg", "Registration Successful! Please login.");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}