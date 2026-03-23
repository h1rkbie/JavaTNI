package Model;

public abstract class User {
    protected String userid;
    protected String name;

    public User(String userid, String name) {
        this.userid = userid;
        this.name = name;
    }

    //Getters
    public String getUserid() {
        return this.userid;
    }
    public String getName() {
        return this.name;
    }

    //abstract for subclass that gotta override
    public abstract double getDiscount();

    @Override
    public String toString() {
        return "[User] : " + name + " (ID: " + userid + ")";
    }
}
