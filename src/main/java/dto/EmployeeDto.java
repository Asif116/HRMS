package dto;

import java.time.LocalDate;

public class EmployeeDto {
	
	
		private Long id;
		
		private long userId;
		
		private String employeeCode;
		
		private String department;
	    
	    private String designation;
	    
	    private LocalDate dateOfJoining;
	    
	    private String phone;
	    
	    private String email;
	    
	    private double salary;
	    
	    public EmployeeDto() {}
	    
	    public EmployeeDto(Long id, Long userId, String employeeCode, String department,String designation,
	    		LocalDate dateOfJoining, String phone, String email, double salary) {
	    	
	    	this.id= id;
	    	this.userId= userId;
	    	this.employeeCode= employeeCode;
	    	this.department= department;
	    	this.designation=designation;
	    	this.dateOfJoining= dateOfJoining;
	    	this.phone=phone;
	    	this.email=email;
	    	this.salary=salary;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public long getUserId() {
			return userId;
		}

		public void setUserId(long userId) {
			this.userId = userId;
		}

		public String getEmployeeCode() {
			return employeeCode;
		}

		public void setEmployeeCode(String employeeCode) {
			this.employeeCode = employeeCode;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getDesignation() {
			return designation;
		}

		public void setDesignation(String designation) {
			this.designation = designation;
		}

		public LocalDate getDateOfJoining() {
			return dateOfJoining;
		}

		public void setDateOfJoining(LocalDate dateOfJoining) {
			this.dateOfJoining = dateOfJoining;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}
	    

}
