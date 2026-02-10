package CoffeeShop;
import java.util.Scanner;

public class CoffeeShop {
    public static Espresso orderEspresso() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a size: ");
        String size = sc.nextLine().toUpperCase();

        Espresso espresso = new Espresso(size);

        System.out.print("Press 'y' or 'Y' for adding a shot: ");
        String addShotChoice = sc.nextLine();

        if (addShotChoice.equalsIgnoreCase("y")) {
            System.out.print("How many shots for adding in Espresso: ");
            int shotCount = sc.nextInt();
            espresso.addShot(shotCount);
        }
        return espresso;
    }


    public static Frappuccino orderFrappuccino() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a size: ");
        String size = sc.nextLine().toUpperCase();

        System.out.print("Do you would like to add whipped cream [y/Y]? ");
        String whippedChoice = sc.nextLine();

        boolean whipped = whippedChoice.equalsIgnoreCase("Y");
        return new Frappuccino(size, whipped);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double totalPrice = 0;
        int espressoCount = 0;
        int frappuccinoCount = 0;

        String showText = "Press 1 for ordering Espresso\nPress 2 for ordering Frappuccino";

        System.out.println(showText);
        boolean KeepOrder = true;

        while (KeepOrder) {
            System.out.print("Enter an option: ");
            int option = sc.nextInt();
            sc.nextLine();

            if (option == 1) {
                Espresso espresso = orderEspresso();
                System.out.println(espresso.toString());
                totalPrice += espresso.calculateFinalPrice();
                espressoCount++;
            }

            if (option == 2 ) {
                Frappuccino frappuccino = orderFrappuccino();
                System.out.println(frappuccino.toString());
                totalPrice += frappuccino.calculateFinalPrice();
                frappuccinoCount++;
            }

            System.out.print("\nDo you want to order more [y/Y]? ");
            String Continue = sc.nextLine();

            if (!Continue.equalsIgnoreCase("y")) {
                KeepOrder = false;
            } else {
                System.out.println(showText);
            }
        }

        System.out.println("\nYou ordered " + espressoCount + " Espresso");
        System.out.println("You ordered " + frappuccinoCount + " Frappucino");
        System.out.println("Total Price = " + totalPrice);
    }
}