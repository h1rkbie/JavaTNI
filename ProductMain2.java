import java.util.Scanner;

public class ProductMain2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product product1 = new Product("Pens", 10.5, 50);
        Product product2 = new Product("Notebooks", 25.75, 20);

        System.out.println("Welcome to Mini Shop");
        for (int i = 0; i <= 30; i++) System.out.print("-");
        System.out.println("\nPress [1] to buy Pen");
        System.out.println("Press [2] to buy Notebook");
        System.out.print("Enter a number: ");
        int choice = sc.nextInt();

        while (choice != 1 && choice != 2) {
            System.out.print("Invalid number! Enter a number, again: ");
            choice = sc.nextInt();
        }

        if (choice == 1) {
            product1.showInfo();
            for (int i = 0; i <= 45; i++) System.out.print("-");
            System.out.print("\nHow many Pens do you want to buy? ");
//            int BuyPens = sc.nextInt();
            product1.sell(sc.nextInt());
            for (int i = 0; i <= 45; i++) System.out.print("-");
            product1.showInfo();
        }

        if (choice == 2) {
            product2.showInfo();
            for (int i = 0; i <= 45; i++) System.out.print("-");
            System.out.print("\nHow many Notebook do you want to buy? ");
//            int BuyNoteBook = sc.nextInt();
            product2.sell(sc.nextInt());
            for (int i = 0; i <= 45; i++) System.out.print("-");
            product2.showInfo();
        }


    }
}
