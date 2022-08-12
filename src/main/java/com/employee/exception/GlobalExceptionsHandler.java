package com.employee.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionsHandler {
@ExceptionHandler(EmployeeNotFoundException.class)
public ResponseEntity<?>employeeNotFound(EmployeeNotFoundException ex,WebRequest request){
	ErrorDetails errorDetails =new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
	return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	
}
@ExceptionHandler(Exception.class)
public ResponseEntity<?>otherExcpetionHandler(Exception ex,WebRequest request){
	ErrorDetails errorDetails =new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
	return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
}

@ExceptionHandler(EmployeeIdAlreadyExistsException.class)
public ResponseEntity<?>employeeExist(EmployeeIdAlreadyExistsException ex, WebRequest request){
    ErrorDetails errordetails=new ErrorDetails(new Date(),ex.getMessage(),request.getDescription(false));
    return new ResponseEntity<>(errordetails,HttpStatus.FOUND);
}

}


