package com.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.exception.*;
import com.employee.model.*;
import com.employee.service.*;
import com.employee.util.EmployeeConstants;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin(origins = EmployeeConstants.ORIGIN_URL)
@RequestMapping(EmployeeConstants.REQUESTMAPPING_URL)
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
//	
//	@ApiOperation(value="Add new Employee")
//	@PostMapping(EmployeeConstants.EMPLOYEE_ADD_URL)
//	public SuccessMessage createEmployee (@Valid @RequestBody Employee employee) throws EmployeeIdAlreadyExistsException{
//		return new SuccessMessage(employeeService.createEmployee(employee));
//	}
 
//	@ApiOperation(value="View Employee by ID")
//	@GetMapping(EmployeeConstants.VIEW_EMPLOYEE_BY_ID_URL)
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long employeeId)
//			throws EmployeeNotFoundException {
//		Employee employee = employeeService.getEmployeeById(employeeId)
//				
//				.orElseThrow(() -> new EmployeeNotFoundException("No Employee found with id :  " + employeeId));
//		return ResponseEntity.ok().body(employee);
//	}
	 
//	@ApiOperation(value="Update Employee by ID")	
//	@PutMapping(EmployeeConstants.EMPLOYEE_UPDATE_URL)
//	public SuccessMessage updateEmployee (@Valid @PathVariable (value = "id") long employeeId,
//			@RequestBody Employee empDetails) throws EmployeeNotFoundException {
//		return new SuccessMessage(employeeService.updateEmployee(empDetails));
//	}
	
//	@ApiOperation(value="Delete Employee by ID")
//	@DeleteMapping(EmployeeConstants.EMPLOYEE_DELETE_URL)
//	public SuccessMessage deleteEmployee(@PathVariable(value = "id") long id)
//			throws EmployeeNotFoundException {
//		return new SuccessMessage(employeeService.deleteEmployee(id));
//
//	}
//	
//	@ApiOperation(value="View List of all Employees")
//	@GetMapping(EmployeeConstants.VIEW_EMPLOYEES_LIST_URL)
//	public List<Employee> getAllEmployees() throws EmployeeNotFoundException {
//		return employeeService.getEmployeesList();
//		
//	}
	
//	@ApiOperation(value="View Employees By Descending order of Salary")
//	@GetMapping(EmployeeConstants.VIEW_EMPLOYEES_NAME_SALARY_DESC_URL)
//	public List<Employee> findByOrderBySalaryDesc() throws EmployeeNotFoundException {
//		return employeeService.findByOrderBySalaryDesc();
//	}
	
//	@ApiOperation(value="View Employees By Ascending order of Salary")
//	@GetMapping(EmployeeConstants.VIEW_EMPLOYEES_NAME_SALARY_ASC_URL)
//	public List<Employee> findByOrderBySalaryAsc() throws EmployeeNotFoundException {
//		return employeeService.findByOrderBySalaryAsc();
//	}
	
	
	//JDBC Template
	
	
	@ApiOperation(value="Add new Employee By Jdbc")
	@PostMapping(EmployeeConstants.EMPLOYEE_ADD_URL)
	public SuccessMessage createEmployeeByJdbc (@Valid @RequestBody Employee employee) throws EmployeeIdAlreadyExistsException{
		return new SuccessMessage(employeeService.createEmployeeByJdbc(employee));
	}
	
	
	@ApiOperation(value="View List of all Employees")
	@GetMapping(EmployeeConstants.VIEW_EMPLOYEES_LIST_URL)
	public List<Employee> getAllEmployeesByJdbc() throws EmployeeNotFoundException {
		return (List<Employee>)employeeService.getAllEmployeesByJdbc();
	}

	@ApiOperation(value="View Employees By Ascending order of Salary")
	@GetMapping(EmployeeConstants.VIEW_EMPLOYEES_NAME_SALARY_ASC_URL)
	public List<Employee> findByOrderBySalaryAscByJdbc() throws EmployeeNotFoundException {
		return employeeService.findByOrderBySalaryAscByJdbc();
	}
	
	 @ApiOperation(value="View Employees By Descending order of Salary")	
	 @GetMapping(EmployeeConstants.VIEW_EMPLOYEES_NAME_SALARY_DESC_URL)
	 public List<Employee> findByOrderBySalaryDescByJdbc() throws EmployeeNotFoundException {
	 return employeeService.findByOrderBySalaryDescByJdbc();
	 }
	
	 @ApiOperation(value="Update Employee By Jdbc")	
	 @PutMapping(EmployeeConstants.EMPLOYEE_UPDATE_URL)
	 public SuccessMessage updateEmployeeByJdbc( @RequestBody Employee employee,@PathVariable long id) throws EmployeeNotFoundException, EmployeeIdAlreadyExistsException {
	 return new SuccessMessage(employeeService.updateEmployeeByJdbc(employee,id));
	}
	 
		@ApiOperation(value="View Employee by ID By JDBC")
		@GetMapping(EmployeeConstants.VIEW_EMPLOYEE_BY_ID_URL)
		public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long employeeId)
				throws EmployeeNotFoundException {
			Employee employee = employeeService.getEmployeeByIdByJdbc(employeeId);
			return ResponseEntity.ok().body(employee);
		}
	
		@ApiOperation(value="Delete Employee by ID by JDBC")
		@DeleteMapping(EmployeeConstants.EMPLOYEE_DELETE_URL)
		public SuccessMessage deleteEmployee(@PathVariable(value = "id") long id)
				throws EmployeeNotFoundException {
			return new SuccessMessage(employeeService.deleteEmployeeByJdbc(id));
			}
	
		//Method Handler for swagger
	
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public Map<String, String> handleMethodArsNotValid(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>();
	        ex.getBindingResult().getFieldErrors()
	                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
	        return errors;

	    }

	
	
//	 
//	 @GetMapping("/id/{id}")
//	 public Employee getEmployeeById(@PathVariable(value = "id") long employeeId) throws EmployeeNotFoundException {
//		return empDao.getEmployeeById(employeeId); 
//	 }
	
//	 @GetMapping("/desc")
//	 public List<Employee> findByOrderBySalaryDesc() throws EmployeeNotFoundException {
//	 return employeeService.findByOrderBySalaryDesc();
//	 }
	
	
//	@GetMapping("/asc")
//	public List<Employee> findByOrderBySalaryAsc() throws EmployeeNotFoundException {
//		return employeeService.findByOrderBySalaryAsc();
//	}
	 
//		@ApiOperation(value="Update Employee by ID")	
//		@PutMapping(EmployeeConstants.EMPLOYEE_UPDATE_URL)
//		public ResponseEntity<Employee> updatedEmployee (@Valid @PathVariable (value = "id") long employeeId,
//				@RequestBody Employee empDetails) throws EmployeeNotFoundException {
//			Employee employee = employeeService.getEmployeeById(employeeId)
//					.orElseThrow(()-> new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND + employeeId));
//			employee.setFirstName(empDetails.getFirstName());
//			employee.setLastName(empDetails.getLastName());
//			employee.setDoj(empDetails.getDoj());
//			employee.setDesignation(empDetails.getDesignation());
//			employee.setSalary(empDetails.getSalary());
//			employee.setPhoneNo(empDetails.getPhoneNo());
//			employee.setEmailId(empDetails.getEmailId());
//			employee.setGender(empDetails.getGender());
//			Employee updatedEmployee = employeeService.updateEmployees(employee);
//			return ResponseEntity.ok().body(updatedEmployee);
//		}
	 
//		@ApiOperation(value="Delete Employee by ID")
//		@DeleteMapping(EmployeeConstants.EMPLOYEE_DELETE_URL)
//		public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") long employeeId)
//				throws EmployeeNotFoundException {
//			Employee employee = employeeService.getEmployeeById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(EmployeeConstants.EMPLOYEE_NOT_FOUND + employeeId));
//			employeeService.deleteEmployee(employee);
//			Map<String, Boolean> response = new HashMap<String, Boolean>();
//			response.put("Deleted", Boolean.TRUE);
//			return response;
//
//		}

	}
	  

