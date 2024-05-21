/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.callable;

import java.sql.*;

/**
 *
 * @author Hung
 */
public class StoredProcedureCallOuputParameters {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");// nap driver
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
        }
    }

    ;
    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://localhost:3306/sampleDB";
        String user = "root";
        String password = "root";

        try (
          Connection conn = DriverManager.getConnection(dbURL, user, password);) {
           CallableStatement statement
                    = conn.prepareCall("{call summary_report (?,?,?,?)}");
  
            statement.registerOutParameter(2, Types.INTEGER);
            statement.registerOutParameter(3, Types.DOUBLE);
            statement.registerOutParameter(4, Types.DOUBLE);

            statement.setString(1, "Java");   
            
            statement.execute(); 
             Integer totalBook = statement.getInt(2); 
            Double totalValue = statement.getDouble(3);
             Double highPrice = statement.getDouble("highPrice");

            System.out.println("Total books: " + totalBook);
            System.out.println("Total value: " + totalValue);
            System.out.println("High price: " + highPrice);
            statement.close(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
