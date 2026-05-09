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
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }

    public boolean addUser(String username, String password) {
        String sql = "INSERT INTO credential (username, password) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to add user: " + e.getMessage());
            return false;
        }
    }

    public boolean authenticateUser(String username, String password) {
        String sql = "SELECT username, password FROM credential WHERE username = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
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