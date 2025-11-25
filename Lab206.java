import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.function.ToLongBiFunction;

public class Lab206 {
    public static void main(String[] args)
    {
        final double BuffetPrice = 299;

        int customer = Integer.parseInt(JOptionPane.showInputDialog("How many customer?"));

        double SumPrice = customer * BuffetPrice;
        double NETPrice = (SumPrice * 7 ) / 100;
        double TotalPrice = NETPrice + SumPrice;

        DecimalFormat df = new DecimalFormat("#,###.00");

        int discountCoupon = Integer.parseInt(JOptionPane.showInputDialog("Price with NET is " + df.format(TotalPrice) +
         " baht." + "\nHow much of discount (%) on your coupon?"));

        double TotalPrice2 = TotalPrice - (TotalPrice / discountCoupon);

        double CUstomerPaid = Double.parseDouble(JOptionPane.showInputDialog(null, "Total price is " + df.format(TotalPrice2)
        + " baht.\nEnter the amount the customer paid:"));

        JOptionPane.showMessageDialog(null, "Total price is " + df.format(TotalPrice - (TotalPrice / discountCoupon)) + " baht\nCustomer paid " + df.format(CUstomerPaid) +" baht." +
                "\nGet change " + df.format( CUstomerPaid - TotalPrice2) + " baht.");
    }
}

