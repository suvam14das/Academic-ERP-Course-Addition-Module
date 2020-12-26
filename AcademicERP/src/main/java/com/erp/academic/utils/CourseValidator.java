package com.erp.academic.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CourseValidator {

    private List<String> errorList = new ArrayList<String>();

    public CourseValidator validateName(Map<String,Object> coursedetails){
        String courseName = (String) coursedetails.get("course_name");
        if(courseName == null || courseName.length() == 0){
            errorList.add("Course name cannot be empty");
        }
        return this;
    }

    public CourseValidator validateDescription(Map<String,Object> coursedetails){
        String description = (String)coursedetails.get("course_description");
        return this;
    }

    public CourseValidator validateYear(Map<String,Object> coursedetails){
        String year = (String)coursedetails.get("course_year");
        if(year == null || year.length() == 0){
            errorList.add("Course Year cannot be empty");
        }else{
            try{
                int yearInt =  Integer.parseInt(year);
                if(yearInt<0 || year.length() != 4){
                    errorList.add("Invalid year provided");
                }
            }catch(Exception e){
                errorList.add("Year is not a number");
            }
        }
        return this;
    }
    public CourseValidator validateTerm(Map<String,Object> coursedetails){
        String term = (String)coursedetails.get("course_term");
        if(term == null || term.length() == 0){
            errorList.add("Course Term cannot be empty");
        }else{
            try{
                int termInt = Integer.parseInt(term);
                if(termInt < 0){
                    errorList.add("Invalid Term provided");
                }
            }catch(Exception e){
                errorList.add("Term is not a number");
            }
        }
        return this;
    }

    public CourseValidator validateCapacity(Map<String,Object> coursedetails){
        String capacity = (String)coursedetails.get("course_capacity");
        if(capacity == null || capacity.length() == 0){
            errorList.add("Course Capacity cannot be empty");
        }else{
            try{
                int capacityInt = Integer.parseInt(capacity);
                if(capacityInt <= 0){
                    errorList.add("Invalid Capacity provided");
                }
            }catch(Exception e){
                errorList.add("Couse Capacity is not a number");
            }
        }
        return this;
    }

    public CourseValidator validateCredit(Map<String,Object> coursedetails){
        String credit = (String)coursedetails.get("course_credit");
        if(credit == null || credit.length() == 0){
            errorList.add("Course Credit cannot be empty");
        }else{
            try{
                int creditInt = Integer.parseInt(credit);
                if(creditInt < 0){
                    errorList.add("Invalid Credit provided");
                }
            }catch(Exception e){
                errorList.add("Credit is not a number");
            }
        }
        return this;
    }

    public CourseValidator validateFaculty(Map<String,Object> coursedetails){
        String facultyId = (String)coursedetails.get("course_faculty");
        if(facultyId == null){
            errorList.add("Invalid Credit provided");
        }
        return this;
    }

   public CourseValidator validateDomain(Map<String,Object> coursedetails){
       List<Integer> domains = HelperUtils.getIntegerList((List<?>)coursedetails.get("domain_list"));
       if(domains.size() == 0){
           errorList.add("No domains for the Course has been selected");
       }
       return this;
   }

    public CourseValidator validatePrerequisites(Map<String,Object> coursedetails){
        return this;
    }

    public CourseValidator validateSpecialization(Map<String,Object> coursedetails){
        List<Integer> specializations = HelperUtils.getIntegerList((List<?>)coursedetails.get("specialization_list"));
        if(specializations.size() == 0){
            errorList.add("No Specialization for the Course has been selected");
        }
        return this;
    }

    public List<String> getErrorList() {
        return errorList;
    }
}
