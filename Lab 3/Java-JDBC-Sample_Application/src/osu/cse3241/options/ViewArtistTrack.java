package osu.cse3241.options;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import osu.cse3241.utilities.Utilities;
import osu.cse3241.sql.SQL;
import osu.cse3241.GRS;

public class ViewArtistTrack {
	
	private static Set<Character> MENU_OPTIONS = new HashSet<>(Arrays.asList('1', '2', 'x'));
	
	public static void menu(Scanner cin) {
		Utilities.printDivider();
		System.out.print("VIEW ALL:\n"
				+ "1. Artists\n"
				+ "2. Tracks\n"
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
				viewArtists();
				break;
			case '2':
				viewTracks();
				break;
			default:
				break;
		}
	}
	
	/*
	 * PART 4:
	 * Remove the placeholder method and complete one of the following methods:
	 * 	
	 * 	viewTracks()
	 * 	viewArtists()
	 * 
	 */
	
	/**
	 * Query all tracks contained in database.
	 */
	private static void viewTracks() {
		String sql = "SELECT * FROM TRACK;";
		SQL.sqlQuery(GRS.conn, sql);
	}
	
	/**
	 * Query all artists contained in database.
	 */
	private static void viewArtists() {
		String sql = "SELECT * FROM ARTIST;";
		SQL.sqlQuery(GRS.conn, sql);
	}
	
}
