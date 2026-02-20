package Banking;

public class BankAccount {
    protected String accId;
    protected double accBalance;
    protected final String filename = "src/Banking/users_account.txt";

    public BankAccount(String accId, double accBalance) {
        this.accId = accId;
        this.accBalance = accBalance;
    }

    public BankAccount(String accId) {
        this.accId = accId;
        this.accBalance = 0.0;
    }

    public BankAccount() {
        this.accId = "";
        this.accBalance = 0.0;
    }

    public String getAccId() {
        return this.accId;
    }

   public void setAccBalance(double accBalance) {
        this.accBalance = accBalance;
    }

    public double getAccBalance() {
        return this.accBalance;
    }

    public String getFilename() {
        return this.filename;
    }

    public String toString() {
        return  accId + "," + accBalance + "\n";
    }

}
