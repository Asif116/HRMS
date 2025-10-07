package entity;

import java.math.BigDecimal;

import dto.PayrollStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor 
@NoArgsConstructor  
public class Payroll {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;    
	
	@ManyToOne
    private Employee employee;

	private  String month;
	
	
	private BigDecimal grossSalary;
	
	private BigDecimal dedections;
	
	private BigDecimal netSalary;
	
    private BigDecimal taxes;

	private boolean processed;
	
	@Enumerated(EnumType.STRING) 
    private String status;


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


	public BigDecimal getDedections() {
		return dedections;
	}


	public void setDedections(BigDecimal dedections) {
		this.dedections = dedections;
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


	public String getStatus() {
		return status;
	}


	public void setStatus(String approved) {
		this.status = approved;
	}
    
    
    
}
