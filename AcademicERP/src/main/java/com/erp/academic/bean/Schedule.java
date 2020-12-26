package com.erp.academic.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Course_Schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer scheduleId;
    @Column(nullable = false)
    private String time;
    @Column(nullable = false)
    private String day;
    @Column(nullable = false)
    private String room;
    private String building;
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "course_id")
    //private Course course;

    public Schedule(String time, String day, String room, String building) {
        this.time = time;
        this.day = day;
        this.room = room;
        this.building = building;
    }

    public Schedule() {
    }

    //public Integer getScheduleId() {         return scheduleId;     }

    //public Course getCourse() {      return course;     }

    //public void setCourse(Course course) {        this.course = course;     }

    //public void setScheduleId(Integer scheduleId) {         this.scheduleId = scheduleId;     }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
