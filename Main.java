import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Initialization of  DatabaseManager to establish the database connection and scanner 
        DatabaseManager dbManager = new DatabaseManager();
        LoginManager loginManager = new LoginManager(dbManager);

        Scanner input = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add User Credential");
            System.out.println("2. Test Login");
            System.out.println("3. Exit");
            System.out.print("Select an option (1-3): ");

            try {
                choice = Integer.parseInt(input.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = 0;
            }

            // switch case user's choice
            switch (choice) {
                case 1:
                    loginManager.addUserCredential(input);
                    break;
                case 2:
                    loginManager.startLoginProcess(input);
                    break;
                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-3.");
            }

        } while (choice != 3);

        dbManager.close();
        input.close();
    }
}