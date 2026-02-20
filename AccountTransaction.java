package Banking;

import java.io.*;
import java.util.*;

public class AccountTransaction extends BankAccount implements Transactionable {

    public AccountTransaction(String accId) {
        super(accId);

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data =  line.split(",");
                if (data[0].equals(accId)) {
                    this.accBalance = Double.parseDouble(data[1].trim());
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public boolean hasAccountId() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data =  line.split(",");
                String accountId = data[0];
                if (accountId.equals(accId)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public void deposit(double amount) throws FileNotFoundException {
        if (hasAccountId()) {
            double newBalance = accBalance + amount;
            setAccBalance(newBalance);
            updateBalanceInFile();
        }
    }

    @Override
    public void withdraw(double amount) throws FileNotFoundException {
        if (accBalance >= amount) {
            double newBalance = accBalance - amount;
            setAccBalance(newBalance);
            updateBalanceInFile();
        }
    }

    @Override
    public double checkBalance() {
        return accBalance;
    }

    private void updateBalanceInFile() throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String accountId = data[0];
                if (accountId.equals(accId)) {
                    lines.add(accId + "," + accBalance);
                } else {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (String line: lines) {
                writer.println(line);
            }
        }

    }
}