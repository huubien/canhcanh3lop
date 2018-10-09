package vn.fujinet.employee.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.fujinet.employee.entity.Employee;

@Repository
public interface EmployeeService extends JpaRepository<Employee, Long>{
	
}
