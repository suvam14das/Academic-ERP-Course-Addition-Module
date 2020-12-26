package com.erp.academic.controller;

import com.erp.academic.bean.Department;
import com.erp.academic.bean.Employee;
import com.erp.academic.services.EmployeeService;
import com.erp.academic.utils.PasswordUtils;
import com.erp.academic.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.util.HashMap;

@Path("employee")
public class EmployeeController {

    @POST
    @Path("/getlogin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Response adminemployeelogin(Employee emp) throws URISyntaxException {
        try {
            EmployeeService loginservice = new EmployeeService();
            Employee employee = loginservice.adminlogin(emp);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("id", employee.getEmployeeId());
            map.put("emp_name", (employee.getFirstName()+" "+employee.getLastName()));
            return Response.ok().entity(map).build();
        }catch(Exception e){
            return Response.ok().entity(0).build();
        }
    }

//    public static void main(String args[])
//    {
////        String salt = PasswordUtils.getSalt(30);
////        String mySecurePassword = PasswordUtils.generateSecurePassword("andrew123", salt);
//        Employee newemployee = new Employee("Andrew", "NG", "andrew@gmail.com","Professor", "");
//        Department department = new Department("Data Science", 50);
//        department.setDepartmentId(1);
//        newemployee.setDepartment(department);
//        newemployee.generateSecurePassword("andrew123");
//        Session session = SessionUtil.getSession();
////        Transaction transaction = session.getTransaction();
////        String stmt = "update Employee set password= :npass, salt= :salt where employeeId=1";
////        Query query = session.createQuery(stmt);
////        query.setParameter("npass", mySecurePassword);
////        query.setParameter("salt", salt);
////        int result = query.executeUpdate();
////        transaction.commit();
//        session.save(newemployee);
//        session.close();
//    }
}
