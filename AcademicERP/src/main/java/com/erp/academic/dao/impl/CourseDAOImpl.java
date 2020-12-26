package com.erp.academic.dao.impl;

import com.erp.academic.bean.Course;
import com.erp.academic.bean.Domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDAOImpl {

    private CommonPurposeDAOImpl commonPurposeDAO= new CommonPurposeDAOImpl();

    public List<Course> getCourse(int year, int term)throws Exception{
        String sql = "from Course where year = :year and term = :term";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("year",year);
        map.put("term",term);
        List<Course> courseList = (List<Course>)(List<?>)commonPurposeDAO.qryWithInputs(sql,map);
        return courseList;
    }

    public Course getCourseById(Integer id) throws Exception {
        String sql = "from Course where course_id = :id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        Course course = (Course)(commonPurposeDAO.qryWithInputs(sql,map).get(0));
        return course;
    }

    public void saveCourse(Course newCourse) throws Exception {
        commonPurposeDAO.save(newCourse);
    }

    public List<Course> getallcourses() throws Exception {
        List<Course> courseList = (List<Course>)(List<?>)commonPurposeDAO.fetchAll("Course");
        return courseList;
    }
}
