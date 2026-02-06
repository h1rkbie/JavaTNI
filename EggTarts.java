import java.util.Scanner;

public class EggTarts extends Bakery {
    private String SweetLevel; //ไส้ทาร์ต
    private int Piece;
    private String Topping;
    Scanner sc  = new Scanner(System.in);

    EggTarts(String SweetLevel, int piece, String Topping, String flavor, double unitPiece) {
        super(flavor,unitPiece);
        this.Topping = Topping;
        this.SweetLevel = SweetLevel;
        this.Piece = piece;
    }

    EggTarts() {
        this.Topping = "";
        this.SweetLevel = "";
        this.Piece = 0;
    }

    String getMincemeat() {
        return this.SweetLevel;
    }

    int getPiece () {
        return this.Piece;
    }

    String Topping () {
        return this.Topping;
    }

    boolean isPackingBox() {
        return this.Piece >= 3;
    }

    String BoxSize () {
        if (isPackingBox()) {
            return "USING BOX";
        } else return "USING BAG";
    }

    boolean getPromotion () {
        return this.Piece >= 3;
    }

    @Override
    double calculateTotalPrice() {
        if (getPromotion()) {
            return (getUnitPrice() * this.Piece) - (   (getUnitPrice() * this.Piece) / 10);
        } else return getUnitPrice() * this.Piece;
    }

    @Override
    public String toString() {
        return super.toString() + "EggTart Flavor (" + getFlavor() + ")\nTopping Picked : " + this.Topping +
                "\nPACKING USING : " + BoxSize() + "\nSweet Level : " + this.SweetLevel + "\n"+ (getPromotion() ? "==> OBTAIN PROMOTION" : "==> NO PROMOTION") + "\nTotal price of Egg Tarts = " + calculateTotalPrice();
    }

    String ShowAndSelectedFlavor () {

        String text = "\nPress 1 for Thai-Tea\nPress 2 for Matcha Premium\nPress 3 for Original Flavor" +
                "\nPress 4 for Chocolate\nPress 5 for Special Flavor\nPress 6 for Secret Flavor";
        System.out.println(text);
        System.out.print("Enter a flavor: ");
        int Selection = sc.nextInt();

        while (Selection <= 0 || Selection > 6 ) {
            System.out.print("Enter an option: ");
            Selection = sc.nextInt();
        }

        if (Selection == 1) {
            return "Thai-Tea";
        } else if (Selection == 2) {
            return "Matcha Premium";
        } else if (Selection == 3) {
            return "Original Flavor";
        } else if (Selection == 4) {
            return "Chocolate";
        } else if (Selection == 5) {
            return "Special Flavor";
        }  else  {
            return "Secret Flavor";
        }
    }
}