import com.sun.security.jgss.GSSUtil;

import java.util.Scanner;

public class CakeOrder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Birthday Cake's Details:");
        System.out.print("Enter a message on cake: ");
        String MessageCake = sc.nextLine();
        System.out.print("Enter a flavor : ");
        String CakeFlavor = sc.nextLine();
        System.out.print("How many pound : ");
        double pound = sc.nextDouble();
        sc.nextLine();
        BirthdayCake order1 = new BirthdayCake(MessageCake, pound, CakeFlavor, 350);
        System.out.println(order1);
        System.out.println();

        System.out.println("Cup Cake's Details:");
        System.out.print("Enter a flavor : ");
        String CupCakeFlavor = sc.nextLine();
        System.out.print("How many piece : ");
        int piece = sc.nextInt();
        sc.nextLine();
        CupCake order2 = new CupCake(piece, CupCakeFlavor, 65);
        System.out.println(order2);
        System.out.println();

        System.out.println("Egg Tarts's Details:");
        System.out.print("Enter a mincemeat (MATCHA, THAI-TEA, ORIGINAL): ");
        String flavor = sc.nextLine();
        System.out.print("Enter Sweet Level (Extreme, Mid, Low): ");
        String level = sc.nextLine();
        System.out.print("Enter Topic on EggTart (Bubble, Crumble Biskit, Chocolate Sauce) : ");
        String Topping = sc.nextLine();
        System.out.print("How many piece : ");
        int EggTartPiece = sc.nextInt();
        EggTarts order3 = new EggTarts(level, EggTartPiece, Topping ,flavor, 95);
        System.out.println(order3);
        System.out.println();

        System.out.println("Total price  = " + (order1.calculateTotalPrice() + order2.calculateTotalPrice() + order3.calculateTotalPrice()));


    }
}