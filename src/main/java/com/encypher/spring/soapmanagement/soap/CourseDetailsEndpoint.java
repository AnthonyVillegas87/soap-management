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

import java.util.List;

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

        Course course = service.findById(request.getId());

        return getResponse(course);
    }



    @PayloadRoot(namespace = "http://www.example.org/courses", localPart = "GetAllCourseDetailsRequest")
    @ResponsePayload
    // The output is the get course details response
    public GetAllCourseDetailsResponse processAllCourseDetailsRequest(@RequestPayload GetAllCourseDetailsRequest request) {

       List<Course> courses = service.findAll();

        return getResponse(courses);
    }





    // This method is responsible for creating details, setting the values, & returning it back ^^
    private GetCourseDetailsResponse getResponse(Course course) {
        GetCourseDetailsResponse response = new GetCourseDetailsResponse();

        CourseDetails courseDetails = new CourseDetails();


        courseDetails.setId(course.getId());

        courseDetails.setName(course.getName());

        courseDetails.setDescription(course.getDescription());

        response.setCourseDetails(courseDetails);
        return response;
    }


}
