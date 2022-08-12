package com.employee.dao;

import java.util.List;
import com.employee.model.Employee;

public interface EmployeeDao {
	
	public List<Employee> getAllEmployees();
	List<Employee> findByOrderBySalaryAsc();
	List<Employee> findByOrderBySalaryDesc();
	public long updateEmployeeByJdbc(Employee employee,long id);
	public int insert(Employee employee);
	public Employee getById(long id);
	void delete(long id);
	public Employee getByEmailId(String email);
}
