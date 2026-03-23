package Model;

public class Session {
    private String sessionID;
    private User user;
    private Computer computer;
    private long startTime;
    private long endTime;
    private boolean isActive;
    
    public Session(String sessionID, User user, Computer computer) {
        this.sessionID = sessionID;
        this.user = user;
        this.computer = computer;
        this.startTime = System.currentTimeMillis();
        this.isActive = true;
        
        computer.setInUse(); // Locked Device automatically
    }
    
    //if end session -> return device
    public void close() {
        this.endTime = System.currentTimeMillis();
        this.isActive = false;
        computer.setAvailable();
    }

    //Calculate ServiceFee (after discount)
    // at least 1 minute for prevent 0 baht when testing
    public double CalculateFee() {
        long endMinutes = isActive ? System.currentTimeMillis() : endTime;
        long usedSeconds = endMinutes - startTime;
        long usedMinutes = Math.max(usedSeconds / 60_000, 1);
        double hours = usedMinutes / 60.0;
        double rawCost = hours * computer.getRatePerHour();
        double discount = user.getDiscount();
        return Math.round(rawCost * (1 - discount) * 100.0) / 100.0;
    }

    // ElapsedTime (Display during Using)
    public long getElapsedMinutes() {
        long ms = (isActive ? System.currentTimeMillis() : endTime) - startTime;
        return Math.max(ms / 60_000, 1);
    }

    //Getters
    public String getSessionID() {
        return sessionID;
    }
    public User getUser() {
        return user;
    }
    public Computer getComputer() {
        return computer;
    }
    public boolean isActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "Session : " + sessionID +
        " | " + user.getName() + " | " + computer.getComputerId() +
        " | " + getElapsedMinutes() + " Minutes";
    }

}
