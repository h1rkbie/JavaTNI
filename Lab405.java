import java.util.Scanner;

public class Lab405 {
    public static void main(String[] args)
    {
      Scanner input = new Scanner(System.in);

        System.out.print("Input some sentence: ");
        String sentence = input.nextLine();
        while (sentence.charAt(sentence.length() - 1) != '.')
        {
            System.out.print("The sentence must end with full stop point: ");
            sentence = input.nextLine();
        }
        System.out.println();

        sentence = sentence.trim();
        String[] parts = sentence.split(" ");

        for (int i = 0; i <= parts.length - 1; i++) {
            System.out.println(parts[i]);
        }
    }
}
