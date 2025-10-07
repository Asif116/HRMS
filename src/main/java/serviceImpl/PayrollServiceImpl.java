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
import java.time.YearMonth;
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

    @Override
    public Payroll calculatePayroll(Long employeeId, YearMonth month) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        // Example calculation logic
        BigDecimal baseSalary = employee.getSalary();
        BigDecimal tax = baseSalary.multiply(new BigDecimal("0.1"));
        BigDecimal netSalary = baseSalary.subtract(tax);

        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setMonth(month.toString());
        payroll.setGrossSalary(baseSalary);
        payroll.setTaxes(tax);
        payroll.setNetSalary(netSalary);
        payroll.setStatus(PayrollStatus.PENDING.name());

        return payrollRepository.save(payroll);
    }

    @Override
    public Payroll approvePayroll(Long payrollId, Long approverId) {
        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        // Add role/authorization check for approverId here

        payroll.setStatus(PayrollStatus.APPROVED.name());
        return payrollRepository.save(payroll);
    }

    @Override
    public Optional<Payroll> getPayrollForEmployee(Long employeeId, YearMonth month) {
        return payrollRepository.findByEmployee_IdAndMonth(employeeId, month);
    }

    @Override
    public List<Payroll> getAllPayrollsForMonth(YearMonth month) {
        return payrollRepository.findAllByMonth(month);
    }

    @Override
    public byte[] generatePayslipPdf(Long payrollId) {
        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new RuntimeException("Payroll not found"));

        return pdfGeneratorService.generatePdf(payroll);
    }
}
