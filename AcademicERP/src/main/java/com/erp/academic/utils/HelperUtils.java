package com.erp.academic.utils;

import java.util.ArrayList;
import java.util.List;

public class HelperUtils {

    public static List<Integer> getIntegerList(List list){
        List<Integer> integerList = new ArrayList<Integer>();
        for(Object element : list){
            integerList.add(Integer.parseInt((String)element));
        }
        return integerList;
    }

}
