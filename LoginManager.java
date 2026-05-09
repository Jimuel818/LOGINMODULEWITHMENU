import java.util.Scanner;

public class LoginManager {

        String url = "jdbc:mysql://localhost:3306/javadatabasepractice";
        String user = "root";
        String password = "";
        String query = "SELECT username, password FROM credential";

        public static final int MAX_ATTEMPTS = 3;

        private DatabaseManager dbManager;

        public LoginManager(DatabaseManager dbManager) {
            this.dbManager = dbManager;
        }

    public boolean login(String username, String password){
        return dbManager.authenticateUser(username, password);
    }

    public void startLoginProcess(Scanner scanner){
        int attemptCount = 0;
        while (attemptCount < MAX_ATTEMPTS){
            System.out.print("Enter username: ");
            String inputUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String inputPassword = scanner.nextLine();

            if (login(inputUsername, inputPassword)){
                System.out.println("Login successful!");
                System.out.print("");
                return;
            } else {
                attemptCount++;
                int remaining = MAX_ATTEMPTS - attemptCount;
                System.out.println("Invalid username or password. Attempts remaining: " + remaining + "\n");
                if (remaining == 0) {
                    System.out.println("Terminated. Too many failed attempts.");
                }
            }
        }
    }

    public void addUserCredential(Scanner scanner){
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();

        if (dbManager.addUser(newUsername, newPassword)){
            System.out.println("User successfully added.");
        }
    }
}