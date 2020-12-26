package com.erp.academic.bean;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Specializations")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="specialization_id")
    private Integer id;
    @Column(unique = true, nullable = false)
    private String code;
    @Column(nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Integer year;
    @Column(name="credits_required", nullable = false)
    private Integer creditReq;

    @ManyToMany
    @JoinTable(name = "Course_Specialization", joinColumns = {@JoinColumn(name="specialization_id")},
            inverseJoinColumns = {@JoinColumn(name="course_id")})
    private List<Course> courses;

    public Specialization(String code, String name, String description, Integer year, Integer creditReq) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.year = year;
        this.creditReq = creditReq;
    }

    public Specialization() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Integer getCreditReq() {
        return creditReq;
    }

    public void setCreditReq(Integer creditReq) {
        this.creditReq = creditReq;
    }
}
