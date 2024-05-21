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
public class StoredProcedureCallSelect {

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
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            CallableStatement statement = conn.prepareCall("{call get_books(?)}");
        ) { 
            statement.setInt(1, 0); 
            boolean hadResults = statement.execute(); 
            // print headings
            System.out.println("| Title | Description | Rating |");
            System.out.println("================================"); 
            while (hadResults) {
                ResultSet resultSet = statement.getResultSet(); 
                // process result set
                while (resultSet.next()) {
                    String title = resultSet.getString("title");
                    String description = resultSet.getString("description");
                    int rating = resultSet.getInt("rating");
 
                    System.out.println(
                            "| " + title + " | " + description + " | " + rating + " |");
                } 
                hadResults = statement.getMoreResults();
            } 
            statement.close(); 
        } catch (SQLException ex) {  ex.printStackTrace();  }
    }
}
