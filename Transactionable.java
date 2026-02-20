package Banking;

import java.io.FileNotFoundException;

public interface Transactionable {
    void deposit(double amount) throws FileNotFoundException;
    void withdraw(double amount) throws FileNotFoundException;
    double checkBalance() throws FileNotFoundException;
}
