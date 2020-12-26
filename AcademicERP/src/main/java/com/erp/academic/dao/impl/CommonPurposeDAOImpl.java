package com.erp.academic.dao.impl;

import com.erp.academic.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

public class CommonPurposeDAOImpl {

    public List<Object> fetchAll(String tableName)throws Exception{
        Session session = null;
        Transaction txn = null;
        try {
            session = SessionUtil.getSession();
            txn = session.getTransaction();
            txn.begin();
            List<Object> domainList = session.createQuery("from "+tableName).list();
            txn.commit();
            return domainList;
        }catch(Exception e){
            throw e;
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    public List<Object> qryWithInputs(String query, Map<String, Object> inp)throws Exception{
        Session session = null;
        Transaction txn = null;
        try {
            session = SessionUtil.getSession();
            txn = session.getTransaction();
            Query qry = session.createQuery(query);
            for(String key : inp.keySet()) {
                qry.setParameter(key,inp.get(key));
            }
            txn.begin();
            List<Object> objList = qry.list();
            txn.commit();
            return objList;
        }catch(Exception e){
            System.out.println("Exception Occured"+e);
            throw e;
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }

    public void save(Object inp)throws Exception{
        Session session = null;
        Transaction txn = null;
        try {
            session = SessionUtil.getSession();
            txn = session.getTransaction();
            txn.begin();
            session.save(inp);
            txn.commit();
        }catch(Exception e){
            System.out.println("Exception Occured"+e);
            throw e;
        }finally{
            if(session!=null){
                session.close();
            }
        }
    }
}
