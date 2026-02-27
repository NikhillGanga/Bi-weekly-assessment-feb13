package com.training.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;


@WebFilter("/LoggingFilter12")
public class LoggingFilter12 extends HttpFilter implements Filter {

    public LoggingFilter12() {
        super();
        
    }

    public void destroy() {
        
    }

    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        
        System.out.println("..........................Enters in the doFilter..........................");
        

        chain.doFilter(request, response);
    }

    
    public void init(FilterConfig fConfig) throws ServletException {
        System.out.println("..........doInit() is running here..........");
    }

}