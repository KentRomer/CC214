package GUI;

import java.util.HashMap;

public class UserManager {
    // Single storage for all user credentials
    private static final HashMap<String, UserData> userDatabase = new HashMap<>();
    
    // Admin credentials
    private static final String ADMIN_USERNAME = "manager001";
    private static final String ADMIN_PASSWORD = "bookeeper4ever";
    
    // User data structure
    public static class UserData {
        public String password;
        public String fullName;
        public String email;
        
        public UserData(String password, String fullName, String email) {
            this.password = password;
            this.fullName = fullName;
            this.email = email;
        }
    }
    
    // Register a new user
    public static boolean registerUser(String username, String password, String fullName, String email) {
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            return false;
        }
        
        // Check if user already exists
        if (userDatabase.containsKey(username)) {
            return false;
        }
        
        // Store new user
        userDatabase.put(username, new UserData(password, fullName, email));
        return true;
    }
    
    // Validate user login
    public static boolean isValidUser(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        
        UserData userData = userDatabase.get(username);
        return userData != null && userData.password.equals(password);
    }
    
    // Check admin credentials
    public static boolean isAdmin(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }
    
    // Get user data
    public static UserData getUserData(String username) {
        return userDatabase.get(username);
    }
}