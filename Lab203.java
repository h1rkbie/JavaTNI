import java.util.Scanner;
import java.text.DecimalFormat;

public class Lab203 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter product id : ");
        String productID = input.nextLine();
        System.out.print("Enter product name: ");
        String productName = input.nextLine();

        System.out.print("Enter product item: ");
        int productItem = input.nextInt();
        System.out.print("Enter price per piece: ");
        double PricePerPiece = input.nextDouble();
        System.out.println("---------------------------------------");

        DecimalFormat df = new DecimalFormat("#,###.00");

        System.out.println("Product name: " + "Notebook " + "(" + productID + ")");
        System.out.println("Product item: " + productItem + " (" + PricePerPiece + " baht/piece)");
        System.out.println("Total price : " + df.format(productItem * PricePerPiece) + " baht");



    }
}
