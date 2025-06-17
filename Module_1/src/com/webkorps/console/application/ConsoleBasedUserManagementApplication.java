package com.webkorps.console.application;

import java.util.Scanner;

import com.webkorps.console.DAOImplementations.UserDAOImpl;
import com.webkorps.console.customexceptions.UserNotFoundException;

public class ConsoleBasedUserManagementApplication {
	
	static final UserDAOImpl operations = new UserDAOImpl(); 
    static final Scanner scanner = new Scanner(System.in);
    
	public static void main(String[] args) {
		while(true) {
            System.out.println("\n--- User Management Console ---");
            System.out.println("1. Create User");
            System.out.println("2. Delete User");
            System.out.println("3. Fetch User by Email");
            System.out.println("4. Fetch All Users");
            System.out.println("5. Update User");
            System.out.println("6. Exit");
            System.out.print("Choose an option (or 0 in any input to return to main menu): ");
            
            String input = scanner.next().trim();
            if(input.equals("0")) {
                continue;
            }
            
            try {
                int choice = Integer.parseInt(input);
                switch (choice) {
                    case 1 -> operations.createUser(scanner);
                    case 2 -> operations.deleteUser(scanner);
                    case 3 -> operations.fetchUser(scanner);
                    case 4 -> operations.fetchAllUsers();
                    case 5 -> operations.updateUser(scanner);
                    case 6 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.err.println("Invalid option!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Please enter a valid number!");
            } catch (UserNotFoundException e) {
                System.out.println(e.getMessage());
            }
		}
	}

}