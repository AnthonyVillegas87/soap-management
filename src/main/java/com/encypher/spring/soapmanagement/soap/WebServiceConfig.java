package com.encypher.spring.soapmanagement.soap;


import jakarta.servlet.Servlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

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
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext context) {
        MessageDispatcherServlet messageDispatcherServlet = new MessageDispatcherServlet();

        messageDispatcherServlet.setApplicationContext(context);
        messageDispatcherServlet.setTransformWsdlLocations(true);

        return  new ServletRegistrationBean(messageDispatcherServlet, "/ws/*");
    }


    //  ->  /ws/courses.wsdl
    @Bean(name="courses")
    public DefaultWsdl11Definition defaultWsdl11Definition (XsdSchema coursesSchema) {

        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        // PortType - CoursePort
        definition.setPortTypeName("CoursePort");
        // Namespace - https://www.example.org/courses
        definition.setTargetNamespace("https://www.example.org/courses");
        definition.setLocationUri("/ws");
        definition.setSchema(coursesSchema);
        return definition;

    }


    // course-details.xsd
    @Bean
    public XsdSchema coursesSchema() {
        return new SimpleXsdSchema(new ClassPathResource("course-details.xsd"));
    }

}
