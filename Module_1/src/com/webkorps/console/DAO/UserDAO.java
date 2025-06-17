package com.webkorps.console.DAO;

import java.util.Scanner;

import com.webkorps.console.customexceptions.UserNotFoundException;

public interface UserDAO {
	
	 void createUser(Scanner scanner);
	 
	 void deleteUser(Scanner scanner) throws UserNotFoundException;
	 
	 void fetchUser(Scanner scanner) throws UserNotFoundException;
	 
	 void fetchAllUsers();
	 
	 void updateUser(Scanner scanner) throws UserNotFoundException;
	 
}
