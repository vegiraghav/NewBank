package newbank.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.List;

public class NewBankServer extends Thread {

    private ServerSocket server;

    public NewBankServer(int port) throws IOException {
        server = new ServerSocket(port);
    }

    public void run() {
        try {
            while (true) {
                Socket socket = server.accept();
                NewBankClientHandler clientHandler = new NewBankClientHandler(socket);
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // Starts a new NewBankServer thread on a specified port number
        NewBankServer bankServer = new NewBankServer(14002);
        bankServer.start();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display the menu
			System.out.println("------------------------------------------------------------------------------------------------------------------------");
            System.out.println("1. Create an account");
            System.out.println("2. Login to your account");
            System.out.println("3. List all users");
            System.out.println("Enter your choice: ");

            int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 1) {
                // Create an account
                System.out.println("Enter the user ID: ");
                String userId = scanner.nextLine();

                System.out.println("Enter the password: ");
                String password = scanner.nextLine();

                // Store the credentials
                UserCredentials.storeCredentials(userId, password);
            } else if (choice == 2) {
                // Login to an account
                System.out.println("Enter the user ID: ");
                String userId = scanner.nextLine();

                System.out.println("Enter the password: ");
                String password = scanner.nextLine();

                // Validate the credentials
                UserCredentials.validateCredentials(userId, password);
			
            } else if (choice == 3) {
                // List all users
                List<String> users = UserCredentials.getAllUsers();
                System.out.println("List of all users:");
                for (String user : users) {
                    System.out.println(user);
                }
            }
            
            else {
                System.out.println("Invalid choice.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid choice.");
            scanner.nextLine(); // Consume invalid input
        }

			System.out.println("Press Enter key to continue...");
			try {
				System.in.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    }
}
