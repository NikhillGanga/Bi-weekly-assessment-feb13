package com.training.web.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.training.web.entity.Admin;
import com.training.web.entity.Customer;
import com.training.web.entity.Policy;
import com.training.web.entity.Purchase;

public class CustomerDao {

    private EntityManager em;
    private EntityManagerFactory emf;

    public CustomerDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

   //created this method for admin login.

    public Admin checkAdminLogin(String username, String password) {
        em = emf.createEntityManager();
        TypedQuery<Admin> query = em.createQuery(
                "SELECT a FROM Admin a WHERE a.username = :un AND a.password = :pw",
                Admin.class);
        query.setParameter("un", username);
        query.setParameter("pw", password);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

  //created this method for user login.
    public void registerCustomer(Customer user) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        System.out.println("CustomerDao.registerCustomer() completed");
    }

    public Customer checkLogin(String username, String password) {
        em = emf.createEntityManager();
        TypedQuery<Customer> query = em.createQuery(
                "SELECT u FROM Customer u WHERE u.username = :un AND u.password = :pw",
                Customer.class);
        query.setParameter("un", username);
        query.setParameter("pw", password);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Customer getCustomerByUsername(String username) {
        em = emf.createEntityManager();
        TypedQuery<Customer> query = em.createQuery(
                "SELECT u FROM Customer u WHERE u.username = :un",
                Customer.class);
        query.setParameter("un", username);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }


    
    public void addPolicy(Policy policy) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(policy);
        em.getTransaction().commit();
        System.out.println("CustomerDao.addPolicy() completed");
    }

    public void deletePolicy(Long id) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Policy policy = em.find(Policy.class, id);
        if (policy != null) {
            em.remove(policy);
            System.out.println("CustomerDao.deletePolicy() completed for id=" + id);
        }
        em.getTransaction().commit();
    }

    public List<Policy> getAllPolicies() {
        em = emf.createEntityManager();
        TypedQuery<Policy> query = em.createQuery("SELECT p FROM Policy p", Policy.class);
        return query.getResultList();
    }

    public Policy getPolicyById(Long id) {
        em = emf.createEntityManager();
        return em.find(Policy.class, id);
    }

   
    public void savePurchase(Purchase purchase) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(purchase);
        em.getTransaction().commit();
        System.out.println("CustomerDao.savePurchase() completed");
    }
//for purchase history
    public List<Purchase> getPurchasesByUsername(String username) {
        em = emf.createEntityManager();
        TypedQuery<Purchase> query = em.createQuery(
                "SELECT p FROM Purchase p WHERE p.customer.username = :un",
                Purchase.class);
        query.setParameter("un", username);
        return query.getResultList();
    }


}