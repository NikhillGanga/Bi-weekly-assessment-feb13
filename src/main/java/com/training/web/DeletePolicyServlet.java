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

@WebServlet("/deletePolicy")
public class DeletePolicyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

   

        Long policyId = Long.parseLong(request.getParameter("policyId"));

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerDao dao = context.getBean(CustomerDao.class);

        dao.deletePolicy(policyId);

        context.close();

        response.sendRedirect("adminDashboard?msg=Policy+Deleted+Successfully");
    }
}