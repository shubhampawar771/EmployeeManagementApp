package com.employee.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.employee.util.EmployeeConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Employee {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
//@GeneratedValue(generator="system-uuid")
//@GenericGenerator(name="system-uuid", strategy="uuid")
//@Column(name="empId")
private long id;

//@NotNull(message = EmployeeConstants.DOJ_REQUIRED)
@JsonFormat(pattern = EmployeeConstants.DOJ_PATTERN)
private Date doj;

//@NotEmpty(message = EmployeeConstants.DESIGNATION_REQUIRED)
private String designation;

@NotNull(message = EmployeeConstants.FIRSTNAME_REQUIRED)
private String firstName;

@NotNull(message = EmployeeConstants.lASTNAME_REQUIRED)
private String lastName;

@NotNull(message = EmployeeConstants.GENDER_REQUIRED)
private String gender;

@Column(name="salary")
@DecimalMin(value = "1.0", message = EmployeeConstants.SALARY_REQUIRED)
private double salary;

@Column(name="phoneNo")
@Pattern(regexp = "[0-9]{10}",message = EmployeeConstants.PHONENO_PATTERN)
@NotNull(message = EmployeeConstants.PHONENO_REQUIRED)
private String phoneNo;

@Column(name="emailId")
@Email
@NotBlank(message = EmployeeConstants.EMAIL_REQUIRED)
private String emailId;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public Date getDoj() {
	return doj;
}

public void setDoj(Date doj) {
	this.doj = doj;
}

public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public double getSalary() {
	return salary;
}

public void setSalary(double salary) {
	this.salary = salary;
}

public String getPhoneNo() {
	return phoneNo;
}

public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}

public String getEmailId() {
	return emailId;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}

@Override
public String toString() {
	return "Employee [id=" + id + ", doj=" + doj + ", designation=" + designation + ", firstName=" + firstName
			+ ", lastName=" + lastName + ", gender=" + gender + ", salary=" + salary + ", phoneNo=" + phoneNo
			+ ", emailId=" + emailId + "]";
}

public Employee(long id, Date doj, String designation, @NotNull(message = "Please give First Name") String firstName,
		@NotNull(message = "Last Name cannot be empty") String lastName,
		@NotNull(message = "Gender cant be empty") String gender,
		@DecimalMin(value = "1.0", message = "Salary cannot be null") double salary,
		@Pattern(regexp = "[0-9]{10}", message = "only 10 digits allowed") @NotNull(message = "Phone Number cannot be empty") String phoneNo,
		@Email @NotBlank(message = "Email Id is required") String emailId) {
	super();
	this.id = id;
	this.doj = doj;
	this.designation = designation;
	this.firstName = firstName;
	this.lastName = lastName;
	this.gender = gender;
	this.salary = salary;
	this.phoneNo = phoneNo;
	this.emailId = emailId;
}

public Employee() {
	super();
	// TODO Auto-generated constructor stub
}


}