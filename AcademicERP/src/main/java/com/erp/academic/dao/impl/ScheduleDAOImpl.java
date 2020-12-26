package com.erp.academic.dao.impl;

import com.erp.academic.bean.Course;
import com.erp.academic.bean.Schedule;
import com.erp.academic.dao.ScheduleDAO;
import com.erp.academic.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.transaction.internal.TransactionImpl;

import java.util.List;

public class ScheduleDAOImpl implements ScheduleDAO {

    /**
    public void saveSchedule(Course newcourse, List<Schedule> scheduleList)
    {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        for(Schedule cs : scheduleList)
        {
            System.out.println(cs.getTime());
            //cs.setCourse(newcourse);
            session.save(cs);
        }
        transaction.commit();
        session.close();


    }
     */
}
