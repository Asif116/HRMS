package dto;

import java.time.YearMonth;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PayrollRequestDto {

    private Long employeeId;
    @JsonFormat(pattern = "yyyy-MM")
    private YearMonth month;

    // Getters & Setters
    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public YearMonth getMonth() {
        return month;
    }

    public void setMonth(YearMonth month) {
        this.month = month;
    }
}
