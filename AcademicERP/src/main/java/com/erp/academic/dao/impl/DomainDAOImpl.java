package com.erp.academic.dao.impl;

import com.erp.academic.bean.Domain;
import com.erp.academic.bean.Specialization;
import com.erp.academic.dao.DomainDAO;
import com.erp.academic.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

public class DomainDAOImpl implements DomainDAO {

    private CommonPurposeDAOImpl commonPurposeDAO= new CommonPurposeDAOImpl();

    public List<Domain> fetchAllDomain()throws Exception {
         List<Domain> domainList = (List<Domain>)(List<?>)commonPurposeDAO.fetchAll("Domain");
         return domainList;
    }

    public Domain getDomainById(Integer id) throws Exception {
        String qry = "select d from Domain as d where d.domainId= :id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        Domain domain = (Domain)commonPurposeDAO.qryWithInputs(qry, map).get(0);
        return domain;
    }
}
