package osu.cse3241.options;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import osu.cse3241.utilities.Utilities;

public class NewAddressEmployee {

    public static boolean isValidInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Asks the user for the ID of the employee who's address to update and asks
     * for the new address
     *
     * With this information, calls the method with the transaction to update
     * the employee's address
     *
     * @param cin
     *            the scanner which accepts user input
     * @param conn
     *            the object which is connected with the database
     */
    public static void menu(Scanner cin, Connection conn) {
        Utilities.printDivider();
        System.out.print(
                "Enter the ID of employee who's address needs to be updated, or "
                        + "'x' to quit: ");
        String employeeID = cin.nextLine();
        while (!isValidInteger(employeeID) || !(employeeID.charAt(0) != 'x')) {
            System.out.print("Incorrect option specified! Try again: ");
            employeeID = cin.nextLine();
        }
        System.out.print("Enter the new address for the employee: ");
        String employeeNewAddress = cin.nextLine();
        switch (employeeID) {
            case ("x"):
                break;
            default:
                int employeeIDInt = Integer.parseInt(employeeID);
                transactionUpdateEmployeeAddress(conn, cin,
                        osu.cse3241.statements.NewAddressEmployeeStatements.updateEmployeeAddress,
                        employeeIDInt, employeeNewAddress);

                break;
        }
    }

    /**
     * This transaction will update an employee's address. The user can choose
     * the employee who they wish to update.
     *
     * The Address column in the employee table will be updated.
     *
     * @param conn
     *            a connection object
     *
     * @param cin
     *            a scanner object
     *
     * @param transactionStatement1
     *            an String object with the transaction statement
     *
     */
    private static void transactionUpdateEmployeeAddress(Connection conn,
            Scanner cin, String updateEmployeeAddress, int employeeID,
            String employeeAddress) {

        try {
            /*
             * PART 5
             *
             * Disable the auto commit on the connection. This ensures data
             * integrity when performing multiple database operations in a
             * single unit. It allows us to roll-back if there is an error.
             */

            /*
             * TODO (Complete the code to disable auto commit)
             */

            /*
             * prepares the statement to update the employee's address by
             * setting the address and the ID
             */

            PreparedStatement statement1 = conn
                    .prepareStatement(updateEmployeeAddress);
            statement1.setString(1, employeeAddress);
            statement1.setInt(2, employeeID);

            /*
             * PART 6
             *
             * execute the update (done for you), and implement an if-else
             * conditional to check if the transaction completed successfully.
             * Use rowsUpdated as a way to observe if the transaction was
             * completed successfully.
             */

            int rowsUpdated = statement1.executeUpdate();

            /*
             * TODO (Complete the code to commit the transaction if rows are
             * updated. If rows are updated, print out
             * "Transaction complete. Address updated." Else, roll-back the
             * transaction.)
             */

            //re-enables auto commit on the connection

            conn.setAutoCommit(true);

            //catches a SQL error and rolls back transaction
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.print("Transaction rolled back. Error occured.");

        }

    }
}
