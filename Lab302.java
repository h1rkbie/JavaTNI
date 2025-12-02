import javax.swing.JOptionPane;

public class Lab302 {
    public static void main(String[] args)
    {
       String name = JOptionPane.showInputDialog("Enter your name:");
       float heightCm = Float.parseFloat(JOptionPane.showInputDialog("Enter your height (cm.):"));
       int Gender = JOptionPane.showConfirmDialog(null, "Are your biological gender is Male?",
               "Gender", JOptionPane.YES_NO_OPTION);

       if (Gender == JOptionPane.YES_OPTION) {
           JOptionPane.showMessageDialog(null, "Hello, Mr." + name +
                   "\nIf your height is " + heightCm + " cm." +
                   "\nYour weight should be " + (heightCm - 100) + " kg.");
       }
       else if (Gender == JOptionPane.NO_OPTION) {
           Gender = JOptionPane.showConfirmDialog(null, "Are your biological gender is Female?", "Gender" ,
                   JOptionPane.YES_NO_OPTION);

           if (Gender == JOptionPane.YES_OPTION) {
               JOptionPane.showMessageDialog(null, "Hello, Ms." + name +
                       "\nIf your height is " + heightCm + " cm." +
                       "\nYour weight should be " + (heightCm - 110) + " kg.");
           }
           else {
               JOptionPane.showMessageDialog(null, "You can use BMI to measure your weight and height.");
           }
       }
    }
}
