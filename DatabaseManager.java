import java.sql.*;

public class DatabaseManager {

    String url = "jdbc:mysql://localhost:3306/javadatabasepractice";
    String user = "root";
    String password = "";

    Connection connection;

    public DatabaseManager() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
    // This block of code Inserts a new user record into the credential table

    public boolean addUser(String username, String password) {
        String sql = "INSERT INTO credential (username, password) VALUES (?, ?)";
        try (PreparedStatement insert = connection.prepareStatement(sql)) {
            insert.setString(1, username);
            insert.setString(2, password);
            insert.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to add user: " + e.getMessage());
            return false;
        }
    }

     // This block of code Checks if the given username and password match a record in the credential table
    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT username, password FROM credential WHERE username = ? AND password = ?";
        try (PreparedStatement insert = connection.prepareStatement(sql)) {
            insert.setString(1, username);
            insert.setString(2, password);
            ResultSet rs = insert.executeQuery();
            return
            rs.next();
        } catch (SQLException e) {
            System.out.println("Login validation failed: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection: " + e.getMessage());
        }
    }
}