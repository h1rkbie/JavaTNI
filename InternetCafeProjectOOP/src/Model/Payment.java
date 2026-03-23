package Model;

public class Payment {

    private String paymentId;
    private Session session;
    private double sessionFee;
    private double total;
    private boolean isPaid;

    private static int counter = 1;

    public Payment(Session session) {
        this.paymentId = "PAY" + counter++;
        this.session = session;
        this.sessionFee = session.CalculateFee();
        this.total = sessionFee;
        this.isPaid = false;
    }

    //Receive money -> return change (-1 if not enough money)
    public double pay(double amount) {
        if (amount < total) {
            System.out.printf("Not enough money! must pay %.2f Baht%n", total);
            return -1;
        }
        isPaid = true;

        double change = amount - total;
        System.out.printf("Successful Payment! Changes: %.2f Baht%n", change);
        return change;
    }

    public String getReceiptText() {
        StringBuilder sb = new StringBuilder();
        sb.append("===== RECEIPT =====\n");
        sb.append("Payment ID : ").append(paymentId).append("\n");
        sb.append("User       : ").append(session.getUser().getName()).append("\n");
        sb.append("Device     : ").append(session.getComputer().getComputerId()).append("\n");
        sb.append("Time       : ").append(session.getElapsedMinutes()).append(" Minutes\n");
        double disc = session.getUser().getDiscount();
        if (disc > 0)
            sb.append(String.format("Discount   : %.0f%%%n", disc * 100));
        sb.append(String.format("Total      : %.2f Baht\n", total));
        sb.append("===================");
        return sb.toString();
    }

    public double getTotal() {
        return total;
    }
    public boolean isPaid() {
        return isPaid;
    }
    public String getPaymentId() {
        return paymentId;
    }

    //Expose session to make ReportPanel able to show Username and Device
    public Session getSession() {
        return session;
    }
}
