import javax.swing.JOptionPane;

public class CakeOrder3 {
    static void main(String[] args) {

        while (true) {
            double pounds = Double.parseDouble(JOptionPane.showInputDialog("How many pounds do you want?"));
            String FlavorCake = JOptionPane.showInputDialog("Enter a flavor birthday cake:");
            String MessageOnCake = JOptionPane.showInputDialog("Enter a message on cake:");
            BirthdayCake Order = new BirthdayCake(MessageOnCake, pounds, FlavorCake, 400);
            String ShowMessage = Order.toString();
            int ConfirmOrder = JOptionPane.showConfirmDialog(null, ShowMessage + "\n\nConfirm this order?", "Select an Option",
                    JOptionPane.YES_NO_CANCEL_OPTION);

            if (ConfirmOrder == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, ShowMessage);
                break;
            }
        }
    }
}
