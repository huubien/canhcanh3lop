package vn.fujinet.employee.presentation.controller;
import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import vn.fujinet.employee.infrastructure.entity.EmployeeEntity;
import vn.fujinet.employee.infrastructure.repository.EmplopyeeRepository;
@RestController

public class EmployeeConrtroller {
	public static Logger logger = LoggerFactory.getLogger(EmployeeConrtroller.class);
	
	@Autowired
	EmplopyeeRepository employeereponsitory;

	
//Search Employee
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ResponseEntity<List<EmployeeEntity>> listAllContact(){
		List<EmployeeEntity> listEmployee= employeereponsitory.findAll();
		if(listEmployee.isEmpty()) {
			return new ResponseEntity<List<EmployeeEntity>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<	EmployeeEntity>>(listEmployee, HttpStatus.OK);
	}
//Detail Employee
	  @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	    public ResponseEntity<EmployeeEntity> getByEmployee(@PathVariable("id") long id) {
	        EmployeeEntity employee = employeereponsitory.getOne(id);
	        if (employee == null) {
	            return new ResponseEntity<EmployeeEntity>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<EmployeeEntity>(employee, HttpStatus.OK);
	    }
//Delete Employee
	  @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	  public ResponseEntity<EmployeeEntity> delEmployee(@PathVariable("id") long id) {
	        EmployeeEntity employee = employeereponsitory.getOne(id);
	        if (employee == null) {
	            return new ResponseEntity<EmployeeEntity>(HttpStatus.NOT_FOUND);
	        }
	        employeereponsitory.deleteById(id);
	        return new ResponseEntity<EmployeeEntity>(employee, HttpStatus.NO_CONTENT);
	    }
}

