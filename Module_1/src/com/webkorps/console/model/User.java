package com.webkorps.console.model;

import java.util.ArrayList;
import java.util.List;

public class User {
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
