package CoffeeShop;

public class Espresso extends Drink {
    private int shot;

    Espresso(String size) {
        super("Espresso", 35, size);
        this.shot = 0;
    }

    void addShot(int shot) {
        this.shot += shot;
    }

    int getShot() {
        return this.shot;
    }

    @Override
    public double calculateFinalPrice() {
        return getBasePrice() + getSizeExtra() + (getShot() * 15);
    }

    @Override
    public String toString() {
        return super.toString() + "\nTotal price = " + this.calculateFinalPrice() + " Baht.";
    }
}