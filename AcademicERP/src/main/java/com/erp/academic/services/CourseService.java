package com.erp.academic.services;

import com.erp.academic.bean.Course;
import com.erp.academic.bean.Domain;
import com.erp.academic.bean.Schedule;
import com.erp.academic.bean.Specialization;
import com.erp.academic.dao.impl.CourseDAOImpl;
import com.erp.academic.utils.CourseValidator;
import com.erp.academic.utils.HelperUtils;

import java.util.*;

public class CourseService {
    public List<Course> getCourses(int year, int term)throws Exception{
        CourseDAOImpl courseDao = new CourseDAOImpl();
        return courseDao.getCourse(year, term);
    }

    public List<String> validateCourse(Map<String,Object> coursedetails){
        CourseValidator validator = new CourseValidator();
        List<String> errorList = validator.validateName(coursedetails)
                .validateYear(coursedetails)
                .validateTerm(coursedetails)
                .validateCredit(coursedetails)
                .validateCapacity(coursedetails)
                .validateFaculty(coursedetails)
                .validateDomain(coursedetails)
                .validateSpecialization(coursedetails)
                .validatePrerequisites(coursedetails)
                .getErrorList();

        return errorList;
    }

    public Set<Course> getPrerequisites(List<Integer> prerequisites, Course newCourse) throws Exception {
        Set<Course> prerequisitesSet= new HashSet<Course>();
        for(Integer id : prerequisites){
            CourseDAOImpl courseDao = new CourseDAOImpl();
            Course preRequisite = courseDao.getCourseById(id);
            prerequisitesSet.add(preRequisite);
        }
        return prerequisitesSet;
    }

    public Course formCourse(Map<String, Object> coursedetails) throws Exception {
        Course newCourse = new Course();
        newCourse.setName((String)coursedetails.get("course_name"));
        newCourse.setDescription((String)coursedetails.get("course_description"));
        newCourse.setCredits(Integer.parseInt((String)coursedetails.get("course_credit")));
        newCourse.setCapacity(Integer.parseInt((String)coursedetails.get("course_capacity")));
        newCourse.setYear(Integer.parseInt((String)coursedetails.get("course_year")));
        newCourse.setTerm(Integer.parseInt((String)coursedetails.get("course_term")));
        String courseCode = getCourseCode(newCourse.getName(),newCourse.getYear());
        newCourse.setCourseCode(courseCode);

        Integer facultyId = Integer.parseInt((String)coursedetails.get("course_faculty"));
        List<Integer> domains = HelperUtils.getIntegerList((List<?>)coursedetails.get("domain_list"));
        List<Integer> prerequisites = HelperUtils.getIntegerList((List<?>)coursedetails.get("prerequisites_list"));
        List<Integer> specializations = HelperUtils.getIntegerList((List<?>)coursedetails.get("specialization_list"));
        newCourse.setFaculty(new EmployeeService().getEmployeeById(facultyId));
        newCourse.setDomains(new DomainService().getDomainsCollection(domains));
        newCourse.setSpecializationList(new SpecializationService().getSpecilizations(specializations));
        newCourse.setPreRequisite(getPrerequisites(prerequisites, newCourse));
        List<Schedule> scheduleList = new CourseScheduleService().JSONtoCourseScheduleList(
                (List<Map<String, Object>>)(List<?>)coursedetails.get("schedule_list"));
        newCourse.setScheduleList(scheduleList);
        return newCourse;
    }

    private String getCourseCode(String courseName, Integer year) {
        String headstring="";
        String name = " "+courseName;
        for(int i=1;i<name.length();i++)
        {
            char c = name.charAt(i);
            if(name.charAt(i-1)==' ' && ((c>=65 && c<=91) || (c>=97 && c<=123)))
            {
                if(c>=97) c = (char)(c - 32);
                headstring = headstring + c;
            }
        }
        String coursecode = year.toString()+"-";
        coursecode += headstring;
        return coursecode;
    }

    public void saveCourse(Course newCourse) throws Exception {
        CourseDAOImpl courseDao = new CourseDAOImpl();
        courseDao.saveCourse(newCourse);
    }

    public List<Course> getAllCourses()throws Exception{
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        return courseDAO.getallcourses();
    }
}
