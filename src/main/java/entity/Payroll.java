package entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import dto.PayrollStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor  
public class Payroll {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;    
	
	@ManyToOne
	@JsonIgnore  
    private Employee employee;

	private  String month;
	
	private BigDecimal grossSalary;
	
	private BigDecimal deductions;
	
	private BigDecimal netSalary;
	
    private BigDecimal taxes;

	private boolean processed;
	
	@Enumerated(EnumType.STRING) 
    private PayrollStatus status;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public BigDecimal getGrossSalary() {
		return grossSalary;
	}


	public void setGrossSalary(BigDecimal grossSalary) {
		this.grossSalary = grossSalary;
	}


	public BigDecimal getDeductions() {
		return deductions;
	}


	public void setDeductions(BigDecimal dedections) {
		this.deductions = dedections;
	}


	public BigDecimal getNetSalary() {
		return netSalary;
	}


	public void setNetSalary(BigDecimal netSalary) {
		this.netSalary = netSalary;
	}


	public BigDecimal getTaxes() {
		return taxes;
	}


	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}


	public boolean isProcessed() {
		return processed;
	}


	public void setProcessed(boolean processed) {
		this.processed = processed;
	}


	public PayrollStatus getStatus() {
		return status;
	}


	public void setStatus(PayrollStatus approved) {
		this.status = approved;
	}
   
}
