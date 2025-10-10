package serviceImpl;

import entity.Payroll;
import org.springframework.stereotype.Service;
import service.PdfGeneratorService;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import java.io.ByteArrayOutputStream;
@Service
public class PdfGeneratorServiceImpl implements PdfGeneratorService {

    @Override
    public byte[] generatePdf(Payroll payroll) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Payslip"));
            document.add(new Paragraph("Employee: " + payroll.getEmployee().getName()));
            document.add(new Paragraph("Month: " + payroll.getMonth()));
            document.add(new Paragraph("Net Pay: " + payroll.getNetSalary()));

            document.close(); // very important to finalize the PDF

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
