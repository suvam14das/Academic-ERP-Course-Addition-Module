package com.erp.academic.controller;

import com.erp.academic.bean.Course;
import com.erp.academic.bean.Schedule;
import com.erp.academic.bean.Specialization;
import com.erp.academic.services.*;
import com.erp.academic.utils.CourseValidator;

import javax.print.URIException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("courses")
public class CourseController {

    @GET
    @Path("/initconf")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateInitCourseData(){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            DomainService domainService = new DomainService();
            SpecializationService specializationService = new SpecializationService();
            EmployeeService employeeServive = new EmployeeService();
            map.put("domain_list", domainService.getAllDomain());
            map.put("specialization_list", specializationService.getAllSpecialization());
            map.put("faculty_list", employeeServive.getAllFaculty());
            return Response.ok().entity(map).build();
        }catch(Exception e){
            System.err.println(e);
            e.printStackTrace();
            List<String> errorList = new ArrayList<String>();
            errorList.add("Unable to fetch Existing Course Faculty, Domains and Course Specialization");
            map.put("error_list",errorList);
            return Response.ok().entity(map).build();
        }
    }

    @GET
    @Path("/{courseId}/year/{year}/term/{term}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrequisites(@PathParam("year") int year, @PathParam("term") int term){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            CourseService courseService = new CourseService();
            List<Course> courseList = courseService.getCourses(year,term);
            map.put("courses",courseList);
            return Response.ok().entity(map).build();
        }catch(Exception e){
            System.err.println(e);
            e.printStackTrace();
            List<String> errorList = new ArrayList<String>();
            errorList.add("Unable to get Courses for year = "+year+" and term = "+term);
            map.put("error_list",errorList);
            return Response.ok().entity(map).build();
        }
    }

    @POST
    @Path("/submit")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response submitCourse(Map<String,Object> coursedetails){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CourseService courseService = new CourseService();
            List<String> errorList = courseService.validateCourse(coursedetails);
            if(errorList != null && errorList.size()>0){
                map.put("error_list",errorList);
                return Response.ok().entity(map).build();
            }
            Course newCourse = courseService.formCourse(coursedetails);
            courseService.saveCourse(newCourse);
            map.put("course_code", newCourse.getCourseCode());
            return Response.ok().entity(map).build();
        }catch(Exception e){
            System.err.println(e);
            e.printStackTrace();
            List<String> errorList = new ArrayList<String>();
            errorList.add("Error on saving the New Course");
            map.put("error_list",errorList);
            return Response.ok().entity(map).build();
        }
    }

    @Path("/allcourses")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCourses() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            CourseService courseService = new CourseService();
            List<Course> allCourses = courseService.getAllCourses();
            map.put("course_list", allCourses);
            return Response.ok().entity(map).build();
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
            List errorList = new ArrayList<String>();
            errorList.add("Unable to fetch all Courses");
            map.put("error_list",errorList);
            return Response.ok().entity(map).build();
        }
    }

}
