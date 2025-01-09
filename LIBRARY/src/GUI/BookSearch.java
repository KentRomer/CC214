package GUI;

import java.util.*;

public class BookSearch {
    // Available books
    private static ArrayList<String> books;

    // Map to track borrowed books per user: username -> list of books
    private static Map<String, ArrayList<String>> userBorrowedBooks = new HashMap<>();

    // Map to track borrow requests per user: username -> queue of requested books
    private static Map<String, Queue<String>> userBorrowQueue = new HashMap<>();

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

    public static boolean requestBorrow(String username, String book) {
        userBorrowQueue.putIfAbsent(username, new LinkedList<>());
        Queue<String> userQueue = userBorrowQueue.get(username);

        if (books.contains(book) && !userQueue.contains(book)) {
            userQueue.add(book);
            return true;
        }
        return false;
    }

    public static boolean approveRequest(String username, String book) {
        userBorrowQueue.putIfAbsent(username, new LinkedList<>());
        userBorrowedBooks.putIfAbsent(username, new ArrayList<>());

        Queue<String> userQueue = userBorrowQueue.get(username);
        ArrayList<String> userBooks = userBorrowedBooks.get(username);

        if (userQueue.contains(book)) {
            userQueue.remove(book);
            books.remove(book);
            userBooks.add(book);
            return true;
        }
        return false;
    }

    public static boolean rejectRequest(String username, String book) {
        Queue<String> userQueue = userBorrowQueue.get(username);
        return userQueue != null && userQueue.remove(book);
    }

    public static List<String> getBorrowQueue(String username) {
        Queue<String> userQueue = userBorrowQueue.getOrDefault(username, new LinkedList<>());
        return new ArrayList<>(userQueue);
    }

    public static List<String> getBorrowedBooks(String username) {
        return new ArrayList<>(userBorrowedBooks.getOrDefault(username, new ArrayList<>()));
    }

    public static boolean returnBook(String username, String book) {
        ArrayList<String> userBooks = userBorrowedBooks.get(username);
        if (userBooks != null && userBooks.contains(book)) {
            userBooks.remove(book);
            books.add(book);
            return true;
        }
        return false;
    }

    public static List<String> getBooks() {
        return new ArrayList<>(books);
    }
}