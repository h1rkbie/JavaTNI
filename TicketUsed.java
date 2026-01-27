import javax.swing.JOptionPane;

public class TicketUsed {
    static String show_movie_list(Ticket... tickets) {
        String ListMovie = "";
        for (int i = 0; i < tickets.length; i++) {
            ListMovie += tickets[i].getTicket_id() + ": " + tickets[i].getMovie().toString() + "\n";
        }
        return ListMovie;
    }

    static String ShowLine() {
        String Line = "";
        for (int i = 0; i <= 45; i++) {
            Line += "-";
        }
        return Line;
    }

    public static void main(String[] args) {
        Ticket[] tickets = new Ticket[] {
                new Ticket("T001", new Movie("Titanic", 120,"PG-13"),"12:00", "B7", 280),
                new Ticket("T002", new Movie("Pokemon", 102, "PG"), "11:00", "G11", 240),
                new Ticket("T003", new Movie("Silent Hill", 125, "R-13"), "14:30", "H2", 240)
        };

        int ConfirmBox = JOptionPane.showConfirmDialog(null, "Do you want to book or use a ticket?", "Ticket", JOptionPane.YES_NO_OPTION);
        if (ConfirmBox == 1) {
            JOptionPane.showMessageDialog(null, "END PROGRAM!!");
        }

        while (true) {
            if (ConfirmBox == 0) {
                String InputTicketID = JOptionPane.showInputDialog(null, show_movie_list(tickets) + "\nEnter a ticket id for booking:");
                InputTicketID = InputTicketID.toUpperCase();

                Ticket SelectedTicket = null;
                for (Ticket tk : tickets) {
                    if (tk.getTicket_id().equalsIgnoreCase(InputTicketID)) {
                        SelectedTicket = tk;
                        break;
                    }
                }
                if (SelectedTicket != null) {
                    String ShowMovieSelected = SelectedTicket.getMovie().toString();

                    int PressChoice = Integer.parseInt(JOptionPane.showInputDialog(null, SelectedTicket.getTicket_id() + ": "+ SelectedTicket.getMovie().toString() + "\n\n" +
                            "Press 1 to book a ticket\nPress 2 to use a ticket\nPress 3 to see a ticket status\n\nEnter a menu:"));

                    if (PressChoice == 1) {
                        String Booking = SelectedTicket.bookTicket();
                        JOptionPane.showMessageDialog(null, Booking + "\n" + ShowLine() + "\n" +SelectedTicket.toString());
                    }

                    if (PressChoice == 2) {
                        String Utilize = SelectedTicket.useTicket();
                        JOptionPane.showMessageDialog(null, Utilize + "\n" + ShowLine() + "\n" + SelectedTicket.toString());
                    }
                    if (PressChoice == 3) {
                        JOptionPane.showMessageDialog(null, "STATUS" + "\n" + ShowLine() + "\n" + SelectedTicket.toString());
                    }
                }

                int ReturnBookOrUse = JOptionPane.showConfirmDialog(null, "Do you want to book or use other tickets?", "Ticket", JOptionPane.YES_NO_OPTION);
                if (ReturnBookOrUse == 0) {
                    continue;
                } else {
                    JOptionPane.showMessageDialog(null, "END PROGRAM!!");
                    break;
                }
            }
         }
    }
}