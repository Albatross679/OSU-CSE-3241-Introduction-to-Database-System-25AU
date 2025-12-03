package osu.cse3241.options;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import osu.cse3241.utilities.Utilities;
import osu.cse3241.sql.SQL;
import osu.cse3241.GRS;

public class EditMenu {
	
	private static final Set<Character> MENU_OPTIONS = new HashSet<>(Arrays.asList('1', '2', '3', 'x'));
	
	public static void menu(Scanner cin) {
		Utilities.printDivider();
		System.out.print("EDIT MENU:\n"
				+ "1. Add Artist\n"
				+ "2. Delete Artist\n"
				+ "3. Update Artist\n"
				+ "Input numerical selection (or 'x' to quit): ");
		String input = cin.nextLine();
		char selection = !input.isEmpty() ? input.charAt(0) : ' ';		
		
		while(!MENU_OPTIONS.contains(selection)) {
			System.out.print("Incorrect option specified! Try again: ");
			input = cin.nextLine();
			selection = !input.isEmpty() ? input.charAt(0) : ' ';
		}

		switch(selection) {
			case '1':
				addArtist(cin);
				break;
			case '2':
				deleteArtist();
				break;
			case '3':
				updateArtist();
			default:
				break;
		}
	}

	/*
	 * PART SEVEN:
	 * Complete the method addArtist() below and remove its placeholder. 
	 * 
	 * Your implementation will follow the general procedure:
	 * 	1) Prompt the user for values for each column in the ARTIST table.
	 * 	2) Call a method in SQL.java which will handle the insertion of data
	 * 		via PreparedStatement and will print out a confirmation message
	 * 		upon successful insertion. You will need to define this method in SQL.java!
	 * 
	 * Note: since this query is not returning anything (like a SELECT query), we need to use 
	 * a different method here (see JDBC_API slide deck): PreparedStatement.executeUpdate(). This also has the possibility to 
	 * throw a SQL Exception (like many other methods you have already used), so make sure it is 
	 * handled in much the same way as the other Statement/PreparedStatement methods are handled!
	 * 
	 * You are free to design the input prompt, and the method on SQL.java to insert the data, etc. 
	 */
	
	
	private static void addArtist(Scanner cin) {
		System.out.print("Enter Artist ID: ");
		String artistIdStr = cin.nextLine().trim();
		int artistId;
		try {
			artistId = Integer.parseInt(artistIdStr);
		} catch (NumberFormatException e) {
			System.out.println("Invalid Artist ID. Please enter a valid integer.");
			return;
		}
		
		System.out.print("Enter Artist Name (required, max 30 characters): ");
		String artistName = cin.nextLine().trim();
		if (artistName.isEmpty()) {
			System.out.println("Artist Name cannot be empty.");
			return;
		}
		if (artistName.length() > 30) {
			System.out.println("Artist Name exceeds 30 characters. Truncating to 30 characters.");
			artistName = artistName.substring(0, 30);
		}
		
		System.out.print("Enter Date of Birth (YYYY-MM-DD) or press Enter to leave blank: ");
		String dobStr = cin.nextLine().trim();
		String dob = dobStr.isEmpty() ? null : dobStr;
		
		String sql = "INSERT INTO ARTIST (Artist_ID, Artist_Name, DOB) VALUES (?, ?, ?);";
		SQL.insertArtist(sql, artistId, artistName, dob);
	}

	private static void deleteArtist() {
		Utilities.placeholder();
	}

	private static void updateArtist() {
		Utilities.placeholder();	
	}
	
}
