package com.example.demo.config;

import jakarta.servlet.ServletRegistration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class ServletConfig {

   @Bean
   public ServletRegistrationBean<HttpServlet> helloServlet() {
       return new ServletRegistrationBean<>(new HttpServlet() {
           @Override
           protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws java.io.IOException {
               resp.getWriter().write("Hello from simple servlet");
           }
       }, "/hello-servlet");
   }
}
