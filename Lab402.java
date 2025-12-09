import javax.swing.JOptionPane;

public class Lab402 {
    public static void main(String[] args)
    {
      String StudentID = JOptionPane.showInputDialog("Enter student-id:");
      StudentID = StudentID.trim();

      while (StudentID.length() != 10) {
          StudentID = JOptionPane.showInputDialog("Enter student-id:");
          StudentID = StudentID.trim();
      }
      String MajorCode = StudentID.substring(2, 5);
      String MajorName = "";
      if (MajorCode.equals("131")) {
          MajorName = "Information Technology (IT)";
      } else if (MajorCode.equals("132")) {
          MajorName = "Multimedia Technology (MT)";
      } else if (MajorCode.equals("133")) {
          MajorName = "Digital Business Information Technology (BI)";
      } else if (MajorCode.equals("134")) {
          MajorName = "Digital Technology in Mass Communication (DC)";
      } else if (MajorCode.equals("135")) {
          MajorName = "Data Science and Data Analytics (DS)";
      } else {
          MajorName = "Cannot found major";
      }
      JOptionPane.showMessageDialog(null, "Student ID : " + StudentID +
              "\nMajor: " + MajorName);
    }
}
