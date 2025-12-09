import java.util.Scanner;

public class Lab404 {
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        String text = "";

        while (true) {
            System.out.print("Enter word: ");
            String word = input.nextLine();
            word = word.trim();
            if (word.equalsIgnoreCase("stop")) break;
            if (text.isEmpty()) {
                text = word;
            } else {
                text = text + " " + word;
            }
        }
        System.out.println(text);
    }
}
