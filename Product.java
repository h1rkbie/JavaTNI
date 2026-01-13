public class Product {
    String name;
    double price = 0;
    int quantity = 0;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    void addStock (int amount) {
        if (amount > 0) {
            this.quantity += amount;
            System.out.println("\nAdded " + amount + " items to stock");
        }
    }

    void sell (int amount) {
        if (amount <= this.quantity) {
            this.quantity -= amount;
            System.out.println("Sold " + amount + " items");
        } else {
            System.out.println("Not enough items in stock...");
        }
    }

    void changePrice(double newPrice) {
        if (newPrice > 0) {
            this.price = newPrice;
            System.out.println("Change price to " + newPrice + " baht");
        }
    }

    double stockValue () {
       return this.price * this.quantity;
    }

    void showInfo() {
        System.out.println("\nName  : " + this.name);
        System.out.println("Price : " + this.price + " Baht/Item");
        System.out.println("Stock : " + this.quantity + " Items");
        System.out.println("Stock Value: " + stockValue() + " Baht");
    }

}
