import java.util.Scanner;

public class ProductMain4 {
    public static void setting_product (Product... products) {
        String[] names = {"Pens", "Pencils", "Markers", "Highlighters"};
        double[] prices = {25.5, 17.25, 30, 35};
        int[] quantities = {20, 35, 10, 40};

        for (int i = 0; i < products.length; i++) {
            products[i] = new Product(names[i], prices[i], quantities[i]);
            products[i].name = names[i];
            products[i].price = prices[i];
            products[i].quantity = quantities[i];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] product = new Product[4];
        setting_product(product);
        System.out.println("Welcome to MINI SHOP!!");
        for (int i = 0 ; i <= 45; i++ ) System.out.print("-");
        System.out.println("\nPress 1 to buy Pens (items=20)");
        System.out.println("Press 2 to buy Pencils (items=35)");
        System.out.println("Press 3 to buy Markers (items=10)");
        System.out.println("Press 4 to buy Highlighters (items=40)");
        for (int i = 0 ; i <= 45; i++ ) System.out.print("-");

        System.out.print("\nEnter a number: ");
        int Choice = sc.nextInt();
        while (Choice < 1 || Choice > 4) {
            System.out.print("Invalid!! Enter a number: ");
            Choice = sc.nextInt();
        }
        for (int i = 0 ; i <= 45; i++ ) System.out.print("-");

        Product SelectedProduct = product[Choice - 1];
        System.out.print("\nHow many " + SelectedProduct.name + " do you want to buy? ");
        int DesireAmount = sc.nextInt();
        if (DesireAmount > SelectedProduct.quantity) {
            System.out.println("Not enough items in stock...");
        } else {
            SelectedProduct.sell(DesireAmount);
        }
        SelectedProduct.showInfo();
    }
}
