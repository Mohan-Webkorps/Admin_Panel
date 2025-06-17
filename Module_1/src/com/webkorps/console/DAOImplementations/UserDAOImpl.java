package com.webkorps.console.DAOImplementations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.webkorps.console.DAO.UserDAO;
import com.webkorps.console.customexceptions.UserNotFoundException;
import com.webkorps.console.model.User;
import com.webkorps.console.validations.Validations;

public class UserDAOImpl implements UserDAO{

	static Map<String, User> users = new HashMap<>();
	Validations validations = new Validations();

	@Override
	public void createUser(Scanner scanner) {
		scanner.nextLine();
		System.out.print("Enter name (or 0 to cancel): ");
        String name = scanner.nextLine().trim();
        if(name.equals("0")) return;
        while(name.isEmpty()) {
            System.err.println("Name cannot be blank!");
            System.out.print("Enter name (or 0 to cancel): ");
            name = scanner.nextLine().trim();
            if(name.equals("0")) return;
        }

        String email = validations.getValidEmail();
        if(email == null) return;
		if (users.containsKey(email)) {
            System.err.println("User already exists with this email.");
            return;
        }

        List<String> mobileNumbers = validations.getValidMobileNumbers();
        if(mobileNumbers == null) return;

        User user = new User(name, email, mobileNumbers);
        users.put(email, user);
        System.out.println("User created successfully.");
	}

	@Override
	public void deleteUser(Scanner scanner) throws UserNotFoundException {
		String email = validations.getValidEmail();
        if(email == null) return;
        if (!users.containsKey(email)) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        users.remove(email);
        System.out.println("User deleted successfully.");
	}

	@Override
	public void fetchUser(Scanner scanner) throws UserNotFoundException{
		String email = validations.getValidEmail();
        if(email == null) return;
        if (!users.containsKey(email)) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        System.out.println(users.get(email));
	}

	@Override
	public void fetchAllUsers() {
		if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
        	for (User user : users.values()) {
        	    System.out.println(user);
        	}
        }
	}

	@Override
	public void updateUser(Scanner scanner) throws UserNotFoundException{
		String email = validations.getValidEmail();
        if(email == null) return;
        if (!users.containsKey(email)) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        User user = users.get(email);
        
        System.out.print("Update name? (current: " + user.getName() + ") [y/n] (or 0 to cancel): ");
        String choice = scanner.nextLine().trim().toLowerCase();
        if(choice.equals("0")) return;
        if(choice.equals("y")) {
            System.out.print("Enter new name (or 0 to cancel): ");
            String name = scanner.nextLine().trim();
            if(name.equals("0")) return;
            while(name.isEmpty()) {
                System.err.println("Name cannot be blank!");
                System.out.print("Enter new name (or 0 to cancel): ");
                name = scanner.nextLine().trim();
                if(name.equals("0")) return;
            }
            user.setName(name);
        }

        System.out.print("Update mobile numbers? (current: " + user.getMobileNumbers() + ") [y/n] (or 0 to cancel): ");
        choice = scanner.nextLine().trim().toLowerCase();
        if(choice.equals("0")) return;
        if(choice.equals("y")) {
            List<String> mobileNumbers = validations.getValidMobileNumbers();
            if(mobileNumbers != null) {
                user.setMobileNumbers(mobileNumbers);
            }
        }

        System.out.println("User updated successfully.");
	}

}
