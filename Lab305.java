import java.util.Scanner;

public class Lab305 {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the first  number: ");
        int FirstNum = input.nextInt();
        System.out.print("Enter the second number: ");
        int SecondNum = input.nextInt();

        //  (Show all number)
        for (int i = FirstNum; i <= SecondNum; i++) System.out.print(i + " ");

        System.out.println();
        // Find EvenNumber
        for (int i = FirstNum; i <= SecondNum; i++) {
            if (i % 2 == 0) System.out.print(i + " ");
        }
    }
}
