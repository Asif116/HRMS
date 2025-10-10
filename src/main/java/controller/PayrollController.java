package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dto.ApprovePayrollRequestDto;
import dto.PayrollRequestDto;
import dto.PayrollResponseDto;
import entity.Payroll;
import service.PayrollService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/payrolls")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;
    private static final Logger log = LoggerFactory.getLogger(PayrollController.class);

    /**
     * Calculate payroll for an employee and month using request body.
     */
    @PostMapping("/calculate")
    public ResponseEntity<Payroll> calculatePayroll(@RequestBody PayrollRequestDto request) {
        Payroll payroll = payrollService.calculatePayroll(request.getEmployeeId(), request.getMonth());
        return ResponseEntity.ok(payroll);
    }

    /**
     * Approve payroll by payrollId and approverId.
     */
    @PostMapping("/{payrollId}/approve")
    public ResponseEntity<Payroll> approvePayroll(
            @PathVariable Long payrollId,
            @RequestBody ApprovePayrollRequestDto request) {
        
        Payroll payroll = payrollService.approvePayroll(payrollId, request.getApproverId());
        return ResponseEntity.ok(payroll);
    }

    /**
     * Get payroll(s) for an employee for a specific month.
     */
    @PostMapping("/employee/payrolls")
    public ResponseEntity<List<PayrollResponseDto>> getPayrollForEmployee(@RequestBody PayrollRequestDto request) {
        String monthStr = request.getMonth().toString();
        List<Payroll> payrolls = payrollService.getAllPayrollsForEmployeeAndMonth(request.getEmployeeId(), monthStr);

        log.info("Found {} payroll records", payrolls.size());

        
        List<PayrollResponseDto> response = payrolls.stream().map(p -> {
            PayrollResponseDto dto = new PayrollResponseDto();
            dto.setId(p.getId());
            dto.setMonth(p.getMonth());
            dto.setGrossSalary(p.getGrossSalary());
            dto.setDeductions(p.getDeductions());
            dto.setNetSalary(p.getNetSalary());
            dto.setTaxes(p.getTaxes());
            dto.setProcessed(p.isProcessed());
            dto.setStatus(p.getStatus().name());
            dto.setEmployeeName(p.getEmployee().getName());  // ✅ safe now
            return dto;
        }).toList();
        log.info("Returning response with {} DTOs", response.size());

         
        return ResponseEntity.ok(response);
    }

    /**
     * Generate pay slip PDF for a given payroll ID.
     */
    @GetMapping("/{payrollId}/payslip")
    public ResponseEntity<byte[]> generatePayslipPdf(@PathVariable Long payrollId) {
        byte[] pdfBytes = payrollService.generatePayslipPdf(payrollId);
     
        log.info("Generated PDF size: " + (pdfBytes != null ? pdfBytes.length : 0) + " bytes");

        
        if (pdfBytes == null || pdfBytes.length == 0) {
            throw new RuntimeException("Generated PDF is empty");
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=payslip_" + payrollId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
    
    
    //CRUD OPERATION
    
    
 // Get all payrolls
    @GetMapping
    public ResponseEntity<List<Payroll>> getAllPayrolls() {
        List<Payroll> payrolls = payrollService.getAllPayrolls();
        return ResponseEntity.ok(payrolls);
    }

    // Get payroll by ID
    @GetMapping("/{id}")
    public ResponseEntity<Payroll> getPayrollById(@PathVariable Long id) {
        Payroll payroll = payrollService.getPayrollById(id);
        if (payroll == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payroll);
    }

    // Create new payroll
    @PostMapping
    public ResponseEntity<Payroll> createPayroll(@RequestBody Payroll payroll) {
        Payroll createdPayroll = payrollService.createPayroll(payroll);
        return ResponseEntity.ok(createdPayroll);
    }

    // Update payroll by ID
    @PutMapping("/{id}")
    public ResponseEntity<Payroll> updatePayroll(@PathVariable Long id, @RequestBody Payroll updatedPayroll) {
        Payroll payroll = payrollService.updatePayroll(id, updatedPayroll);
        if (payroll == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payroll);
    }
 
    // Delete payroll by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id) {
        boolean deleted = payrollService.deletePayroll(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
