package Model;

public class Computer {
    private String computerId;
    private String status;
    private double ratePerHour;

    public Computer(String computerId, double ratePerHour) {
        this.computerId = computerId;
        this.ratePerHour = ratePerHour;
        this.status = "AVAILABLE";
    }

    // Check for ready to use or no
    public boolean isAvailable() {
        return status.equals("AVAILABLE");
    }

    public void setInUse() {
        this.status = "IN_USE";
    }
    public void setAvailable() {
        this.status = "AVAILABLE";
    }

    //Getters
    public String getComputerId() {
        return computerId;
    }
    public String getStatus() {
        return status;
    }
    public double getRatePerHour() {
        return ratePerHour;
    }

    @Override
    public String toString() {
        return String.format("%-8s | %-10s | %.0f Baht./Hours", computerId, status, ratePerHour);
    }
}
