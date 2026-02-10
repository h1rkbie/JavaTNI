package CoffeeShop;

public abstract class Drink implements Priceable{
    private String drinkName;
    private double basePrice;
    private String size;

    public Drink(String drinkName, double basePrice, String size) {
        this.drinkName = drinkName;
        this.basePrice = basePrice;
        this.size = size;
    }

    String getDrinkName() {
        return this.drinkName;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    @Override
    public double getSizeExtra() {
        switch (size.toUpperCase()) {
            case "S":
                return 0;
            case "M":
                return 10;
            case "L":
                return 15;
            default:
                return -1;
        }
    }

    abstract double calculateFinalPrice();

    public String toString() {
        return "Your order: " + getDrinkName() + " (Size: " + size.toUpperCase() + ")";
    }
}