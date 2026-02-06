public class Bakery {
    private String flavor;
    private double unitPrice;
    private int packingCost = 5;

    Bakery(String flavor, double unitPrice) {
        this.flavor = flavor;
        this.unitPrice = unitPrice;
    }

    Bakery() {
        this("", 0.0);
    }

    String getFlavor() {
        return this.flavor;
    }
    double getUnitPrice() {
        return this.unitPrice;
    }

    int getPackingCost() {
        return this.packingCost;
    }

    double calculateTotalPrice() {
        return this.unitPrice * this.packingCost;
    }

    public String toString() {
        return "Thank you for your order!\n";
    }
}