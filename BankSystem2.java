package Banking;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class BankSystem2 {
    static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your bank account: ");
        String BankAccount = input.nextLine();

        AccountTransaction account = new AccountTransaction(BankAccount);

        if (!account.hasAccountId()) {
            System.out.println("\nBank account not found...");
            return;
        }

        System.out.println();
        String menu = "Press 1 to deposit\nPress 2 to withdraw\nPress 3 to check balance\n" +
                "Press 4 to exit\n";
        System.out.println(menu);
        int selection;

        while (true) {
          System.out.print("Enter a menu: ");
          selection = input.nextInt();

          if (selection == 1) {
              System.out.print("Enter amount to deposit: ");
              double amount = input.nextDouble();
              account.deposit(amount);
              System.out.println("Your balance = " + account.checkBalance());
              System.out.println();
          }

          if (selection == 2) {
              System.out.print("Enter amount to withdraw: ");
              double amount = input.nextDouble();
              account.withdraw(amount);
              System.out.println("Your balance = " + account.checkBalance());
              System.out.println();
          }

          if (selection == 3) {
              System.out.println("Your balance = " + account.checkBalance());
              System.out.println();
          }

          if (selection == 4) {
              break;
          }
      }
    }
}
