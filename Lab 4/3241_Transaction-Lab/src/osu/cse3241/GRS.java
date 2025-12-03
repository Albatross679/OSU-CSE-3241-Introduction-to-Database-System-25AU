package osu.cse3241;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import osu.cse3241.options.ExecuteSale;
import osu.cse3241.options.NewAddressEmployee;
import osu.cse3241.utilities.Utilities;

public class GRS {

    private static String DATABASE = "transaction_database_binary.db";

    public static Connection conn = null;

    /**
     * Connects to the database if it exists, creates it if it does not, and
     * returns the connection object.
     *
     * @param databaseFileName
     *            the database file name
     * @return a connection object to the designated database
     */
    public static Connection initializeDB(String databaseFileName) {
        /**
         * The "Connection String" or "Connection URL".
         *
         * "jdbc:sqlite:" is the "subprotocol". (If this were a SQL Server
         * database it would be "jdbc:sqlserver:".)
         */
        String url = "jdbc:sqlite:" + databaseFileName;
        Connection conn = null; // If you create this variable inside the Try block it will be out of scope
        try {
            conn = DriverManager.getConnection(url);
            if (conn != null) {
                // Provides some positive assurance the connection and/or creation was successful.
                DatabaseMetaData meta = conn.getMetaData();
                System.out
                        .println("The driver name is " + meta.getDriverName());
                System.out.println(
                        "The connection to the database was successful.");
            } else {
                // Provides some feedback in case the connection failed but did not throw an exception.
                System.out.println("Null Connection");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out
                    .println("There was a problem connecting to the database.");
        }
        return conn;
    }

    /**
     * Displays two main options available, updating employee's address and
     * executing a sale.
     *
     * @param cin
     *            the scanner which accepts user input
     * @param conn
     *            the object which is connected with the database
     */
    private static void mainMenu(Scanner cin, Connection conn) {
        String input;
        do {
            System.out.print("~GROOVE RECORD STORE~\n"
                    + "1. Update Employee's Address\n" + "2. Execute a sale\n"
                    + "Input numerical selection (or 'x' to quit): ");
            input = cin.nextLine();
            while ((input.charAt(0) != 'x') && (input.charAt(0) != '1')
                    && (input.charAt(0) != '2')) {
                System.out.print("Incorrect option specified! Try again: ");
                input = cin.nextLine();
            }
            switch (input.charAt(0)) {
                case '1':
                    NewAddressEmployee.menu(cin, conn);
                    break;
                case '2':
                    ExecuteSale.menu(cin, conn);
                    break;
                default:
                    break;
            }
            Utilities.printDivider();
        } while (input.charAt(0) != 'x');
    }

    public static void main(String[] args) {
        conn = initializeDB(DATABASE);

        Scanner cin = new Scanner(System.in);
        mainMenu(cin, conn);

        cin.close();
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("Bye");
    }

}
