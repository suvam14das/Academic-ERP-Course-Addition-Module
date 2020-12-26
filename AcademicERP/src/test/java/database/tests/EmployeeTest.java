package database.tests;

import com.erp.academic.bean.Department;
import com.erp.academic.bean.Employee;
import com.erp.academic.utils.PasswordUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeTest {

    public void createAndsave(Session session){

        StringBuffer hql = new StringBuffer();
        //hql.append("Select departmentId from Department where name='CSE'");
        hql.append("from Department where name='Admin'");
        Query query = session.createQuery(hql.toString());
        List<Department> department = query.getResultList();

        Employee emp1 = new Employee();
        emp1.setDepartment(department.get(0));
        emp1.setEmail("adminfaculty_sample@email.com");
        emp1.setFirstName("first");
        emp1.setLastName("name");
        emp1.setTitle("CourseSetter");
        session.save(emp1);

    }

    public void createSecureEmployee(Session session){

        StringBuffer hql = new StringBuffer();
        hql.append("from Department where name='Administration'");
        Query query = session.createQuery(hql.toString());
        List<Department> department = query.getResultList();


        Employee newemployee1 = new Employee("Aditya", "Saha",
                "adityasaha@gmail.com","CourseSetter", "");
        newemployee1.generateSecurePassword("1234");
        newemployee1.setDepartment(department.get(0));
        session.save(newemployee1);

        Employee newemployee2 = new Employee("Suvam", "Das",
                "suvamDas@gmail.com","CourseSetter", "");
        newemployee2.generateSecurePassword("2345");
        newemployee2.setDepartment(department.get(0));
        session.save(newemployee2);

        StringBuffer hql1 = new StringBuffer();
        hql1.append("from Department where name='CSE'");
        Query query1 = session.createQuery(hql1.toString());
        List<Department> department1 = query.getResultList();

        Employee newemployee3 = new Employee("Soumyajit", "Das",
                "soumyajitDas@gmail.com","Professor", "");
        newemployee3.generateSecurePassword("6789");
        newemployee3.setDepartment(department1.get(0));
        session.save(newemployee3);

        Employee newemployee4 = new Employee("Shaon", "Dasgupta",
                "shaonDasgupta@gmail.com","Professor", "");
        newemployee4.generateSecurePassword("5678");
        newemployee4.setDepartment(department1.get(0));
        session.save(newemployee4);

        StringBuffer hql2 = new StringBuffer();
        hql2.append("from Department where name='IT'");
        Query query2 = session.createQuery(hql2.toString());
        List<Department> department2 = query.getResultList();

        Employee newemployee5 = new Employee("Subhodeep", "Sahoo",
                "subhodeepSahoo@gmail.com","Professor", "");
        newemployee5.generateSecurePassword("4569");
        newemployee5.setDepartment(department2.get(0));
        session.save(newemployee5);

    }
}
