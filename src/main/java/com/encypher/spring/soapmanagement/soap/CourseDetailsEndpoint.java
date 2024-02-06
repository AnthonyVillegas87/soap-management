package com.encypher.spring.soapmanagement.soap;

import com.encypher.spring.soapmanagement.soap.service.CourseDetailsService;
import org.example.courses.CourseDetails;
import org.example.courses.GetCourseDetailsRequest;
import org.example.courses.GetCourseDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;
    // method
    // input - request object (GetCourseDetailsRequest)
    // output - response object (GetCourseDetailsResponse)


    // Create a method which is accepting get course details request as the input

    // Configured to accept requests of our namespace to convert from XML to Java
    @PayloadRoot(namespace = "http://www.example.org/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
        // The output is the get course details response
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {

        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        CourseDetails courseDetails = new CourseDetails();
        courseDetails.setId(request.getId());
        courseDetails.setName("Microservices Course");
        courseDetails.setDescription("independently deployable services that communicate w/ each other through well defined API's");


        response.setCourseDetails(courseDetails);
        return response;
    }



}
