import javax.security.sasl.SaslClient;
import java.util.Scanner;

public class Lab308 {
    public static void main(String[] args)
    {
       Scanner input = new Scanner(System.in);
       int MAX = Integer.MIN_VALUE;
       int MIN = Integer.MAX_VALUE;
       int number = 0;
       int Even = 0, Odd = 0;

        System.out.print("How many number to input: ");
        int numberInput = input.nextInt();

        for (int i = 1; i <= numberInput; i++) {
            System.out.print("Enter number " + i + ": ");
            number = input.nextInt();

            if (number > MAX) MAX = number;
            if (number < MIN) MIN = number;
            if (number % 2 == 0) Even++;
            else Odd++;
        }
        System.out.println();

        System.out.println("Maximum = " + MAX);
        System.out.println("Minimum = " + MIN);
        System.out.println("Even number = " + Even);
        System.out.println("Odd  number = " + Odd);

    }
}
