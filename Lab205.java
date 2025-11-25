import javax.swing.JOptionPane;

public class Lab205 {
    public static void main(String[] args)
    {
        int time  = Integer.parseInt(JOptionPane.showInputDialog("Input minutes:"));

        int hour = time / 60;
        int minute = time % 60;

        JOptionPane.showMessageDialog(null, time + " minutes is " + hour + " hour " + minute + " minute"
        ,"Message" ,  JOptionPane.INFORMATION_MESSAGE);


    }
}
