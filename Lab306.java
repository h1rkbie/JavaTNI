import javax.swing.JOptionPane;

public class Lab306 {
    public static void main(String[] args)
    {
        int num = Integer.parseInt(JOptionPane.showInputDialog("Enter an integer number:"));
        int sum = 0;
        for (int i = 0; i <= num; i++) sum += i;
        JOptionPane.showMessageDialog(null, "Summation of 1 to " + num + " is " + sum);
    }
}
