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
public class StoredProcedureCallInsert {
    static{
     try {
            Class.forName("com.mysql.jdbc.Driver");// nap driver
        } 
        catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
        } 
    };
public static void main(String[] args) {
    String dbURL = "jdbc:mysql://localhost:3306/sampleDB";
    String user = "root";
    String password = "root";

    try (
       Connection conn = DriverManager.getConnection(dbURL, user, password);
       CallableStatement statement 
               = conn.prepareCall("{call create_author(?, ?)}");) { 
        statement.setString(1, "Bill Gates");
        statement.setString(2, "bill@microsoft.com"); 
        statement.execute();
        statement.close(); 
        System.out.println("Stored procedure called successfully!");

    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
}
