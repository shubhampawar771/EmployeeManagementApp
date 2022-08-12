package com.employee.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.employee.model.Employee;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
	
	final String QUERY= "INSERT INTO EMPLOYEE (DESIGNATION, DOJ, EMAILID, FIRSTNAME,GENDER,LASTNAME,PHONENO,SALARY)VALUES (?,?,?,?,?,?,?,?)";
//	final String = ""
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<Employee> getAllEmployees() {
		return jdbcTemplate.query("SELECT * FROM EMPLOYEE", new BeanPropertyRowMapper<Employee>(Employee.class));
		
	}

	@Override
	public List<Employee> findByOrderBySalaryAsc() {
		
		return jdbcTemplate.query("SELECT * FROM Employee ORDER BY SALARY",new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public List<Employee> findByOrderBySalaryDesc() {
		return jdbcTemplate.query("SELECT * FROM Employee ORDER BY SALARY DESC",new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Override
	public long updateEmployeeByJdbc(Employee employee,long id) {
		try{
			return jdbcTemplate.update("update employee set designation = ?, doj = ?, firstname = ?, lastname=?, phoneno=?,salary=?,emailid=?,gender=? where id = ?",new Object[] {employee.getDesignation(),employee.getDoj(),employee.getFirstName(),employee.getLastName(),employee.getPhoneNo(),employee.getSalary(),employee.getEmailId(),employee.getGender(),id});
	}		
	catch(EmptyResultDataAccessException e){
		return (Long) null;
	}
	}

	@Override
	public int insert(Employee employee) {

		return jdbcTemplate.update(QUERY,employee.getDesignation(),employee.getDoj(),employee.getEmailId(),employee.getFirstName(),employee.getGender(),employee.getLastName(),employee.getPhoneNo(),employee.getSalary());
	}

	@Override
	public Employee getById(long id) {
		try{
			return jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEE WHERE ID=?", new BeanPropertyRowMapper<Employee>(Employee.class),id);
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}
	}

	@Override
	public Employee getByEmailId(String email) {
		try{
			return jdbcTemplate.queryForObject("SELECT EMAILID FROM EMPLOYEE WHERE EMAILID=?",  new BeanPropertyRowMapper<Employee>(Employee.class),email);
		}
		catch(EmptyResultDataAccessException e){
			return null;
		}
	}
	

	@Override
	public void delete(long id) {
		jdbcTemplate.update("DELETE FROM EMPLOYEE WHERE ID=?", id);
	}


}
