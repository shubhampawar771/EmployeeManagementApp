package com.employee.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.model.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	 @Query(value= "SELECT * FROM Employee ORDER BY SALARY",nativeQuery = true)
	List<Employee> findByOrderBySalaryAsc();
	 
	 @Query(value= "SELECT * FROM Employee ORDER BY SALARY DESC ",nativeQuery = true) 
	List<Employee> findByOrderBySalaryDesc();
}
