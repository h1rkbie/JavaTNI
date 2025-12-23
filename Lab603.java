import java.util.Scanner;
import java.util.Random;

public class Lab603 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int guess;
        int[] number = random_array();
        System.out.print("Enter your guess (0-20): ");
        guess = input.nextInt();
        System.out.println();
        int count = count_element(number, guess);
        if (count > 0) {
            System.out.println("Congratulations!! " + guess + " is an element in the array!!");

            if (count > 1) {
                System.out.println("Fantastic!! It also appears more than once!!");
            }
        } else {
            System.out.println("Sorry, try again next time...");
            display_array(number);
        }
    }

    static int[] random_array() {
        Random random = new Random();
        int[] numbers = new int[10];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(21);
        }
        return numbers;
    }

    static void display_array(int[] numbers) {
        System.out.print("Here are the element in the array: ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i]);
            if (i < numbers.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    static int count_element(int[] numbers, int element) {
        int count = 0;
        for (int _number : numbers) {
            if (_number == element) {
                count++;
            }
        }
        return count;
    }

}
