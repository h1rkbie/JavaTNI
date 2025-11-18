import javax.swing.JOptionPane;

public class BonusPoint {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,
                "Summation number = " + (2+5) +
                        "\nSubtraction number = " + (2 - 5) +
                        "\nMultiplication number = " + (2 * 5) +
                        "\nDivision number = " +((float) 2 / 5) +
                        "\nModulus number = " + (2 % 5) ,
                "Arithmetic Operators", JOptionPane.WARNING_MESSAGE);
    }
}
