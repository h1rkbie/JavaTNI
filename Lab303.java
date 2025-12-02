import java.util.Scanner;

public class Lab303 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the value for X coordinate: ");
        int X = input.nextInt();
        System.out.print("Enter the value for Y coordinate: ");
        int Y = input.nextInt();

        String quadrant = "";

        if (X > 0 && Y > 0) quadrant = "First";
        else if (X < 0 && Y < 0) quadrant = "Third";
        else if (X < 0 && Y > 0) quadrant = "Second";
        else if (X > 0 && Y < 0) quadrant = "Fourth";
        else if (X == 0 && Y == 0) {
            System.out.println("The coordinate point " + "(" + X + "," + Y + ")" + " lies in the Origin");
            return;
        }
        else if (X == 0) {
            System.out.println("The coordinate point (" + X + "," + Y + ") lies on the Y-axis");
            return;
        } else if (Y == 0) {
            System.out.println("The coordinate point (" + X + "," + Y + ") lies on the X-axis");
            return;
        }
        System.out.println("The coordinate point (" + X + "," + Y + ") lies in the " + quadrant + " quadrant");
    }
}
