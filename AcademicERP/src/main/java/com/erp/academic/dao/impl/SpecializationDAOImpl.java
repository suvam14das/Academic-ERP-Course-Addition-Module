package com.erp.academic.dao.impl;

import com.erp.academic.bean.Domain;
import com.erp.academic.bean.Specialization;
import com.erp.academic.dao.SpecializationDAO;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecializationDAOImpl implements SpecializationDAO {

    private CommonPurposeDAOImpl commonPurposeDAO= new CommonPurposeDAOImpl();


    public List<Specialization> fetchAllSpecialization()throws Exception {
        List<Specialization> specializationList = (List<Specialization>)(List<?>)commonPurposeDAO.fetchAll("Specialization");
        return specializationList;
    }

    public Specialization getSpecializationById(Integer id) throws Exception {
        String qry = "select s from Specialization as s where s.id= :id";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        Specialization specialization = (Specialization)commonPurposeDAO.qryWithInputs(qry, map).get(0);
        return specialization;
    }
}
