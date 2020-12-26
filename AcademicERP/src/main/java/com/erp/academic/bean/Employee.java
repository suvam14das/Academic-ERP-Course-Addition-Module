package com.erp.academic.bean;

import com.erp.academic.utils.PasswordUtils;

import javax.persistence.*;

@Entity
@Table(name="Employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id")
    private Integer employeeId;
    @Column(name="firstname", nullable = false)
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    private String title;
    private String photographpath;
    @Column(nullable = false)
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dept_id")
    private Department department;
    private String salt;


    public Employee(String firstName, String lastName, String email, String title, String photographpath) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.title = title;
        this.photographpath = photographpath;
    }

    public Employee() {
    }

    public Employee(String email) {
        this.email = email;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhotographPath() {
        return photographpath;
    }

    public void setPhotographPath(String photographpath) {
        this.photographpath = photographpath;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotographpath() {
        return photographpath;
    }

    public void setPhotographpath(String photographpath) {
        this.photographpath = photographpath;
    }

    public String getSalt() {
        return salt;
    }

    public void generateSecurePassword(String password){
        this.salt = PasswordUtils.getSalt(30);
        String securePassword = PasswordUtils.generateSecurePassword(password, salt);
        this.password =  securePassword;
    }
}
