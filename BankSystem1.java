package Banking;

import java.util.Scanner;

public class BankSystem1 {
    static Scanner sc = new Scanner(System.in);

    public static String input_account_id() {
        String accId;
        while (true) {
            System.out.print("Enter account id: ");
            accId = sc.nextLine();
            if (accId.length() == 10) {
                return accId;
            } else {
                System.out.print("Enter account id: ");
                sc.nextLine();
            }
            System.out.println();
        }
    }

    public static double input_initial_balance() {
        while (true) {
            try {
                System.out.print("Enter your initial deposit amount: ");
                return  Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Try again!! ");
            }
        }
    }

    static void main(String[] args) {
        String account_bank = input_account_id();
        System.out.println();
        double initial_balance = input_initial_balance();

        System.out.println();

        OpenNewAccount account = new OpenNewAccount(account_bank, initial_balance);
        String result = account.recordAccount();
        System.out.println(result);
    }
}