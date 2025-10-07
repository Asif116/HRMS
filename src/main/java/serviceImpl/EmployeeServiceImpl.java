package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import entity.Employee;
import repository.EmployeeRepository;
import service.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmpoloyees() {
		
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
		
		return employeeRepository.findById(id).orElse(null);
	}

	@Override
	public Employee createEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Long id, Employee updatedEmployee) {
		
		return employeeRepository.findById(id)
			    .map(emp -> {
                    emp.setEmployeeCode(updatedEmployee.getEmployeeCode());
                    emp.setDepartment(updatedEmployee.getDepartment());
                    emp.setDesignation(updatedEmployee.getDesignation());
                    emp.setDateOfJoining(updatedEmployee.getDateOfJoining());
                    emp.setPhone(updatedEmployee.getPhone());
                    emp.setEmail(updatedEmployee.getEmail());
                    emp.setSalary(updatedEmployee.getSalary());
                    emp.setUser(updatedEmployee.getUser()); 
                    return employeeRepository.save(emp);
			    })
			    .orElse(null);

	}

	@Override
	public boolean deleteEmployee(Long id) {
		if(employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
}