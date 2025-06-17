package com.webkorps.console.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validations {
	static final Scanner scanner = new Scanner(System.in);	
    
    public String getValidEmail() {
        while (true) {
            System.out.print("Enter email (or 0 to cancel): ");
            String email = scanner.nextLine().trim();
            if(email.equals("0")) return null;
            if (isValidEmail(email)) 
                return email;
            System.err.println("Invalid email format. Try again.");
        }
    }
    
    public List<String> getValidMobileNumbers() {
        List<String> numbers = new ArrayList<>();
        System.out.print("Enter number of mobile numbers (or 0 to cancel): ");
        String input = scanner.nextLine().trim();
        if(input.equals("0")) return null;
        
        int count;
        try {
            count = Integer.parseInt(input);
            if(count <= 0) {
                System.err.println("Please enter a positive number!");
                return getValidMobileNumbers();
            }
        } catch (NumberFormatException e) {
            System.err.println("Please enter a valid number!");
            return getValidMobileNumbers();
        }
        
        for (int i = 1; i <= count; i++) {
            while (true) {
                System.out.print("Enter mobile number " + i + " (or 0 to cancel): "); 
                String number = scanner.nextLine().trim();
                if(number.equals("0")) return null;
                if (isValidMobileNumber(number)) {
                    numbers.add(number);
                    break;
                } else {
                    System.err.println("Invalid mobile number. Must be 10 digits.");
                }
            }
        }
        return numbers;
    }
    
    public boolean isValidEmail(String email) {
    	String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    	return Pattern.matches(emailRegex, email);
    }
    
    public boolean isValidMobileNumber(String number) {
    	return number.matches("\\d{10}");
    }
    
}