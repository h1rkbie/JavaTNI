package CoffeeShop;

public class Frappuccino extends Drink {
    private boolean whipped;

    Frappuccino(String size, boolean whipped) {
        super("Frappuccino", 40, size);
        this.whipped = whipped;
    }

    @Override
    public double calculateFinalPrice() {
        double price = getBasePrice() + getSizeExtra();
        if (whipped) price += 15;
        return price;
    }

    @Override
    public String toString() {
        String whippedText = whipped ? "Add whipped 15 Baht" : "";
        return super.toString() + "\n" + (whipped ? whippedText + "\n" : "") +
                "Total price = " + calculateFinalPrice() + " Baht";
    }
}
