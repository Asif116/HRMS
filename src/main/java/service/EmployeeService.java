 package service;

import java.util.List;

import entity.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmpoloyees();
	
	Employee getEmployeeById(Long id);
	
	Employee createEmployee(Employee employee);
	
	Employee updateEmployee(Long id, Employee updatedEmployee);
	
	boolean deleteEmployee(Long id);
	
}
