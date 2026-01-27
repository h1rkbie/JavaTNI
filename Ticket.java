public class Ticket {
    private String ticket_id;
    private Movie movie;
    private String show_time;
    private String seat_number;
    private double price;
    private boolean is_booked;
    private boolean is_used;

    Ticket(String ticket_id, Movie movie, String show_time, String seat_number, double price) {
        this.ticket_id = ticket_id;
        this.movie = movie;
        this.show_time = show_time;
        this.seat_number = seat_number;
        this.price = price;
    }
    Ticket() {
        this.ticket_id = "";
        this.movie = null;
        this.show_time =  "";
        this.seat_number = "";
        this.price = 0;
    }

    String getTicket_id() {
        return this.ticket_id;
    }

    Movie getMovie() {
        return this.movie;
    }

    String bookTicket() {
        if (is_booked) {
            return "This ticket is already booked";
        } else {
            is_booked = true;
            return "Ticket booked successfully!";
        }
    }

    String useTicket() {
        if (!is_booked) {
            return "Please book the ticket first";
        } else if (is_used) {
            return "Ticket already used";
        } else {
            is_used = true;
            return "Enjoy the movie!";
        }
    }

    public String toString () {
        String bookedStatus = is_booked ? "Yes" : "No";
        String usedStatus = is_used ? "Yes" : "No";

        return "Ticket Id: " + ticket_id + "\n" +
                "Movie: " + movie.toString() + "\n" +
                "Show Time: " + show_time + "\n" +
                "Seat Number: " + seat_number + "\n" +
                "Ticket Price: " + price + "\n" +
                "Booked: " + bookedStatus + "\n" +
                "Used: " + usedStatus;
    }

}
