package com.training.web;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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
import com.training.web.entity.Policy;
import com.training.web.entity.Purchase;

@WebServlet("/purchase")
public class PurchaseServlet extends HttpServlet {

    // View purchase history
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("loggedUser");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerDao dao = context.getBean(CustomerDao.class);

        List<Purchase> purchaseList = dao.getPurchasesByUsername(username);

        context.close();

        request.setAttribute("purchaseList", purchaseList);
        request.getRequestDispatcher("purchaseHistory.jsp").forward(request, response);
    }

    // Buy a policy
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("loggedUser");
        Long policyId   = Long.parseLong(request.getParameter("policyId"));

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CustomerDao dao = context.getBean(CustomerDao.class);

        Customer customer = dao.getCustomerByUsername(username);
        Policy   policy   = dao.getPolicyById(policyId);

        Purchase purchase = new Purchase();
        purchase.setCustomer(customer);
        purchase.setPolicy(policy);
        purchase.setPurchaseDate(LocalDate.now());

        dao.savePurchase(purchase);

        context.close();

        request.setAttribute("msg", "Policy Purchased Successfully!");
        request.getRequestDispatcher("purchaseSuccess.jsp").forward(request, response);
    }
}