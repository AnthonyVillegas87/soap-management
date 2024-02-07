package com.encypher.spring.soapmanagement.soap;

import com.encypher.spring.soapmanagement.soap.service.CourseDetailsService;
import org.example.courses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class CourseDetailsEndpoint {

    @Autowired
    CourseDetailsService service;


    // Objective:
    // input - request object (GetCourseDetailsRequest)
    // output - response object (GetCourseDetailsResponse)




    // Create a method which is accepting get course details request as the input
    // Configured to accept requests of our namespace to convert from XML to Java
    @PayloadRoot(namespace = "http://www.example.org/courses", localPart = "GetCourseDetailsRequest")
    @ResponsePayload
        // The output is the GetCourseDetailsRequest
    public GetCourseDetailsResponse processCourseDetailsRequest(@RequestPayload GetCourseDetailsRequest request) {
        Course course = service.findById(request.getId());
        return getCourseDetailsResponse(course);
    }

    private GetCourseDetailsResponse getCourseDetailsResponse(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();
        response.setCourseDetails(getDetails(course));
        return response;
    }

    @PayloadRoot(namespace = "http://www.example.org/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    // The output is the get ALL course details response
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {
        List<Course> courses = service.findAll();
        return getAllCourseDetails(courses);
    }


    private GetAllCourseDetailsResponse getAllCourseDetails(List<Course> courses) {
        GetAllCourseDetailsResponse response = new GetAllCourseDetailsResponse();
        for (Course course : courses) {
            CourseDetails getCourse = getDetails(course);
            response.getCourseDetails().add(getCourse);
        }

        return response;
    }




    private CourseDetails getDetails(Course course) {
        CourseDetails courseDetails = new CourseDetails();

        courseDetails.setId(course.getId());

        courseDetails.setName(course.getName());

        courseDetails.setDescription(course.getDescription());
        return courseDetails;
    }










}
