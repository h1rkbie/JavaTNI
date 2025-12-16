import javax.swing.*;

public class Lab502 {
    public static void main(String[] args) {
       while(true) {
           int Check = JOptionPane.showConfirmDialog(null, "Do you want to check your score?",
                   "Check score", JOptionPane.YES_NO_OPTION);

           if (Check == JOptionPane.NO_OPTION) {
               JOptionPane.showMessageDialog(null,
                       "END PROGRAM!!");
               break;
           }
           if (Check == JOptionPane.YES_NO_OPTION) {
               int midterm = input_score("Enter midterm-score:", 45);
               int Final = input_score("Enter final-score:", 55);
               int totalScore = cal_score(midterm, Final);
               String Grade = find_grade(totalScore);

               JOptionPane.showMessageDialog(null, "You score is " + totalScore + "\n" +
                       "You get grade " + Grade);
           }
       }
    }

    static String find_grade(int score) {
        if (score >= 80 && score <= 100) return "A";
        else if (score >= 70 && score <= 79) return "B";
        else if (score >= 60 && score <= 69) return "C";
        else if (score >= 50 && score <= 59) return "D";
        else return "F";
    }

    static int cal_score(int midterm, int Final) {
        return midterm + Final;
    }

    static int input_score(String message, int limit_score) {
        int score;
        String InputMessage = message;

        while (true) {
            score = Integer.parseInt(JOptionPane.showInputDialog(InputMessage));

            if (score >= 0 && score <= limit_score) return score;
            else InputMessage = "Score must be in range between 0 and " + limit_score +
                    "\n" + message;
        }
    }

}
