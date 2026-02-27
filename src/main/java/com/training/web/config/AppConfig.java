package com.training.web.config;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.training.web.dao.CustomerDao;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.training.web") // ⭐ Component Scan
public class AppConfig {

    // DataSource
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource ds =
                new DriverManagerDataSource();

        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUrl("jdbc:postgresql://localhost:5433/insurance");
        ds.setUsername("postgres");
        ds.setPassword("tiger");

        return ds;
    }

    
    // EntityManagerFactory
    @Bean
    public EntityManagerFactory entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(dataSource());

        factory.setPackagesToScan(
                "com.training.web.entity");

        HibernateJpaVendorAdapter adapter =
                new HibernateJpaVendorAdapter();

        factory.setJpaVendorAdapter(adapter);

        Properties props = new Properties();

        props.put(
                "hibernate.dialect",
                "org.hibernate.dialect.PostgreSQLDialect");

        props.put(
                "hibernate.hbm2ddl.auto",
                "update");

        props.put(
                "hibernate.show_sql",
                "true");

        factory.setJpaProperties(props);
        factory.afterPropertiesSet();
        return factory.getObject();
    }


    // EntityManager Bean
    @Bean
    public EntityManager entityManager() {

        return entityManagerFactory().createEntityManager();
    }


    // Transaction Manager
    @Bean
    public JpaTransactionManager transactionManager() {

        JpaTransactionManager txManager =
                new JpaTransactionManager();

        txManager.setEntityManagerFactory(
                entityManagerFactory());

        return txManager;
    }



    @Bean
    public CustomerDao userDao(EntityManagerFactory emf) {
        return new CustomerDao(emf);
    }

}