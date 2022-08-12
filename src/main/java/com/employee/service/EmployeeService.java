package com.employee.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.employee.exception.EmployeeIdAlreadyExistsException;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.*;


@Service
public interface EmployeeService {
	
	//JPA Template
	public String createEmployee(Employee employee) throws EmployeeIdAlreadyExistsException;
	public String updateEmployee(Employee employee) throws EmployeeNotFoundException;
	public String deleteEmployee(long id) throws EmployeeNotFoundException;
	public List<Employee> getEmployeesList();
	public Optional<Employee> getEmployeeById(long id) throws EmployeeNotFoundException;
	public List<Employee> findByOrderBySalaryDesc() throws EmployeeNotFoundException;
	public List<Employee> findByOrderBySalaryAsc() throws EmployeeNotFoundException;
	
	//JDBC Template	
	public String updateEmployeeByJdbc(Employee employee,long id) throws EmployeeNotFoundException, EmployeeIdAlreadyExistsException;
	public String createEmployeeByJdbc(Employee employee) throws EmployeeIdAlreadyExistsException;
	
	public Employee getEmployeeByIdByJdbc(@PathVariable long id) throws EmployeeNotFoundException;
	public List<Employee> findByOrderBySalaryAscByJdbc() throws EmployeeNotFoundException;
	public Iterable<Employee> getAllEmployeesByJdbc() throws EmployeeNotFoundException;
	public List<Employee> findByOrderBySalaryDescByJdbc() throws EmployeeNotFoundException;
	public String deleteEmployeeByJdbc(long id) throws EmployeeNotFoundException;

}
