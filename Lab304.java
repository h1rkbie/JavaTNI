import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Lab304 {
    public static void main(String[] args)
    {
        DecimalFormat df = new DecimalFormat("##.00");

        float WeightKG = Float.parseFloat(JOptionPane.showInputDialog("Enter your weight (kg):"));
        int heightCm = Integer.parseInt(JOptionPane.showInputDialog("Enter your height (cm.):"));

        float heightM = heightCm / 100f;
        double BMI = WeightKG / (heightM * heightM);

        String ResultBMI = "";

        if (BMI < 18.5) ResultBMI = "Underweight";
        else if (BMI >= 18.5 && BMI <= 24.9) ResultBMI = "Healthy Weight";
        else if (BMI >= 25 && BMI <= 29.9) ResultBMI = "Overweight";
        else ResultBMI = "Obese";

        JOptionPane.showMessageDialog(null, "Your BMI is " + df.format(BMI) +
                "\nBMI result is " + ResultBMI);
    }
}
