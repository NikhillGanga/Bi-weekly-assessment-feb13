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
import com.training.web.entity.Policy;

@WebServlet("/addPolicy")
public class AddPolicyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       

        String policyName    = request.getParameter("policyName");
        String description   = request.getParameter("description");
        double premiumAmount = Double.parseDouble(request.getParameter("premiumAmount"));
        int    duration      = Integer.parseInt(request.getParameter("duration"));

        Policy policy = new Policy();
        policy.setPolicyName(policyName);
        policy.setDescription(description);
        policy.setPremiumAmount(premiumAmount);
        policy.setDuration(duration);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerDao dao = context.getBean(CustomerDao.class);

        dao.addPolicy(policy);

        context.close();

        response.sendRedirect("adminDashboard?msg=Policy+Added+Successfully");
    }
}