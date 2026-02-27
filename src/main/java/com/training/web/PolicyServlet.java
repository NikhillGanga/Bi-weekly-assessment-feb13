//package com.training.web;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import com.training.web.config.AppConfig;
//import com.training.web.dao.CustomerDao;
//import com.training.web.entity.Policy;
//
//@WebServlet("/addPolicy")
//public class PolicyServlet extends HttpServlet {
//
//    // View all policies
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        CustomerDao dao = context.getBean(CustomerDao.class);
//
//        List<Policy> policyList = dao.getAllPolicies();
//
//        context.close();
//
//        request.setAttribute("policyList", policyList);
//        request.getRequestDispatcher("addPolicy.jsp").forward(request, response);
//    }
//
//    // Add a new policy
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String policyName    = request.getParameter("policyName");
//        String description   = request.getParameter("description");
//        double premiumAmount = Double.parseDouble(request.getParameter("premiumAmount"));
//        int    duration      = Integer.parseInt(request.getParameter("duration"));   // matches entity field
//
//        Policy policy = new Policy();
//        policy.setPolicyName(policyName);
//        policy.setDescription(description);
//        policy.setPremiumAmount(premiumAmount);
//        policy.setDuration(duration);
//
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//
//        CustomerDao dao = context.getBean(CustomerDao.class);
//
//        dao.addPolicy(policy);
//
//        context.close();
//
//        request.setAttribute("msg", "Policy Added Successfully!");
//        request.getRequestDispatcher("addPolicy.jsp").forward(request, response);
//    }
//}