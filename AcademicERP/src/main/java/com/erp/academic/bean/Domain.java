package com.erp.academic.bean;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="Domains")
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="domain_id")
    private Integer domainId;
    @Column(nullable = false)
    private String program;
    @Column(nullable = false)
    private Integer batch;
    @Column(nullable = false)
    private Integer capacity;
    private String qualification;

    @ManyToMany
    @JoinTable(name="Course_Domain",joinColumns = {@JoinColumn(name="domain_id")},
            inverseJoinColumns = {@JoinColumn(name="course_id")})
    private List<Course> courses;

    public Domain(String program, Integer batch, Integer capacity, String qualification) {
        this.program = program;
        this.batch = batch;
        this.capacity = capacity;
        this.qualification = qualification;
    }

    public Domain() {
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

}
