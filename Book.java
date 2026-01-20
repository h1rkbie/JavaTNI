public class Book {
    private String title;
    private int totalBook;
    private int availableBook;

    Book(String title, int totalBook) {
        this.availableBook = totalBook;
        this.totalBook = totalBook;
        this.title = title;
    }
    Book (String title) {
        this.title = title;
    }
    Book() {
//        this.title = "";
//        this.totalBook = 0;
        this("", 0);

    }
    String getTitle() {
        return this.title;
    }
    int getTotalBook() {
        return this.totalBook;
    }
    int getAvailableBook () {
        return this.availableBook;
    }
    void borrowBook() {
        if (availableBook > 0) {
            this.availableBook--;
        }
    }
    void returnBook() {
        if (availableBook < totalBook) {
            this.availableBook++;
        }
    }




}
