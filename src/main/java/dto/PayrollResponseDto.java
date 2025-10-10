package dto;

import java.math.BigDecimal;

public class PayrollResponseDto {
    private Long id;
    private String month;
    private BigDecimal netSalary;
    private BigDecimal grossSalary;
    private BigDecimal deductions;
    private String status;
    
    private BigDecimal taxes;

    
    private String employeeName;   
    private boolean processed;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public BigDecimal getNetSalary() {
		return netSalary;
	}
	public void setNetSalary(BigDecimal netSalary) {
		this.netSalary = netSalary;
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
	public void setDeductions(BigDecimal deductions) {
		this.deductions = deductions;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public boolean isProcessed() {
		return processed;
	}
	public void setProcessed(boolean processed) {
		this.processed = processed;
	}
    public PayrollResponseDto() {}
    
    
	public BigDecimal getTaxes() {
		return taxes;
	}
	public void setTaxes(BigDecimal taxes) {
		this.taxes = taxes;
	}
	public PayrollResponseDto(Long id, String month, BigDecimal netSalary, BigDecimal grossSalary,
			BigDecimal deductions, String status, String employeeName, boolean processed) {
		super();
		this.id = id;
		this.month = month;
		this.netSalary = netSalary;
		this.grossSalary = grossSalary;
		this.deductions = deductions;
		this.status = status;
		this.employeeName = employeeName;
		this.processed = processed;
	}

    
}

