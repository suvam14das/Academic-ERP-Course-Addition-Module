package database.tests;

import com.erp.academic.bean.Department;
import org.hibernate.Session;

public class DepartmentTest {

    public void createAndsave(Session session){
        Department department = new Department();
        department.setCapacity(20);
        department.setName("Admin");
        session.save(department);
    }
}
