package GUI;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BorrowRecord {
    private final String bookTitle;
    private final String borrowerUsername;

    public BorrowRecord(String bookTitle, String borrowerUsername) {
        this.bookTitle = bookTitle;
        this.borrowerUsername = borrowerUsername != null ? borrowerUsername : "Unknown User";
    }

    public String getBookTitle() { return bookTitle; }
    public String getBorrowerUsername() { return borrowerUsername; }
}

public class BookSearch {
    private static ArrayList<String> books;
    private static ArrayList<BorrowRecord> borrowRecords = new ArrayList<>();
    private static Queue<PendingRequest> borrowQueue = new LinkedList<>();
    private static List<BookListListener> listeners = new ArrayList<>();

    static class PendingRequest {
        String bookTitle;
        String username;

        PendingRequest(String bookTitle, String username) {
            this.bookTitle = bookTitle;
            this.username = username;
        }
    }

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
    public interface BookListListener {
        void onBookListUpdated(List<String> updatedBooks);
    }

    public static void addBookListListener(BookListListener listener) {
        listeners.add(listener);
    }

    public static void removeBookListListener(BookListListener listener) {
        listeners.remove(listener);
    }

    private static void notifyListeners() {
        for (BookListListener listener : listeners) {
            listener.onBookListUpdated(new ArrayList<>(books));
        }
    }

    public static void addBook(String book) {
        books.add(book);
        notifyListeners();
    }

    public static void removeBook(String book) {
        books.remove(book);
        notifyListeners();
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

    public static boolean requestBorrow(String book, String username) {
        if (books.contains(book) && !isBookInQueue(book)) {
            borrowQueue.add(new PendingRequest(book, username));
            return true;
        }
        return false;
    }

    private static boolean isBookInQueue(String book) {
        for (PendingRequest request : borrowQueue) {
            if (request.bookTitle.equals(book)) {
                return true;
            }
        }
        return false;
    }

    public static boolean approveRequest(String bookInfo) {
        // Extract just the book title from the info string (removes the "Requested by: username" part)
        String[] parts = bookInfo.split(" \\(Requested by: ");
        String bookTitle = parts[0];
        String username = parts[1].substring(0, parts[1].length() - 1); // Remove the closing parenthesis

        PendingRequest requestToApprove = null;
        for (PendingRequest request : borrowQueue) {
            if (request.bookTitle.equals(bookTitle) && request.username.equals(username)) {
                requestToApprove = request;
                break;
            }
        }

        if (requestToApprove != null) {
            borrowQueue.remove(requestToApprove);
            books.remove(requestToApprove.bookTitle);
            borrowRecords.add(new BorrowRecord(requestToApprove.bookTitle, requestToApprove.username));
            return true;
        }
        return false;
    }

    public static boolean rejectRequest(String book) {
        return borrowQueue.removeIf(request -> request.bookTitle.equals(book));
    }

    public static List<String> getBorrowQueue() {
        List<String> queueBooks = new ArrayList<>();
        for (PendingRequest request : borrowQueue) {
            queueBooks.add(request.bookTitle + " (Requested by: " + request.username + ")");
        }
        return queueBooks;
    }

    public static List<BorrowRecord> getBorrowRecords() {
        return new ArrayList<>(borrowRecords);
    }

    public static boolean returnBook(String book) {
        for (BorrowRecord record : borrowRecords) {
            if (record.getBookTitle().equals(book)) {
                borrowRecords.remove(record);
                books.add(book);
                return true;
            }
        }
        return false;
    }

    public static List<String> getBooks() {
        return new ArrayList<>(books);
    }
}