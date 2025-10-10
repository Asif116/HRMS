package serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.PayrollStatus;
import entity.Employee;
import entity.Payroll;
import repository.EmployeeRepository;
import repository.PayrollRepository;
import service.PayrollService;
import service.PdfGeneratorService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PayrollServiceImpl implements PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    /**
     * Calculate and save payroll for a given employee and month.
     */
    @Override
    public Payroll calculatePayroll(Long employeeId, java.time.YearMonth month) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        BigDecimal baseSalary = employee.getSalary();
        BigDecimal tax = baseSalary.multiply(new BigDecimal("0.1"));
        BigDecimal netSalary = baseSalary.subtract(tax);

        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setMonth(month.toString()); // Save month as String (yyyy-MM)
        payroll.setGrossSalary(baseSalary);
        payroll.setTaxes(tax);
        payroll.setNetSalary(netSalary);
        payroll.setDeductions(BigDecimal.ZERO); // Assuming 0 for now
        payroll.setStatus(PayrollStatus.PENDING);
        payroll.setProcessed(false);

        return payrollRepository.save(payroll);
    }

    /**
     * Approve payroll by setting status.
     */
    @Override
    public Payroll approvePayroll(Long payrollId, Long approverId) {
        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        // TODO: Add approverId verification / role check

        payroll.setStatus(PayrollStatus.APPROVED);
        return payrollRepository.save(payroll);
    }

    /**
     * Get a single payroll for an employee and month.
     */
    @Override
    public Optional<Payroll> getPayrollForEmployee(Long employeeId, String month) {
        return payrollRepository.findByEmployee_IdAndMonth(employeeId, month);
    }

    /**
     * Get all payroll records for an employee and month.
     */
    @Override
    public List<Payroll> getAllPayrollsForEmployeeAndMonth(Long employeeId, String month) {
        return payrollRepository.findAllByEmployee_IdAndMonth(employeeId, month);
    }

    /**
     * Generate a PDF payslip from payroll record.
     */
    @Override
    public byte[] generatePayslipPdf(Long payrollId) {
        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        return pdfGeneratorService.generatePdf(payroll);
    }
    //CRUD BUSSINESS LOGIC
    
    @Override
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    @Override
    public Payroll getPayrollById(Long id) {
        return payrollRepository.findById(id).orElse(null);
    }

    @Override
    public Payroll createPayroll(Payroll payroll) {
        return payrollRepository.save(payroll);
    }
    @Override
    public Payroll updatePayroll(Long id, Payroll updatedPayroll) {
        Payroll existingPayroll = payrollRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payroll not found with id: " + id));
        
        // Update fields (example)
        existingPayroll.setMonth(updatedPayroll.getMonth());
        existingPayroll.setGrossSalary(updatedPayroll.getGrossSalary());
        existingPayroll.setDeductions(updatedPayroll.getDeductions());
        existingPayroll.setNetSalary(updatedPayroll.getNetSalary());
        existingPayroll.setTaxes(updatedPayroll.getTaxes());
        existingPayroll.setProcessed(updatedPayroll.isProcessed());
        existingPayroll.setStatus(updatedPayroll.getStatus());
        existingPayroll.setEmployee(updatedPayroll.getEmployee());

        return payrollRepository.save(existingPayroll);
    }


    @Override
    public boolean deletePayroll(Long id) {
        if (!payrollRepository.existsById(id)) {
            return false;
        }
        payrollRepository.deleteById(id);
        return true;
    }

}
