package dto;

public enum PayrollStatus {
	  PENDING("Pending"),
	    APPROVED("Approved"),
	    REJECTED("Rejected"),
	    COMPLETED("Completed");

    private final String status;

    // Constructor to associate the string value with the enum
    PayrollStatus(String status) {
        this.status = status;
    }

    // Getter for the string value of the status
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
