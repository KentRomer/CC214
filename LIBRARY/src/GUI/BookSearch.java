package GUI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BookSearch {

    // Available books
    private static ArrayList<String> books;

    // Borrowed books
    private static ArrayList<String> borrowedBooks = new ArrayList<>();

    // Borrow requests queue
    private static Queue<String> borrowQueue = new LinkedList<>();

    static {
        books = new ArrayList<>();
        books.add("Verity, Colleen hoover, ISBN123456");
        books.add("Song of Achilles, Wayneky Aresma, ISBN789012");
        books.add("It Starts With Us, Kentromer Madera, ISBN345678");
        books.add("Stuck With Mr. Billionaire, ISBN456789");
        books.add("CEO's Girl, ISBN5678910");
        books.add("The Personal Assistant, ISBN789101");
        books.add("Mr. Rude & Me, ISBN910112");
        books.add("Something Inside, ISBN456789");
    }

    public static List<String> searchBooks(String searchQuery) {
        ArrayList<String> results = new ArrayList<>();
        for (String book : books) {
            if (book.toLowerCase().contains(searchQuery.toLowerCase())) {
                results.add(book);
            }
        }
        return results;
    }

    public static boolean requestBorrow(String book) {
        if (books.contains(book) && !borrowQueue.contains(book)) {
            borrowQueue.add(book);
            return true;
        }
        return false;
    }

    public static boolean approveRequest(String book) {
        if (borrowQueue.contains(book)) {
            borrowQueue.remove(book);
            books.remove(book);
            borrowedBooks.add(book);
            return true;
        }
        return false;
    }

    public static boolean rejectRequest(String book) {
        return borrowQueue.remove(book);
    }

    public static List<String> getBorrowQueue() {
        return new ArrayList<>(borrowQueue);
    }

    public static List<String> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public static boolean returnBook(String book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            books.add(book);
            return true;
        }
        return false;
    }

    public static List<String> getBooks() {
        return new ArrayList<>(books);
    }
}
