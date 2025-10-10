package dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PayrollDTO {
	public PayrollDTO() {
	}

	

	  private Long payrollId;
	    private Long employeeId;
	    private String employeeName;
	    private String month;
	    private BigDecimal grossSalary;
	    private BigDecimal deductions;
	    private BigDecimal netSalary;
	    private BigDecimal taxes;
	    private LocalDate payrollDate;
	    private PayrollStatus status;

	    //Constructor
		public PayrollDTO(Long payrollId, Long employeeId, String employeeName, String month, BigDecimal grossSalary,
				BigDecimal deductions, BigDecimal netSalary, BigDecimal taxes, LocalDate payrollDate,
				PayrollStatus status) {
			super();
			this.payrollId = payrollId;
			this.employeeId = employeeId;
			this.employeeName = employeeName;
			this.month = month;
			this.grossSalary = grossSalary;
			this.deductions = deductions;
			this.netSalary = netSalary;
			this.taxes = taxes;
			this.payrollDate = payrollDate;
			this.status = status;
		}
		//Getter & Setter
		
		public Long getPayrollId() {
			return payrollId;
		}
		public void setPayrollId(Long payrollId) {
			this.payrollId = payrollId;
		}
		public Long getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(Long employeeId) {
			this.employeeId = employeeId;
		}
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
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
		public void setDeductions(BigDecimal deductions) {
			this.deductions = deductions;
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
		public LocalDate getPayrollDate() {
			return payrollDate;
		}
		public void setPayrollDate(LocalDate payrollDate) {
			this.payrollDate = payrollDate;
		}
		public PayrollStatus getStatus() {
			return status;
		}
		public void setStatus(PayrollStatus status) {
			this.status = status;
		}
  //toString
		@Override
		public String toString() {
			return "PayrollDTO [payrollId=" + payrollId + ", employeeId=" + employeeId + ", employeeName="
					+ employeeName + ", month=" + month + ", grossSalary=" + grossSalary + ", deductions=" + deductions
					+ ", netSalary=" + netSalary + ", taxes=" + taxes + ", payrollDate=" + payrollDate + ", status="
					+ status + "]";
		} 
		
}
