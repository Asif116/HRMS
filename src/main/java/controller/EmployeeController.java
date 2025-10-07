package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import entity.Employee;
import service.EmployeeService;

public class EmployeeController {
	
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmpoloyees();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee emp= employeeService.getEmployeeById(id);
		if(emp !=null) {
			return ResponseEntity.ok(emp);
		}else {
		return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
		Employee created= employeeService.createEmployee(employee);
		return ResponseEntity.ok(created);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
		Employee updated = employeeService.updateEmployee(id, employee);
		if(updated !=null) {
			return ResponseEntity.ok(updated);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
		boolean deleted = employeeService.deleteEmployee(id);
		if(deleted) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}