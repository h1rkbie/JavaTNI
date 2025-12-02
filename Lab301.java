import java.util.Scanner;

public class Lab301 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer number: ");
        int num = input.nextInt();

        if (num == 0) System.out.println("This is Zero number.");
        else {
            String evenOdd = (num % 2 == 0) ? "Even" : "Odd";
            String PosAndNeg = (num > 0) ? "Positive" : "Negative";
            System.out.println("This number is " + evenOdd + " and " + PosAndNeg + " number.");
        }


    }
}
