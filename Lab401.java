import javax.swing.JOptionPane;

public class Lab401 {
    public static void main(String[] args) {

        String FullName = JOptionPane.showInputDialog("Enter your full name:");
        FullName = FullName.trim();
        String[] parts = FullName.split(" ");

        while (parts.length < 2 )
        {
            FullName = JOptionPane.showInputDialog("Enter your full name:");
            FullName = FullName.trim();
            parts = FullName.split(" ");
            if (FullName == null) break;
        }

        String RealName = parts[0];
        String LastName = parts[parts.length - 1].toUpperCase();
        String FirstCharAtRealName = RealName.substring(0,1).toUpperCase() + RealName.substring(1);

        if (parts.length > 2)
        {
            String middleChar = "";
            for (int i = 1; i < parts.length - 1; i++) {
                middleChar += parts[i].toUpperCase();
            }
            JOptionPane.showMessageDialog(null, "Welcome, " + FirstCharAtRealName
            + " " + middleChar + " " + LastName);
        }
        else {
            JOptionPane.showMessageDialog(null, "Welcome, " + FirstCharAtRealName +
                    " " + LastName);
        }
    }
}
