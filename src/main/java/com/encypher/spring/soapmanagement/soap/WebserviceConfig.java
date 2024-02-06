package com.encypher.spring.soapmanagement.soap;


import jakarta.servlet.Servlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

// Goal :
// Enable Spring Web Services
@EnableWs
// Spring Configuration
@Configuration
public class WebServiceConfig {
    // Configure MessageDispatcherServlet
        // Pass in a Spring application context

    // Configure url -> /ws/*

    @Bean
    ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();

        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        return  new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }

}
