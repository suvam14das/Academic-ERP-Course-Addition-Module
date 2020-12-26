package database.tests;

import com.erp.academic.bean.Department;
import com.erp.academic.bean.Domain;
import org.hibernate.Session;

public class DomainTest {

    public void createAndsave(Session session){

        Domain domain1 = new Domain();
        domain1.setBatch(2020);
        domain1.setCapacity(1000);
        domain1.setProgram("AIML");
        domain1.setQualification("Btech");
        Domain domain2 = new Domain();
        domain2.setBatch(2020);
        domain2.setCapacity(1000);
        domain2.setProgram("NC");
        domain2.setQualification("Btech");

        session.save(domain1);
        session.save(domain2);
    }



}

