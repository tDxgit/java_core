/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.sampleDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Hung
 */
public class DeleteData {

    public static void main(String[] args) {
         try {
            Class.forName("com.mysql.jdbc.Driver");// nap driver
        } 
        catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
        }  
        String dbURL = "jdbc:mysql://localhost:3306/sampledb";
        String username = "root";
        String password = "root";

        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {

            String sql = "DELETE FROM Users WHERE username=?";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "bill");

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
