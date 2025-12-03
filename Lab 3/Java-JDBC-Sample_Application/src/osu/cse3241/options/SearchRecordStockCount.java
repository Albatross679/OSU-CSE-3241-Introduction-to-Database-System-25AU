package osu.cse3241.options;

import java.util.Scanner;

import osu.cse3241.utilities.Utilities;
import osu.cse3241.sql.SQL;

public class SearchRecordStockCount {
	
	public static void menu(Scanner cin) {
		Utilities.printDivider();
		System.out.print("SEARCH RECORD STOCK COUNT:\n"
				+ "Input album name (or 'x' to quit): ");
		String album_name = cin.nextLine();
		
		if (!"x".equals(album_name) && !album_name.trim().isEmpty()) {
		    searchRecordStockCount(album_name);
		}
	}

	/*
	 * PART SIX:
	 * Complete the following method and remove its placeholder.
	 */
	
	/**
	 * Search record stock count by album name.
	 * 
	 * @param album_name album name to search by
	 */
	private static void searchRecordStockCount(String album_name) {
		String sql = "SELECT ALBUM.Album_Name, RECORD.Record_Count FROM ALBUM JOIN RECORD ON ALBUM.Record_ID = RECORD.Record_ID WHERE ALBUM.Album_Name LIKE ?;";
		SQL.ps_SearchRecordStockCount(sql, "%" + album_name + "%");
	}
	
}
