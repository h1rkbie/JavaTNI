import com.sun.security.jgss.GSSUtil;

import java.util.Scanner;

public class Lab504 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String Pass = "";
        String ConfirmPassword = "";

        while (true) {
            System.out.print("Create password: ");
            Pass = input.nextLine();

            if (check_password(Pass)) {
                break;
            } else {
                System.out.print("Invalid Password! ");
            }
        }
        System.out.println();

        while (true) {
            System.out.print("Enter confirm password: ");
            ConfirmPassword = input.nextLine();

            if (check_password(Pass, ConfirmPassword)) {
                break;
            } else {
                System.out.print("Password do not match! ");
            }
        }

        System.out.println("\nPassword set successfully!!");
    }

    static boolean check_password (String pass) {
        return pass.length() >= 8;
    }
    static boolean check_password (String pass, String confirm_pass) {
        return pass.equals(confirm_pass);
    }

}
