package service;

import entity.Payroll;

public interface PdfGeneratorService {

    byte[] generatePdf(Payroll payroll);

}
