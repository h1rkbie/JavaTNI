import javax.swing.JOptionPane;

public class BookInventory {
    public static void main(String[] args) {
        Book[] books = new Book[3];
        for (int i = 0; i < books.length; i++) {
            String bookTitle = JOptionPane.showInputDialog(null, "Enter book title:");
            int TotalNumber = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the total number:"));
            books[i] = new Book(bookTitle, TotalNumber);
        }

        for (Book _book : books) {
            System.out.println(_book.getTitle() + " has " + _book.getTotalBook() + " books, can borrow " + _book.getAvailableBook() +
                    " books.");
        }

    }
}
