package service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import entity.Payroll;

public interface PayrollService {

	    Payroll calculatePayroll(Long employeeId, YearMonth month);
	    Payroll approvePayroll(Long payrollId, Long approverId);
	    Optional<Payroll> getPayrollForEmployee(Long employeeId, YearMonth month);
	    List<Payroll> getAllPayrollsForMonth(YearMonth month);
	    byte[] generatePayslipPdf(Long payrollId); // internally fetches payroll
	

}
 