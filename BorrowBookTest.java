import javax.swing.JOptionPane;

public class BorrowBookTest {
    public static void main(String[] args) {
        Book book = new Book("C# Programming", 5);
        while (true) {
            int ConfirmChoice = JOptionPane.showConfirmDialog(null, "Do you want to borrow books?",
                    "Borrow Book", JOptionPane.YES_NO_OPTION);

            if (ConfirmChoice == 0) {
                if (book.getAvailableBook() > 0) {
                    book.borrowBook();
                    System.out.println("Borrowed 1 book, available " + book.getAvailableBook() + " books.");
                } else {
                    JOptionPane.showMessageDialog(null, "No books available to borrow...", "Warning Message",
                            JOptionPane.WARNING_MESSAGE);
                    break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "END PROGRAM");
                break;
            }
        }

    }
}
