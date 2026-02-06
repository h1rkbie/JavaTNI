import java.util.Scanner;

class CupCake extends Bakery {
    private int piece;
    Scanner sc = new Scanner(System.in);

    CupCake(int piece, String flavor, double unitPrice) {
        super(flavor, unitPrice);
        this.piece = piece;
    }

    CupCake () {
        this.piece = 0;
    }

    boolean isPackingBox() {
        return this.piece >= 6;
    }

    int getBoxNumber() {
        if(isPackingBox()) return this.piece / 6;
        return 0;
    }

    int getBagNumber() {
        return this.piece % 6;
    }

    @Override
    int getPackingCost() {
        if (isPackingBox()) {
            return getBoxNumber() * super.getPackingCost();
        }
        return 0;
    }

    @Override
    double calculateTotalPrice() {
        return (getUnitPrice() * this.piece) + getPackingCost() + (getBagNumber() * 0.5);
    }

    @Override
    public String toString() {
        return super.toString() + "Cup cake (" + getFlavor() + ") with " + (isPackingBox() ? getBoxNumber() : 0) + " Box " + getBagNumber() + " Bag" +
                "\nTotal price of Cup Cake = " + calculateTotalPrice();
    }

    String selectFlavor() {
        String flavor;
        String text = ("\nPress 1 for Chocolate\nPress 2 for Vanilla\nPress 3 for Strawberry\nPress 4 for Lemon" +
                "\nPress 5 for Red Velvet\nPress 6 for Thai-Tea (was added)");
        System.out.println(text);
        System.out.print("Enter an option: ");
        int Selection = sc.nextInt();

        while (Selection <= 0 || Selection > 6 ) {
            System.out.print("Enter an option: ");
            Selection = sc.nextInt();
        }

        if (Selection == 1) {
            flavor = "Chocolate";
        }else if (Selection == 2) {
            flavor =  "Vanilla";
        } else if (Selection == 3) {
            flavor = "Strawberry";
        }  else if (Selection == 4) {
            flavor = "Lemon";
        }  else if (Selection == 5) {
            flavor = "Red Velvet";
        }  else {
            flavor = "Thai-Tea";
        }
        return flavor;
    }
}