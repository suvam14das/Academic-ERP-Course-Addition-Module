package database.tests;

import com.erp.academic.bean.*;
import com.erp.academic.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class HibernateTest {
    public static void main(String args[]){
        try( Session session = SessionUtil.getSession()){

            Transaction txn = session.getTransaction();
            txn.begin();
            //Important for employee creation
            //new EmployeeTest().createSecureEmployee(session);
            /*
            Course course = new Course();
            course.setCourseCode("CS103");
            course.setCapacity(250);
            course.setDescription("Sample Course 3");
            course.setName("SampleCourse3");
            course.setCredits(4);
            course.setFaculty(null);
            course.setPreRequisite(null);
            course.setSpecializationList(null);
            course.setTerm(2);
            course.setYear(2021);

            session.save(course);
            */
            /*
            Specialization sp = new Specialization();
            sp.setName("Networks And Comminication");
            sp.setDescription("Networks And Comminication Specialization");
            sp.setYear(2020);
            sp.setCode("NC2020");
            sp.setCreditReq(20);
            session.save(sp);
            new DepartmentTest().createAndsave(session);
            new DomainTest().createAndsave(session);
            new EmployeeTest().createAndsave(session);
            */
            //new EmployeeTest().createAndsave(session);
            txn.commit();
        }catch (Exception e){
            System.err.println("error generated"+e);
            e.printStackTrace();
        }
    }
}
