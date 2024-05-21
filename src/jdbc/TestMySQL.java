/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL E7450
 */
public class TestMySQL {
    public static void main(String[] args) {
        String urlMySQL = "jdbc:mysql://localhost/test";
        Connection con=null; 
         try {
            Class.forName("com.mysql.jdbc.Driver");// nap driver
        } 
        catch(java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
        }      
         
         try {
            con = DriverManager.getConnection(urlMySQL,"root","root");//ket noi
        } catch(SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
         // Create Table
        String createString;
        createString = "Create Table Products2 " + "(COF_NAME VARCHAR(32)," +
                            "SUP_ID INTEGER, " + "PRICE FLOAT, " +
                            "SALES INTEGER, " +  "TOTAL INTEGER)"; 
        try {
            CreateTable(con,createString);
        } catch (SQLException ex) {
            Logger.getLogger(TestMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static void CreateTable(Connection conn, String createTable) throws SQLException{
          
        Statement stmt;
        stmt = conn.createStatement();// buoc 3           
        stmt.executeUpdate(createTable);            
        stmt.close();        
    }
}
