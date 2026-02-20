package Banking;

import java.io.*;
import java.util.Scanner;

public class OpenNewAccount extends BankAccount {

    public OpenNewAccount(String accId, double accBalance) {
        super(accId, accBalance);
    }

    public String recordAccount() {
        if (isExistingAccount(accId)) {
            return "This account has been created!!";
        }
        try (FileWriter writer = new FileWriter(filename, true)) {
            PrintWriter printWriter = new PrintWriter(writer);
            printWriter.write(toString());
            return "Created account Success!!";
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    public boolean isExistingAccount(String account_name) {
        try (Scanner sc = new Scanner(new File(filename))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");

                if (data[0].equals(accId)) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}