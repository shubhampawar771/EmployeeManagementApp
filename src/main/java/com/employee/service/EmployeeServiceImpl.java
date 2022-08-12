package com.employee.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.employee.dao.EmployeeDao;
import com.employee.exception.EmployeeIdAlreadyExistsException;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.model.*;
import com.employee.repository.*;
import com.employee.util.EmployeeConstants;

@Service
public class EmployeeServiceImpl implements EmployeeService  {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public String createEmployee(Employee employee) throws EmployeeIdAlreadyExistsException{
		logger.info("Finding by Id '{}' exists in database or not", employee.getId());
		Optional<Employee> findId = employeeRepository.findById(employee.getId());
		if(findId.isPresent()) {
			logger.info("Id '{}' exists in database..Hence throwing error", employee.getId());
			throw new EmployeeIdAlreadyExistsException(EmployeeConstants.EMPLOYEE_ALREADY_EXISTS);
		}
		employeeRepository.save(employee);
		logger.info("Id not in database, Employee added succesfully '{}'",employee.getId());
		return EmployeeConstants.EMPLOYEE_ADDED;
	}

	@Override
	public String updateEmployee(Employee employee) throws EmployeeNotFoundException{
		logger.info("Finding by Id '{}' exists in database or not", employee.getId());
		Optional<Employee> emp = employeeRepository.findById(employee.getId());
			if(emp.isPresent()) {
				logger.info("Finding by Id '{}' exists...Updating", employee.getId());
				employeeRepository.save(employee);
				return EmployeeConstants.EMPLOYEE_UPDATED;
			}
			logger.info("finding by Id '{}' doestn't exists...throws error",employee.getId());
			throw new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND);
	}

	@Override
	public String deleteEmployee(long id) throws EmployeeNotFoundException {
		logger.info("Finding by Id '{}' exists in database or not",id);
		Optional<Employee> findbyId = employeeRepository.findById(id);
		if(findbyId.isPresent()) {
			logger.info("Finding by Id '{}' exists in database..hence deleting",id);
			employeeRepository.deleteById(id);
			return EmployeeConstants.EMPLOYEE_DELETED + id;
		}
		else {
			logger.info("Finding by Id '{}' doesn't exits in databse..Throws error",id);
			throw new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND + id);
		}	
	}
	
	@Override
	public List<Employee> getEmployeesList() {
		 List<Employee> employeeList = employeeRepository.findAll();
		logger.info("Finding if List exists in database or not",employeeList);
			  if(employeeList.isEmpty()) {
				 logger.info("List doesn't exists...Throws error");
			    new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_RECORDS_EMPTY);	  
		}
		logger.info("Getting List of Employees from database",employeeList);
			return employeeRepository.findAll();
	}
	
	@Override
	public Optional<Employee> getEmployeeById(long id) throws EmployeeNotFoundException {
		logger.info("Finding ID by getEmpById '{}' exists in database or not",id);
		Optional<Employee> findbyId = employeeRepository.findById(id);
		if(findbyId.isPresent()) {
			logger.info("Id '{}' exists in database",id);
			return employeeRepository.findById(id);
		}
		else {
			logger.info("Finding by Id '{}' doesn't exits in databse..Throws error",id);
			throw new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND);
		}
	}
	
	@Override
	public List<Employee> findByOrderBySalaryDesc() throws EmployeeNotFoundException {
		 List<Employee> employeeDescList = employeeRepository.findAll();
		logger.info("Finding if List exists in database or not");
			  if(employeeDescList.isEmpty()) {
				 logger.info("List doesn't exists...Throws error");
			    new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_RECORDS_EMPTY);	  
		}
	    logger.info("List exists in database..hence Showing Descending order");
     		return empDao.findByOrderBySalaryDesc();
	}

	@Override
	public List<Employee> findByOrderBySalaryAsc() throws EmployeeNotFoundException {
		 List<Employee> employeeAscList = employeeRepository.findAll();
		logger.info("Finding if List exists in database or not");
			  if(employeeAscList.isEmpty()) {
				 logger.info("List doesn't exists...Throws error");
			    new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_RECORDS_EMPTY);	  
		}
	    logger.info("List exists in database..hence Showing Ascending order");
		return empDao.findByOrderBySalaryAsc();
	}
	

	//JDBC Template
	
	@Override
	public List<Employee> getAllEmployeesByJdbc() throws EmployeeNotFoundException {
		logger.info("Finding if Employees List present in database by Jdbc");
		List<Employee> employeeList= empDao.getAllEmployees();
      	if (employeeList.isEmpty()) {
      		logger.info("Employee List Empty by Jdbc");
    		throw new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_RECORDS_EMPTY);
    }
      	logger.info("List Found Hence showing List by Jdbc");
		return employeeList;
	}
	
	
	@Override
	public List<Employee> findByOrderBySalaryAscByJdbc() throws EmployeeNotFoundException {
		logger.info("Finding if Employees List present in database by Jdbc");
		List<Employee> employeeList = empDao.getAllEmployees();
        	if (employeeList.isEmpty()) {
        		logger.info("Employee List Empty by Jdbc..hence throws error");
        		throw new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_RECORDS_EMPTY);
        }
        	logger.info("Before Ascending order By Jdbc list {} ", employeeList);
        	List<Employee> employeeAscList = employeeList.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary)).collect(Collectors.toList());
        	logger.info("Ascending Order By Jdbc list {} ", employeeAscList);
        return employeeAscList;
   }
		
	
	@Override
	public List<Employee> findByOrderBySalaryDescByJdbc() throws EmployeeNotFoundException {
		logger.info("Finding if Employees List present in database by Jdbc");		
		List<Employee> employeeList = empDao.getAllEmployees();
    	if (employeeList.isEmpty()) {
    		logger.info("Employee List Empty by Jdbc..hence throws error");
    		throw new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_RECORDS_EMPTY);
    }
    	logger.info("Before Descending order By Jdbc list {} ", employeeList);
    	List<Employee> employeeDescList = employeeList.stream()
            .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).collect(Collectors.toList());
    	logger.info("Descending Order By Jdbc list {} ", employeeDescList);
    return employeeDescList;
	}

	@Override
	public String updateEmployeeByJdbc(Employee employee,long id) throws EmployeeNotFoundException {
		logger.info("Finding by Id '{}' exists in database or not by Jdbc",id);
		Optional<Employee> emp = Optional.ofNullable(empDao.getById(id));
		if(emp.isPresent()) {
			logger.info("Id '{}' exists in database By Jdbc..hence Updating",id);
			empDao.updateEmployeeByJdbc(employee,id);
			return EmployeeConstants.EMPLOYEE_UPDATED + id;
		}
		logger.info("Finding by Id '{}' doesn't exists by Jdbc...Throws error",id);
		throw new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND + id);
	}

	@Override
	public String createEmployeeByJdbc(Employee employee) throws EmployeeIdAlreadyExistsException {
		Optional<Employee> findByEmail = Optional.ofNullable(empDao.getByEmailId(employee.getEmailId()));
		logger.info("Finding by EmailId:'{}' exists in database or not by Jdbc",employee.getEmailId());
		if(findByEmail.isPresent()) {	
		logger.info("EmailId: '{}' exists in database by Jdbc with this mail Hence Throws Error",employee.getEmailId());
			throw new EmployeeIdAlreadyExistsException(EmployeeConstants.EMPLOYEE_EMAILID_ALREADY_EXISTS + employee.getEmailId());
	}
			empDao.insert(employee);
			logger.info("EmailId doesn't exists in database By Jdbc..New Employee Added Successfully!'{}'",employee);
			return EmployeeConstants.EMPLOYEE_ADDED + employee.getId();
		}

	@Override
	public Employee getEmployeeByIdByJdbc(long id) throws EmployeeNotFoundException {
		logger.info("Finding by Id '{}' exists in database or not by jdbc",id);
		Optional<Employee> emp= Optional.ofNullable(empDao.getById(id));
		if(emp.isPresent()) {
			logger.info("Id '{}' exists in database by Jdbc..Showing Existing Employee",id);
			return emp.get();
		}
		logger.info("Id '{}' doesn't exists in database by Jdbc Hence Throws Error",id);
		throw new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND + id);	
	}

	@Override
	public String deleteEmployeeByJdbc(long id) throws EmployeeNotFoundException {
		
		logger.info("Finding by Id '{}' exists in database or not by Jdbc",id);
		Optional<Employee> findbyId = Optional.ofNullable(empDao.getById(id));
		if(findbyId.isPresent()) {
			empDao.delete(id);
			logger.info("Id '{}' exists in database By Jdbc..Employee Deleted Successfully!",id);
			return EmployeeConstants.EMPLOYEE_DELETED + id;
		}
		logger.info("Id '{}' doesn't exists in database by Jdbc Hence Throws Error",id);
		throw new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND + id);
	}
}

	
