package osu.cse3241.statements;

/**
 * These statements will help add a new sale to the database system. They will
 * all be used to conduct the 2nd and 3rd transactions. The 1st transaction
 * gathers data regarding the sale, and the 2nd transaction updates tables. The
 * sales, sales_details, and record tables are updated.
 *
 *
 * Remember to include the semicolon at the end of the statement strings. (Not
 * all programming languages and/or packages require the semicolon (e.g.,
 * Python's SQLite3 library))
 */
public class SaleStatements {

    /*
     * PART 7: Complete the rest of the strings to build the statements that the
     * two transactions regarding sales will execute.
     */

    //obtains the cost of each unique record in the sale
    public static String obtainCostQuery = "SELECT Price" + " FROM RECORD"
            + " WHERE RECORD.Record_ID = ?";

    //obtains the new SaleID to add
    public static String obtainNewSaleID = "SELECT MAX(Sale_ID)+1\r\n"
            + "FROM SALES";

    /*
     * Insert into the Sales table the new sale that the employee is carrying
     * out with the customer. Remember to use '?' to indicate values that will
     * be inputed by the user.
     *
     * For the 'Date' value, use DATE('now').
     *
     */

    /*
     * TODO
     */
    public static String insertSale = "INSERT INTO SALES (Sale_ID, Employee, Date, Total_Value) "
            + "VALUES (?, ?, DATE('now'), ?);";

    /*
     * Insert into the Sales_Details table details regarding a unique record in
     * the sale. These details include the num_sold of that unique record, and
     * the value of that record. Remember to use '?' to indicate values that
     * will be inputed by the user.
     *
     * For the 'Date' value, use DATE('now').
     *
     */

    /*
     * TODO
     */
    public static String insertSaleDetail = "INSERT INTO Sales_Details (Sale_ID, Record_ID, Num_Sold, Value) "
            + "VALUES (?, ?, ?, ?);";

    /*
     * Update the row in the Record table pertaining to a record in the sale.
     * Specifically, update the Record_Count to reflect the number of that
     * record being bought. Remember to use '?' to indicate values that will be
     * inputed by the user or system.
     *
     * Update Record_Count by taking its current value and subtracting the
     * number of that record being count (Just use a '?' here, since the user
     * indicates this);
     *
     *
     */

    /*
     * TODO
     */
    public static String updateRecordCount = "UPDATE RECORD SET Record_Count = Record_Count - ? "
            + "WHERE Record_ID = ?;";

}