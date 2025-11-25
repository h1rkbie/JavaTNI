import com.sun.security.jgss.GSSUtil;

import java.util.Scanner;

public class Lab202 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number 1 : ");
        int num1 = input.nextInt();

        System.out.print("Enter number 2 : ");
        int num2 = input.nextInt();

        System.out.println();

        System.out.println("Summation = " + (num1 + num2));
        System.out.println("Subtraction = " + (num1 - num2));
        System.out.println("Multiplication = " + (num1 * num2));
        System.out.println("Division = " + ((float)num1 / num2));
        System.out.println("Modulus = " + (num1 % num2));


    }
}
