import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        LoginManager loginManager = new LoginManager(dbManager);

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add User Credential");
            System.out.println("2. Test Login");
            System.out.println("3. Exit");
            System.out.print("Select an option (1-3): ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                choice = 0;
            }

            switch (choice) {
                case 1:
                    loginManager.addUserCredential(scanner);
                    break;
                case 2:
                    loginManager.startLoginProcess(scanner);
                    break;
                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please select 1-3.");
            }

        } while (choice != 3);

        dbManager.close();
        scanner.close();
    }
}