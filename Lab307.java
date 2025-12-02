import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Lab307 {
    public static void main(String[] args)
    {
        DecimalFormat df = new DecimalFormat("#,###.0");
        int InputPrice;
        int sum = 0;

        while (true){
            InputPrice = Integer.parseInt(JOptionPane.showInputDialog("Input price [press 0 to stop]:"));

            if (InputPrice == 0) break;
            while (InputPrice < 0) {
                InputPrice = Integer.parseInt(JOptionPane.showInputDialog("Invalid price!!\nInput price [press 0 to stop]"));
            }
            sum += InputPrice;
        }
        JOptionPane.showMessageDialog(null, "Total price is " + df.format(sum) + " baht.");

    }
}
