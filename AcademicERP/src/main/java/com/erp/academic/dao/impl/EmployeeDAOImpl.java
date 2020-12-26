package com.erp.academic.dao.impl;

import com.erp.academic.bean.Course;
import com.erp.academic.bean.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAOImpl {

    private CommonPurposeDAOImpl commonPurposeDAO= new CommonPurposeDAOImpl();

    public List<Employee> getemployeedetails(Employee emp) throws Exception {

        String sql = "select e from Employee as e where e.email= : inputemail";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("inputemail", emp.getEmail());
        List<Employee> employee = (List<Employee>)(List<?>)commonPurposeDAO.qryWithInputs(sql,map);
        return employee;

    }

    public List<Employee> getAllProfessors() throws Exception {
        String sql = "from Employee as e where title='Professor'";
        List<Employee> professorList = (List<Employee>)(List<?>)commonPurposeDAO.qryWithInputs(sql, new HashMap());
        return professorList;
    }

    public Employee getEmployeeById(Integer id) throws Exception {
        String sql = "from Employee where employeeId= :id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        Employee employee = (Employee)(commonPurposeDAO.qryWithInputs(sql, map).get(0));
        return employee;
    }


}
