import java.util.Scanner;

public class CakeOrder2 {
    static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Press 1 to order Birthday Cake");
        System.out.println("Press 2 to order Cup Cake");
        System.out.println("Press 3 to order EggTarts");
        System.out.print("Enter an option: ");
        int SelectOption = sc.nextInt();
        sc.nextLine();

        if (SelectOption != 1 && SelectOption != 2 && SelectOption != 3) {
            System.out.println("\nWrong option!! Try again!!");
        }

        if (SelectOption == 1) {
            BirthdayCake ShowAndOrder = new BirthdayCake();
            String CakeFlavorSelected = ShowAndOrder.selectFlavor();
            System.out.print("\nEnter a message: ");
            String MessageOnCake = sc.nextLine();
            System.out.print("How many pounds: ");
            double Pounds = sc.nextDouble();
            BirthdayCake ShowCakeOrdered = new BirthdayCake(MessageOnCake, Pounds, CakeFlavorSelected, 350);
            System.out.println();
            System.out.println(ShowCakeOrdered);
        }

        if (SelectOption == 2) {
            CupCake ShowList = new CupCake();
            String CupCakeFlavorSelected = ShowList.selectFlavor();
            System.out.print("\nHow many pieces: ");
            int pieces = sc.nextInt();
            CupCake ShowCupCakeOrdered = new CupCake(pieces, CupCakeFlavorSelected, 70);
            System.out.println();
            System.out.println(ShowCupCakeOrdered);
        }

        if (SelectOption == 3) {
            EggTarts ShowListEggTart = new EggTarts();
            String EggTartsFlavorSelected = ShowListEggTart.ShowAndSelectedFlavor();
            System.out.print("\nHow many piece : ");
            int EggTartPiece = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter Sweet Level (Extreme, Mid, Low): ");
            String level = sc.nextLine();
            System.out.print("Enter Topic on EggTart (Bubble, Crumble Biskit, Chocolate Sauce) : ");
            String Topping = sc.nextLine();
            EggTarts ShowEggTartsOrdered = new EggTarts(level, EggTartPiece, Topping ,EggTartsFlavorSelected, 95);
            System.out.println();
            System.out.println(ShowEggTartsOrdered);
        }
    }
}
