package service;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import entity.Payroll;

public interface PayrollService {

  Payroll calculatePayroll(Long employeeId, YearMonth month);
  Payroll approvePayroll(Long payrollId, Long approverId);
  Optional<Payroll> getPayrollForEmployee(Long employeeId, String month);
  List<Payroll> getAllPayrollsForEmployeeAndMonth(Long employeeId,String month);
  byte[] generatePayslipPdf(Long payrollId); // internally fetches payroll
  
  // --- CRUD methods ---
  List<Payroll> getAllPayrolls();
  Payroll getPayrollById(Long id);
  Payroll createPayroll(Payroll payroll);
  Payroll updatePayroll(Long id, Payroll updatedPayroll);
  boolean deletePayroll(Long id);


}
 