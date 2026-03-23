package Model;// ==================================================
// Model.Guest.java — general user doesn't register membership
// No any discount
// ==================================================

public class Guest extends User {

    //Count amount guest automatically
    private static int counter = 1;

    public Guest(String name ) {
        super("G:" + counter++, name);
    }

    //Not any discount for guest
    @Override
    public double getDiscount() {
        return 0.0;
    }

    @Override
    public String toString() {
        return "[Guest] " + name + " (ID: " + userid + ")";
    }
}
