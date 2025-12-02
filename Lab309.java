import javax.swing.JOptionPane;

public class Lab309 {
    public static void main(String[] args) {

        boolean continueProgram = true;

        while (continueProgram) {
            int Respone = JOptionPane.showConfirmDialog(null, "Do you want to order menu?", "Yakitori Shop",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (Respone == JOptionPane.NO_OPTION) continueProgram = false;
            else {
                int totalPrice = 0;
                boolean ordering = true;

                while (ordering) {
                    String menu = "Yakitori Menu" + "\n[1] Chicken Wing 99 B." + "\n[2] Pork with Leek 89 B." +
                            "\n[3] Beef Tongue 109 B." + "\n[0] Exit and Calculate" + "\nEnter menu number:";
                    String input = JOptionPane.showInputDialog(null, menu);
                    if (input == null) break;
                    int MenuNumber = Integer.parseInt(input);

                    if (MenuNumber == 1) totalPrice += 99;
                    else if (MenuNumber == 2) totalPrice += 89;
                    else if (MenuNumber == 3) totalPrice += 109;
                    else if (MenuNumber == 0) ordering = false;
                }
                JOptionPane.showMessageDialog(null, "Total price is " + totalPrice + " Baht.",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
