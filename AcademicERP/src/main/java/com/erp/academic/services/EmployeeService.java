package com.erp.academic.services;

import com.erp.academic.bean.Employee;
import com.erp.academic.dao.impl.EmployeeDAOImpl;
import com.erp.academic.utils.PasswordUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeService {

    public Employee adminlogin(Employee emp) throws Exception {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        List<Employee> employeedetails = employeeDAO.getemployeedetails(emp);
        System.out.println(employeedetails.get(0).getDepartment().getName());
        for(Employee e : employeedetails ){
            boolean passwordMatch = PasswordUtils.verifyUserPassword(emp.getPassword(),
                    e.getPassword(), e.getSalt());
            if(passwordMatch){
                if(e.getDepartment().getName().equalsIgnoreCase("Administration"))
                    return e;
                else {
                    Employee employee = new Employee();
                    employee.setEmployeeId(-1);
                    return employee;
                }
            }
        }
        Employee employee = new Employee();
        employee.setEmployeeId(-2);
        return employee;
    }

    public List<Employee> getAllFaculty() throws Exception {
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        List<Employee> professorList = employeeDAO.getAllProfessors();
        return  professorList;
    }

    public Employee getEmployeeById(Integer id) throws Exception {

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        Employee professor = employeeDAO.getEmployeeById(id);
        return professor;
    }

}
