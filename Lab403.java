import javax.swing.JOptionPane;

public class Lab403 {
    public static void main(String[] args)
    {
      final String USERNAME = "admin",  PASSWORD = "Admin1234";

     while (true) {
         String username = JOptionPane.showInputDialog("Enter username:");
         String password = JOptionPane.showInputDialog("Enter password:");
         if (username == null || password == null) break;
         username = username.trim();
         password = password.trim();
         if (username.equalsIgnoreCase(USERNAME) && password.equals(PASSWORD)) {
             JOptionPane.showMessageDialog(null, "Login Success!!");
         } else {
             JOptionPane.showMessageDialog(null, "Login Fail...", "Incorrect username or password",
                     JOptionPane.WARNING_MESSAGE); continue;
         }
     }
    }
}
