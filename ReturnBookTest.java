import javax.swing.JOptionPane;

public class ReturnBookTest {
    public static void main(String[] args) {
        Book book = new Book("C# Programming", 5);
        while(true) {
            int BookBorrowConfirm = JOptionPane.showConfirmDialog(null, "Do you want to borrow/return book?",
                    "Borrow/Return Book", JOptionPane.YES_NO_OPTION);
            if (BookBorrowConfirm == 0) {
                int PressBotton = Integer.parseInt(JOptionPane.showInputDialog(null, "Press 1 to borrow book\nPress 2 to return book"));
                if (PressBotton == 1) {
                    if (book.getAvailableBook() > 0) {
                        book.borrowBook();
                        System.out.println("Borrowed 1 book, available " + book.getAvailableBook() + " books.");

                    } else {
                        JOptionPane.showMessageDialog(null, "No books available to borrow...", "Warning Message",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                if (PressBotton == 2) {
                    if (book.getAvailableBook() == book.getTotalBook()) {
                        JOptionPane.showMessageDialog(null, "Cannot return! All books ara already here.", "Warning Message",
                                JOptionPane.WARNING_MESSAGE);
                    } else {
                        book.returnBook();
                        System.out.println("Returned 1 book, available " + book.getAvailableBook() + " books.");
                    }
                }
                if (PressBotton != 1 && PressBotton != 2) {
                    JOptionPane.showMessageDialog(null, "END PROGRAM");
                    break;
                }
            }
            if (BookBorrowConfirm == 1) {
                JOptionPane.showMessageDialog(null, "END PROGRAM");
                break;
            }
        }
    }
}
