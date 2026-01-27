import javax.swing.JOptionPane;

public class TicketInfo {
    static String select_showtime(Movie movie) {
        int ChoicePress = Integer.parseInt(JOptionPane.showInputDialog(null, movie.toString() + "\n" +
                "Press 1 to select show time = 13:00\nPress 2 to select show time = 14:30\n" +
                "Press 3 to select show time = 15:00\nEnter a number:"));

        if (ChoicePress == 1) {
            return "13:00";
        } else if (ChoicePress == 2) {
            return "14:30";
        } else if (ChoicePress == 3) {
            return "15:00";
        } else {
            return "Error time";
        }
    }

    static String select_seat_number () {
        String SelectRow = "";
        String NumberSeat = "";

        while (true) {
            SelectRow = JOptionPane.showInputDialog(null, "Select seat row [A-G]:");
            SelectRow = SelectRow.toUpperCase();

            if (SelectRow.length() == 1 && SelectRow.charAt(0) >= 'A' && SelectRow.charAt(0) <= 'G') {
                break;
            }
        }
        while (true) {
            NumberSeat = JOptionPane.showInputDialog(null, "Select seat number [1-12]:");
            int seat = Integer.parseInt(NumberSeat);

            if (seat >= 1 && seat <= 12) {
                break;
            }
        }
        return SelectRow + NumberSeat;
    }

    public static void main(String[] args) {
        Movie movie = new Movie("Titanic", 120, "PG-13");
        String show_time =  select_showtime(movie);
        String seat_number = select_seat_number();
        Ticket ticket = new Ticket("T001", movie, show_time, seat_number, 240);
        JOptionPane.showMessageDialog(null, ticket);

    }
}