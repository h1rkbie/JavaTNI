import javax.swing.JOptionPane;

public class ProductMain {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to Product Stock System");
        String ProductName = JOptionPane.showInputDialog(null, "Enter product name:");
        double ProductPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter product price per item:"));
        int ProductStock = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter product stock:"));

        Product product = new Product(ProductName, ProductPrice, ProductStock);
        product.showInfo();

        int ChoiceStock = JOptionPane.showConfirmDialog(null, "Do you want to add more item?", "Select an Option",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (ChoiceStock == JOptionPane.YES_OPTION) {
//            int AddItems = Integer.parseInt(JOptionPane.showInputDialog(null, "How many items to add in stock?"));
            product.addStock(Integer.parseInt(JOptionPane.showInputDialog(null, "How many items to add in stock?")));
        }

        int ChoicePrice = JOptionPane.showConfirmDialog(null, "Do you want to change price?", "Select an Option",
                JOptionPane.YES_NO_CANCEL_OPTION);

        if (ChoicePrice == JOptionPane.YES_OPTION) {
//            double AddPrice = Double.parseDouble(JOptionPane.showInputDialog(null, "How much is the new price per item?"));
            product.changePrice(Double.parseDouble(JOptionPane.showInputDialog(null, "How much is the new price per item?")));
        }
        product.showInfo();

    }
}
