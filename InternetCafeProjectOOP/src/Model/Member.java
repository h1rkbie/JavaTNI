package Model;

public class Member extends User {
    private String memberType;
    private String password;

    private static int counter = 1;

    public Member(String name, String memberType, String password) {
        super("M:" + counter++, name);
        this.memberType = memberType.toUpperCase();
        this.password = password;
    }

    // scrutinize password when login
    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }

    // REGULAR MEMBER GOT 10%, VIP GOT 20%
    @Override
    public double getDiscount() {
        if (memberType.equalsIgnoreCase("VIP")) return 0.20;
        if (memberType.equalsIgnoreCase("REGULAR")) return 0.10;
        else return 0.0;
    }

    public String getMemberType() {
        return memberType;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "[" + memberType + "] " + name + " | ID: " + userid;
    }
}
