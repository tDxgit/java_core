/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.sampleDB;

/**
 *
 * @author Hung
 */

import java.sql.*; 
public class ScrollResultSetDemo { 
public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/sampledb";
    String username = "root";
    String password = "root"; 
    try (Connection conn = DriverManager.getConnection(url, username, password)) { 
        String sql = "SELECT * FROM Users";

        Statement statement = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet result = statement.executeQuery(sql); 
        result.first(); 
        readStudentInfo("first", result); 
        result.relative(1); 
        readStudentInfo("relative(1)", result); 
        result.previous(); 
        readStudentInfo("previous", result);  
        result.last(); 
        readStudentInfo("last", result); 
///=== Quay ve đâu tiên
        result.first(); 
        readStudentInfo("first", result); //
        

    } catch (SQLException ex) {
        ex.printStackTrace();
    }

}
 
    private static void readStudentInfo(String position, ResultSet result)
            throws SQLException { 
        String name = result.getString("username");
        String pass = result.getString("password");
        String fullname = result.getString("fullname");
         String email = result.getString("email");
 
        String studentInfo = "%s: %s %s - %s - %s\n";
        System.out.format(studentInfo, position, name, pass, fullname, email );
    }
}