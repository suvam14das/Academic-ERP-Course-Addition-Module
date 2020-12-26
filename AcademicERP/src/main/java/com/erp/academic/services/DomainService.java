package com.erp.academic.services;

import com.erp.academic.bean.Domain;
import com.erp.academic.dao.DomainDAO;
import com.erp.academic.dao.impl.DomainDAOImpl;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DomainService {

    public List<Domain> getAllDomain() throws Exception {
        DomainDAOImpl domainDao = new DomainDAOImpl();
        return domainDao.fetchAllDomain();
    }

    public List<Domain> getDomainsCollection(List<Integer> domains) throws Exception {
        DomainDAOImpl domainDao = new DomainDAOImpl();
        List<Domain> domainsSet = new ArrayList<Domain>();
        for(Integer id :domains){
            domainsSet.add(domainDao.getDomainById(id));
        }
        return domainsSet;
    }
}
