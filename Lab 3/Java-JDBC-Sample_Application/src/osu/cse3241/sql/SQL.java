package osu.cse3241.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import osu.cse3241.GRS;

/**
 * 
 * All database connectivity should be handled from within here.
 *
 */
public class SQL {
	
	private static PreparedStatement ps;
	
    /**
     * Queries the database and prints the results.
     * 
     * @param conn a connection object
     * @param sql a SQL statement that returns rows:
     * 		this query is written with the Statement class, typically 
     * 		used for static SQL SELECT statements.
     */
    public static void sqlQuery(Connection conn, String sql){
        try {
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery(sql);
        	ResultSetMetaData rsmd = rs.getMetaData();
        	int columnCount = rsmd.getColumnCount();
        	for (int i = 1; i <= columnCount; i++) {
        		String value = rsmd.getColumnName(i);
        		System.out.print(value);
        		if (i < columnCount) System.out.print(",  ");
        	}
			System.out.print("\n");
        	while (rs.next()) {
        		for (int i = 1; i <= columnCount; i++) {
        			String columnValue = rs.getString(i);
            		System.out.print(columnValue);
            		if (i < columnCount) System.out.print(",  ");
        		}
    			System.out.print("\n");
        	}
        	rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    /**
     * Queries the database and prints the results.
     * 
     * @param conn a connection object
     * @param sql a SQL statement that returns rows:
     * 		this query is written with the PrepareStatement class, typically 
     * 		used for dynamic SQL SELECT statements.
     */
    public static void sqlQuery(Connection conn, PreparedStatement sql){
        try {
        	ResultSet rs = sql.executeQuery();
        	ResultSetMetaData rsmd = rs.getMetaData();
        	int columnCount = rsmd.getColumnCount();
        	for (int i = 1; i <= columnCount; i++) {
        		String value = rsmd.getColumnName(i);
        		System.out.print(value);
        		if (i < columnCount) System.out.print(",  ");
        	}
			System.out.print("\n");
        	while (rs.next()) {
        		for (int i = 1; i <= columnCount; i++) {
        			String columnValue = rs.getString(i);
            		System.out.print(columnValue);
            		if (i < columnCount) System.out.print(",  ");
        		}
    			System.out.print("\n");
        	}
        	rs.close();
        	ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    /**
     * (PART 5) Create PreparedStatement to search a track by track name.
     * 
     * @param sql query for prepared statement
     * 
     * @param track_name track name to search by 
     */
    public static void ps_SearchTracks(String sql, String track_name){
    	try {
    		ps = GRS.conn.prepareStatement(sql);
    		ps.setString(1, track_name);
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	
    	sqlQuery(GRS.conn, ps);
    }
    
    /**
     * (PART 5) Create PreparedStatement to search an artist by artist name.
     * 
     * @param sql query for prepared statement
     * 
     * @param track_name track name to search by 
     */
    public static void ps_SearchArtists(String sql, String artist_name){
    	try {
    		ps = GRS.conn.prepareStatement(sql);
    		ps.setString(1, artist_name);
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	
    	sqlQuery(GRS.conn, ps);
    }
    
    /**
     * (PART 6) Create PreparedStatement to search number of records in stock according to album name.
     * 
     * @param sql query for prepared statement
     * 
     * @param album_name album name to search by
     */
    public static void ps_SearchRecordStockCount(String sql, String album_name) {
    	try {
    		ps = GRS.conn.prepareStatement(sql);
    		ps.setString(1, album_name);
    	} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    	
    	sqlQuery(GRS.conn, ps);
    }
    
    /**
     * (PART 7) Insert a new artist into the ARTIST table using PreparedStatement.
     * 
     * @param sql INSERT query for prepared statement
     * @param artistId the artist ID
     * @param artistName the artist name (required)
     * @param dob the date of birth (can be null)
     */
    public static void insertArtist(String sql, int artistId, String artistName, String dob) {
    	try {
    		// Ensure auto-commit is enabled for immediate persistence
    		if (!GRS.conn.getAutoCommit()) {
    			GRS.conn.setAutoCommit(true);
    		}
    		
    		ps = GRS.conn.prepareStatement(sql);
    		ps.setInt(1, artistId);
    		ps.setString(2, artistName);
    		if (dob == null || dob.isEmpty()) {
    			ps.setNull(3, java.sql.Types.DATE);
    		} else {
    			ps.setString(3, dob);
    		}
    		
    		int rowsAffected = ps.executeUpdate();
    		if (rowsAffected > 0) {
    			System.out.println("Artist successfully added to the database!");
    			System.out.println("Artist ID: " + artistId + ", Name: " + artistName + ", DOB: " + (dob == null ? "NULL" : dob));
    		} else {
    			System.out.println("Failed to add artist. No rows were affected.");
    		}
    		ps.close();
    	} catch (SQLException e) {
    		System.out.println("Error inserting artist: " + e.getMessage());
    	}
    }
	
}
