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

    // Handles the login flow with a limited number of attempts  
    // and  Loop until max attempts are reached
    public void startLoginProcess(Scanner input){
        int attemptCount = 0;
        while (attemptCount < MAX_ATTEMPTS){
            System.out.print("Enter username: ");
            String inputUsername = input.nextLine();

            System.out.print("Enter password: ");
            String inputPassword = input.nextLine();

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
    
    //This block code input for new credentials and saves them to the database

    public void addUserCredential(Scanner input){
        System.out.print("Enter new username: ");
        String newUsername = input.nextLine();

        System.out.print("Enter new password: ");
        String newPassword = input.nextLine();

        if (dbManager.addUser(newUsername, newPassword)){
            System.out.println("User successfully added.");
        }
    }
}