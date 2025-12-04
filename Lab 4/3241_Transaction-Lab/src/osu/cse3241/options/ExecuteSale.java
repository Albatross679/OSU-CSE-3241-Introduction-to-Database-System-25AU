package osu.cse3241.options;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import osu.cse3241.statements.SaleStatements;
import osu.cse3241.utilities.Utilities;

public class ExecuteSale {
    /**
     * Asks the user if they wish to begin the execution of a sale and calls all
     * the functions that execute the critical transactions
     *
     * @param cin
     *            the scanner which accepts user input
     * @param conn
     *            the object which is connected with the database
     */
    public static void menu(Scanner cin, Connection conn) {
        Utilities.printDivider();
        System.out.print(
                "Enter 'y' to begin execution a sale, or " + "'x' to quit: ");
        String input = cin.nextLine();
        char selection = !input.isEmpty() ? input.charAt(0) : ' ';

        while (!Character.isLetter('y') && !Character.isLetter('x')) {
            System.out.print("Incorrect option specified! Try again: ");
            input = cin.nextLine();
            selection = !input.isEmpty() ? input.charAt(0) : ' ';
        }

        switch (selection) {
            case 'y':
                java.lang.Object[] saleInformation = transactionObtainInformationForSale(
                        conn, cin,
                        osu.cse3241.statements.SaleStatements.obtainCostQuery,
                        osu.cse3241.statements.SaleStatements.obtainNewSaleID);
                transactionExecuteSale(conn, cin,
                        SaleStatements.obtainCostQuery,
                        SaleStatements.insertSale,
                        SaleStatements.insertSaleDetail,
                        SaleStatements.updateRecordCount, saleInformation);
            case 'x':
                break;
            default:
                break;
        }
    }

    /**
     * This method lets the user input values so it can gather up information
     * about the sale.
     *
     * This method also contains a transaction that queries valuable information
     * from the database
     *
     * @param conn
     *            the object which is connected with the database
     * @param cin
     *            the scanner which accepts user input
     * @param obtainCostQuery
     *            the SQL query that will obtain the cost of each record in the
     *            sale
     * @param obtainNewSaleID
     *            the SQL query that obtains the new sale ID
     */
    public static java.lang.Object[] transactionObtainInformationForSale(
            Connection conn, Scanner cin, String obtainCostQuery,
            String obtainNewSaleID) {

        //Array with information about the sale to return back
        Object[] saleInformation = new Object[6];

        try {

            /*
             * Asks user to enter ID of employee executing transaction, the
             * number of unique records purchased, and quantity of each unique
             * record sold
             */

            //disable auto commit on the connection and prepare the statement
            conn.setAutoCommit(false);

            System.out
                    .print("Enter the ID of employee executing transaction: ");
            Integer employeeID = cin.nextInt();
            saleInformation[0] = employeeID;

            System.out.print(
                    "Enter number of unique items that are being purchased: ");
            Integer numUniqueItems = cin.nextInt();
            saleInformation[1] = numUniqueItems;

            Map<Integer, Integer> RecordNums = new HashMap<>();
            Double totalCost = 0.00;
            for (int i = 1; i <= numUniqueItems; i++) {
                System.out.print("Item " + i + " -"
                        + " enter the Record_ID of this item:  ");
                Integer recordID = cin.nextInt();
                System.out.print(
                        "Enter the quantity of this product that is purchased: ");
                Integer quantity = cin.nextInt();
                RecordNums.put(recordID, quantity);
            }
            saleInformation[2] = RecordNums;
            Set<Integer> Records = RecordNums.keySet();
            saleInformation[3] = Records;

            /*
             * Calculates total cost for entire sale. Iterates through each
             * record to get its cost and adds to total
             */
            for (Integer UniqueRecord : Records) {

                /*
                 * PART 8
                 *
                 * Create a resultSet, prepare the totalCost Statement, set the
                 * unknown integer in the statement to UniqueRecord.
                 *
                 */
                ResultSet resultSet = null;
                PreparedStatement totalCostStatement = conn.prepareStatement(obtainCostQuery);
                totalCostStatement.setInt(1, UniqueRecord);

                resultSet = totalCostStatement.executeQuery();
                if (resultSet.next()) {
                    totalCost += RecordNums.get(UniqueRecord) * (resultSet.getDouble(1));
                }

            }

            saleInformation[4] = totalCost;

            //SQL query to obtain saleID of this sale
            int saleID = 0;
            Statement saleIDStatement = conn.createStatement();
            ResultSet resultSet = saleIDStatement.executeQuery(obtainNewSaleID);
            if (resultSet.next()) {
                saleID = resultSet.getInt(1);
            }
            saleInformation[5] = saleID;

            cin.nextLine();

            //re-enable auto commit on the connection
            conn.setAutoCommit(true);

        } catch (SQLException e) {

            /*
             * PART 9
             *
             * Complete the code to roll-back the transaction if error occurs.
             *
             */
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.print("Transaction rolled back. Error occurred.");

        }

        System.out.println("Obtaining Sale Information. Transaction complete");

        return saleInformation;
    }

    /**
     * This method lets the user input values so it can gather up information
     * about the sale.
     *
     * This method also contains a transaction that queries valuable information
     * from the database
     *
     * @param conn
     *            the object which is connected with the database
     * @param cin
     *            the scanner which accepts user input
     * @param obtainCostQuery
     *            the SQL query that will obtain the cost of each record in the
     *            sale
     * @param obtainNewSaleID
     *            the SQL query that obtains the new sale ID
     */
    public static void transactionExecuteSale(Connection conn, Scanner cin,
            String obtainCostQuery, String insertSale, String insertSaleDetail,
            String updateRecordCount, java.lang.Object[] saleInformation) {
        //Update tables with information regarding sale
        try {

            //disable auto commit on the connection and prepare the statement
            conn.setAutoCommit(false);

            /*
             * PART 10
             *
             * Complete the code to add a sale to the Sales table. First, use
             * conn.prepareStatement(insertSale) to create the Prepared
             * Statement Next, set the two user inputed values in the insertSale
             * statement. You will use setInt(1, (int) saleInformation[5]), setInt(2, (int)saleInformation[0]), and
             * setDouble(2, (double) saleInformation[4]) Finally, execute the
             * update.
             *
             */
            PreparedStatement insertSaleStatement = conn.prepareStatement(insertSale);
            insertSaleStatement.setInt(1, (int) saleInformation[5]);
            insertSaleStatement.setInt(2, (int) saleInformation[0]);
            insertSaleStatement.setDouble(3, (double) saleInformation[4]);
            insertSaleStatement.executeUpdate();

            //Add Individual Unique Records to SalesDetails Table

            @SuppressWarnings("unchecked")
            Set<Integer> records = (Set<Integer>) saleInformation[3];
            @SuppressWarnings("unchecked")
            Map<Integer, Integer> RecordNums = (Map<Integer, Integer>) saleInformation[2];
            for (Integer UniqueRecord : records) {

                //SQL query to obtain cost of unique record in the sale
                ResultSet resultSet = null;
                double recordCost = 0.0;

                PreparedStatement recordCostStatement = conn
                        .prepareStatement(obtainCostQuery);
                recordCostStatement.setInt(1, UniqueRecord);

                resultSet = recordCostStatement.executeQuery();
                if (resultSet.next()) {
                    recordCost = RecordNums.get(UniqueRecord)
                            * (resultSet.getDouble(1));
                }

                //SQL statement to update SalesDetails table
                PreparedStatement saleDetailStatement = conn
                        .prepareStatement(insertSaleDetail);
                saleDetailStatement.setInt(1, (int) saleInformation[5]);
                saleDetailStatement.setInt(2, UniqueRecord);
                saleDetailStatement.setInt(3, RecordNums.get(UniqueRecord));
                saleDetailStatement.setDouble(4, recordCost);
                /*
                 * execute the statement, and implement an if-else conditional
                 * to check if the transaction is rolled back.
                 */
                int rowsUpdated = saleDetailStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    conn.commit();
                } else {
                    conn.rollback();
                    System.out.println("Transaction rolled back.");
                }

            }

            //lower record_Amount values for corresponding records
            for (Integer UniqueRecord : records) {

                /*
                 * SQL statement to update the record_Amount column for each
                 * record in the sale
                 */
                PreparedStatement recordAmountStatement = conn
                        .prepareStatement(updateRecordCount);
                recordAmountStatement.setInt(1, RecordNums.get(UniqueRecord));
                recordAmountStatement.setInt(2, UniqueRecord);
                /*
                 * execute the statement, and implement an if-else conditional
                 * to check if the transaction is rolled back.
                 */
                int rowsUpdated = recordAmountStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    conn.commit();
                } else {
                    conn.rollback();
                    System.out.println("Transaction rolled back.");
                }

            }

            //re-enable auto commit on the connection
            conn.setAutoCommit(true);

            System.out.println("Sale has been Executed. Transaction complete.");

            //catch an exception if error occurs and roll back
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
