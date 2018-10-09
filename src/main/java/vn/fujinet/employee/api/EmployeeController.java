package vn.fujinet.employee.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import vn.fujinet.employee.entity.Employee;
import vn.fujinet.employee.reponsitory.EmployeeService;


@RestController
@RequestMapping("/api")
public class EmployeeController {
	public static Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeService employeeservice;
	
	@RequestMapping(value = "/employee/", method = RequestMethod.GET)
	public ResponseEntity<List<	Employee>> listAllContact(){
		List<Employee> listContact= employeeservice.findAll();
		if(listContact.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<	Employee>>(listContact, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public 	Employee findContact(@PathVariable("id") long id) {
		Employee contact= employeeservice.getOne(id);
		if(contact == null) {
			ResponseEntity.notFound().build();
		}
		return contact;
	}
	
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteContact(@PathVariable(value = "id") Long id) {
		Employee contact = employeeservice.getOne(id);
	    if(contact == null) {
	        return ResponseEntity.notFound().build();
	    }

	    employeeservice.delete(contact);
	    return ResponseEntity.ok().build();
	}
}
