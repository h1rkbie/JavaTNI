import javax.swing.JOptionPane;

public class Lab506 {
    public static void main(String[] args) {
        int number;
        do {
            number = Integer.parseInt(JOptionPane.showInputDialog("Enter an integer: "));
        } while (number <= 1);

        boolean PrimeNumber = is_prime_number(number);
        if (PrimeNumber) {
            JOptionPane.showMessageDialog(null, number + " is Prime number");
        } else {
            JOptionPane.showMessageDialog(null, number + " is NOT Prime number");
        }
    }

    static boolean is_prime_number (int number) {
        if (number <= 1) return false;
        if (number == 2 ) return true;
        if (number % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }

}
