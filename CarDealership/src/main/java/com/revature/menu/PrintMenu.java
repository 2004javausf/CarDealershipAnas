package com.revature.menu;
import java.util.ArrayList;
import java.util.List;

public class PrintMenu {

	private String title;
	private List<String> menuItems = new ArrayList<String>();
	
	// No-argument constructor
	public PrintMenu() {
		title = "Menu";
	}
	
    // Constructor with arguments
	public PrintMenu(String title, String... menuItems) {
		this.title = title;
		
		// Iterates through menu items and add them to the menu
		for (String item : menuItems) {
			this.menuItems.add(item);
		}
	}
	
	// Method to add items to the menu list
	public void addMenuItem (String item) {
		menuItems.add(item);
	}
	
	// Displays the menu and respective options
	public void display() {
		System.out.println(title + ":" );
		
		int counter = 1;
		for (String item : menuItems) {
			System.out.println(counter++ + ") " + item);
		}
		System.out.println("Press the corresponding number from the options to proceed:");
		System.out.print("> ");
	}
	
	
	public void processOptions() {
		
	}
}
