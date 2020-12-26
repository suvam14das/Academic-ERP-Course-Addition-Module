package com.erp.academic.bean;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="Courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="course_id")
    private Integer courseId;
    @Column(name="course_code", nullable = false, unique = true)
    private String courseCode;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Integer year;
    @Column(nullable = false)
    private Integer term;
    @Column(nullable = false)
    private Integer credits;
    @Column(nullable = false)
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee faculty;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="Course_Domain", joinColumns = {@JoinColumn(name="course_id")},
            inverseJoinColumns = {@JoinColumn(name="domain_id")})
    private List<Domain> domains;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="Course_Prerequisite",
            joinColumns = {@JoinColumn(name="course_id", referencedColumnName = "course_id", unique = false)},
            inverseJoinColumns = {@JoinColumn(name="prerequisite_id", referencedColumnName = "course_id", unique = false)})
    private Set<Course> preRequisite;
    @ManyToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinTable(name="Course_Specialization", joinColumns = {@JoinColumn(name="course_id")},
            inverseJoinColumns = {@JoinColumn(name = "specialization_id")})
    private List<Specialization> specializationList;
    @OneToMany(cascade=CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name="course_id")
    private List<Schedule> scheduleList;

    public Course(String courseCode, String name, String description, Integer year, Integer term, Integer credits, Integer capacity) {
        this.courseCode = courseCode;
        this.name = name;
        this.description = description;
        this.year = year;
        this.term = term;
        this.credits = credits;
        this.capacity = capacity;
    }

    public Course() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Employee getFaculty() {
        return faculty;
    }

    public void setFaculty(Employee faculty) {
        this.faculty = faculty;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(List<Domain> domains) {
        this.domains = domains;
    }

    public Set<Course> getPreRequisite() {
        return preRequisite;
    }

    public void setPreRequisite(Set<Course> preRequisite) {
        this.preRequisite = preRequisite;
    }

    public List<Specialization> getSpecializationList() {
        return specializationList;
    }

    public void setSpecializationList(List<Specialization> specializationList) {
        this.specializationList = specializationList;
    }

    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
