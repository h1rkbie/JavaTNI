import java.util.Scanner;

class BirthdayCake extends Bakery {
    private String message;
    private double pound;
    Scanner input = new Scanner(System.in);

    BirthdayCake(String message, double pound, String flavor, double unitPrice) {
        super(flavor, unitPrice);
        this.message = message;
        this.pound = pound;
    }

    BirthdayCake() {
        this.message = "Please enter your choice";
        this.pound = 0;
    }


    String getMessage() {
        return this.message;
    }

    void changeMessage (String new_message) {
        this.message = new_message;
    }

    @Override
    int getPackingCost () {
        if (this.pound >= 3) return 10;
        else return super.getPackingCost();
    }

    @Override
    double calculateTotalPrice() {
        return getUnitPrice() * this.pound + getPackingCost();
    }

    @Override
    public String toString() {
        return super.toString() + getFlavor() + " birthday cake (message= " + getMessage() + ")" +
                "\nTotal price of Birthday Cake = " + calculateTotalPrice();
    }

    String selectFlavor() {
        String flavor;

        System.out.println("\nPress 1 for Chocolate\nPress 2 for Vanilla\nPress 3 for Strawberry\nPress 4 for Lemon" +
                "\nPress 5 for Red Velvet\nPress 6 for Matcha Premium (was added)");
        System.out.print("Enter an option: ");
        int Selection = input.nextInt();

        while (Selection <= 0 || Selection > 6 ) {
            System.out.print("Enter an option: ");
            Selection = input.nextInt();
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
            flavor = "Matcha Premium";
        }
        return flavor;
    }
}
