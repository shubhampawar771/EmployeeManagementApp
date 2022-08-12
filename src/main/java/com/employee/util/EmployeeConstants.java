package com.employee.util;

public class EmployeeConstants {

//for messages	
    public static final String EMPLOYEE_ADDED = "Employee Added Successfully with this Employeee Id : ";
    public static final String EMPLOYEE_UPDATED = "Employee Details Updated Successfully with this Employeee Id : ";
    public static final String EMPLOYEE_DELETED = "Employee Deleted Successfully with this Employeee Id : ";
    public static final String EMPLOYEE_RECORDS_EMPTY = "Employee list is empty! No Employee data present into database";
    public static final String EMPLOYEE_FOUND = "Employee Found with this employee id : ";   
    public static final String EMPLOYEE_NOT_FOUND = "Employee Not Found with this employee id : "; 
    public static final String EMPLOYEE_ALREADY_EXISTS = "ID already exists";
    public static final String EMPLOYEE_EMAILID_ALREADY_EXISTS = "Email ID already exists : ";

//for url in controller
    public static final String EMPLOYEE_ADD_URL = "/new";
    public static final String EMPLOYEE_UPDATE_URL = "/update/{id}";
    public static final String EMPLOYEE_DELETE_URL = "/delete/{id}";
    public static final String VIEW_EMPLOYEES_LIST_URL = "/all";
    public static final String VIEW_EMPLOYEES_NAME_SALARY_ASC_URL = "/asc";
    public static final String VIEW_EMPLOYEES_NAME_SALARY_DESC_URL = "/desc";
    public static final String VIEW_EMPLOYEE_BY_ID_URL = "/id/{id}";
    public static final String ORIGIN_URL = "http://localhost:4200";
    public static final String REQUESTMAPPING_URL = "/employee";
    
//for validations  
    public static final String DOJ_REQUIRED = "Date of joining is required";
    public static final String DESIGNATION_REQUIRED ="Designation cannot be empty";
    public static final String FIRSTNAME_REQUIRED ="Please give First Name";
    public static final String lASTNAME_REQUIRED ="Last Name cannot be empty";
    public static final String DOJ_PATTERN ="yyyy-MM-dd";
    public static final String GENDER_REQUIRED = "Gender cant be empty";
    public static final String SALARY_REQUIRED ="Salary cannot be null";
    public static final String PHONENO_PATTERN = "only 10 digits allowed";
    public static final String PHONENO_REQUIRED = "Phone Number cannot be empty";
    public static final String EMAIL_REQUIRED = "Email Id is required";
}