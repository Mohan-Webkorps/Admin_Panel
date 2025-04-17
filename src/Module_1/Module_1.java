package Module_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

class User {
    private String name;
    private String email;
    private List<String> mobileNumbers;
    
    public User() {
    	
    }

    public User(String name, String email, List<String> mobileNumbers) {
        this.name = name;
        this.email = email;
        this.mobileNumbers = new ArrayList<>(mobileNumbers);
    }

    public String getName() {     	
    	return name;     	
    }
    
    public void setEmail(String email) {    	
		this.email = email;		
	}

	public String getEmail() {     	
    	return email;     	
    }
    
    public List<String> getMobileNumbers() {     	
    	return mobileNumbers;     	
    }

    public void setName(String name) {
    	this.name = name;     	
    }
    
    public void setMobileNumbers(List<String> mobileNumbers) {
        this.mobileNumbers = new ArrayList<>(mobileNumbers);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Mobile Numbers: " + mobileNumbers;
    }
}

class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}


public class Module_1 {
	
	static Map<String, User> users = new HashMap<>();
	static Scanner scanner = new Scanner(System.in);
	 
	public static void main(String[] args) {
	
		 while(true) {
	            System.out.println("\n--- User Management Console ---");
	            System.out.println("1. Create User");
	            System.out.println("2. Delete User");
	            System.out.println("3. Fetch User by Email");
	            System.out.println("4. Fetch All Users");
	            System.out.println("5. Update User");
	            System.out.println("6. Exit");
	            System.out.print("Choose an option: ");
	            int choice = Integer.parseInt(scanner.nextLine());

	            try {
	                switch (choice) {
	                    case 1 -> createUser();
	                    case 2 -> deleteUser();
	                    case 3 -> fetchUser();
	                    case 4 -> fetchAllUsers();
	                    case 5 -> updateUser();
	                    case 6 -> {
	                        System.out.println("Exiting...");
	                        return;
	                    }
	                    default -> System.out.println("Invalid option!");
	                }
	            } catch (UserNotFoundException e) {
	                System.out.println(e.getMessage());
	            }
	        }
		 
	}
	
	private static void createUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        String email = getValidEmail();
        if (users.containsKey(email)) {
            System.out.println("User already exists with this email.");
            return;
        }

        List<String> mobileNumbers = getValidMobileNumbers();

        User user = new User(name, email, mobileNumbers);
        users.put(email, user);
        System.out.println("User created successfully.");
    }

    private static void deleteUser() throws UserNotFoundException {
        String email = getValidEmail();
        if (!users.containsKey(email)) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        users.remove(email);
        System.out.println("User deleted successfully.");
    }

    private static void fetchUser() throws UserNotFoundException {
        String email = getValidEmail();
        if (!users.containsKey(email)) {
            throw new UserNotFoundException("User not found with email: " + email);
        }
        System.out.println(users.get(email));
    }

    private static void fetchAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            users.values().forEach(System.out::println);
        }
    }

    private static void updateUser() throws UserNotFoundException {
        String email = getValidEmail();
        if (!users.containsKey(email)) {
            throw new UserNotFoundException("User not found with email: " + email);
        }

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        List<String> mobileNumbers = getValidMobileNumbers();

        User user = users.get(email);
        user.setName(name);
        user.setMobileNumbers(mobileNumbers);

        System.out.println("User updated successfully.");
    }

    private static String getValidEmail() {
        while (true) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            if (isValidEmail(email)) 
            	return email;
            System.out.println("Invalid email format. Try again.");
        }
    }

    private static List<String> getValidMobileNumbers() {
        List<String> numbers = new ArrayList<>();
        System.out.print("Enter number of mobile numbers: ");
        int count = scanner.nextInt();
        for (int i = 1; i <= count; i++) {
            while (true) {
                System.out.print("Enter mobile number " + i + ": "); 
                scanner.nextLine();
                String number = scanner.nextLine();
                if (isValidMobileNumber(number)) {
                    numbers.add(number);
                    break;
                } else {
                    System.out.println("Invalid mobile number. Must be 10 digits.");
                }
            }
        }
        return numbers;
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.\\w{2,}$";
        return Pattern.matches(emailRegex, email);
    }

    private static boolean isValidMobileNumber(String number) {
        return number.matches("\\d{10}");
    }
	
}
