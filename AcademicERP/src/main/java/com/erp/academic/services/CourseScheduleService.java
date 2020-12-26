package com.erp.academic.services;

import com.erp.academic.bean.Course;
import com.erp.academic.bean.Schedule;
import com.erp.academic.dao.impl.ScheduleDAOImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseScheduleService {

    public List<Schedule> JSONtoCourseScheduleList(List<Map<String, Object>> jsonList){
        List<Schedule> list = new ArrayList<Schedule>();
        for(Map json : jsonList) {
            Schedule courseSchedule = new Schedule();
            courseSchedule.setTime((String)json.get("time"));
            courseSchedule.setBuilding((String)json.get("building"));
            courseSchedule.setDay((String)json.get("day"));
            courseSchedule.setRoom((String)json.get("room"));
            list.add(courseSchedule);
        }
        return list;
    }

    /**
    public void saveSchedule(Course newcourse,List<Schedule> scheduleList)
    {
        ScheduleDAOImpl scheduleDAO = new ScheduleDAOImpl();
        scheduleDAO.saveSchedule(newcourse, scheduleList);
    }
     */
}
