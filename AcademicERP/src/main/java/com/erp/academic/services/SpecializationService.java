package com.erp.academic.services;

import com.erp.academic.bean.Specialization;
import com.erp.academic.dao.impl.DomainDAOImpl;
import com.erp.academic.dao.impl.SpecializationDAOImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpecializationService {
    public List<Specialization> getAllSpecialization()throws Exception{
        SpecializationDAOImpl specializationDAO = new SpecializationDAOImpl();
        return specializationDAO.fetchAllSpecialization();
    }

    public List<Specialization> getSpecilizations(List<Integer> specializationIds) throws Exception {
        List<Specialization> specializationsSet= new ArrayList<Specialization>();
        for(Integer id:specializationIds){
            SpecializationDAOImpl specializationDAO = new SpecializationDAOImpl();
            specializationsSet.add(specializationDAO.getSpecializationById(id));
        }
        return specializationsSet;
    }


}
