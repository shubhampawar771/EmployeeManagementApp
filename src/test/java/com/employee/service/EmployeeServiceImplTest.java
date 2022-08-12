package com.employee.service;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.employee.dao.EmployeeDao;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl mockEmployeeServiceImpl;
	
	@Mock
	private EmployeeDao mockEmployeeDao;
	
	Employee employee;
	@Before
	public void setUp() throws Exception {
		 employee = new Employee(23,java.sql.Date.valueOf("2022-04-18 05:30:00"),"Analyst A4","Shubham","Pawar","Male",2000394.00,"8087970201","rj@capgemini.com");
	}
  @Test
  public void testDeleteEmployeeByJdbc() throws EmployeeNotFoundException {

      long employeeId = employee.getId();

      Mockito.when(mockEmployeeDao.getById(Mockito.any())).thenReturn(employee);

      String act = mockEmployeeServiceImpl.deleteEmployeeByJdbc(employeeId);
      String ex = "Employee Deleted Successfully with this Employeee Id : 23";
      assertEquals(act, ex);
  }

}
