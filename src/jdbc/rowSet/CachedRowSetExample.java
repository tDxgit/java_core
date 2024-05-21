/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.rowSet;

import java.io.Console;
import java.sql.*;
import java.util.Scanner;
import javax.sql.rowset.*;
import javax.sql.rowset.spi.SyncProviderException;

/**
 *
 * @author Hung
 */
public class CachedRowSetExample { 
    static Scanner sc = new Scanner(System.in);
    static String answer;
    static boolean quit = false; 
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");// nap driver
        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
        }
    } 
    public static void main(String[] args) {
        String dbURL = "jdbc:mysql://localhost:3306/sampleDB";
        String username = "root";
        String password = "root";
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            conn.setAutoCommit(false); 
            String sql = "SELECT * FROM student"; 
            Statement statement = conn.createStatement(); 
            ResultSet result = statement.executeQuery(sql); 
            RowSetFactory factory = RowSetProvider.newFactory();
            CachedRowSet rowset = factory.createCachedRowSet(); 
            rowset.setTableName("student"); 
            rowset.populate(result);

            while (!quit) { 
                 insertStudent(rowset);
                if (!readStudent(rowset)) {
                    continue;
                } 
                updateStudent(rowset); 
                deleteStudent(rowset); 
                insertStudent(rowset);

                saveChanges(rowset, conn);

                askToQuit();

            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

    }

    static void readStudentInfo(String position, ResultSet result)
            throws SQLException {
        String name = result.getString("name");
        String email = result.getString("email");
        String major = result.getString("major");

        String studentInfo = "%s: %s - %s - %s\n";
        System.out.format(studentInfo, position, name, email, major);
    }

    static boolean readStudent(ResultSet result) throws SQLException {
        System.out.println("Enter student number: "); 
        int row = sc.nextInt();

        if (result.absolute(row)) {
            readStudentInfo("Student at row " + row + ": ", result);
            return true;
        } else {
            System.out.println("There's no student at row " + row);
            return false;
        }
    }

    static void updateStudent(ResultSet result) throws SQLException {
        System.out.print("Do you want to update this student (Y/N)?: ");
        answer = sc.next();
        if (answer.equalsIgnoreCase("Y")) {
            System.out.print("Update name:");
            String name = sc.next();//.readLine("\tUpdate name: ");
            System.out.print("Update email:");
            String email = sc.next();//console.readLine("\tUpdate email: ");
            System.out.print("Update major:");
            String major = sc.next();//console.readLine("\tUpdate major: ");

            if (!name.equals("")) {
                result.updateString("name", name);
            }
            if (!email.equals("")) {
                result.updateString("email", email);
            }
            if (!major.equals("")) {
                result.updateString("major", major);
            }

            result.updateRow();

            System.out.println("The student has been updated.");
        }

    }

    static void deleteStudent(ResultSet result) throws SQLException {
        //  answer = console.readLine("Do you want to delete this student (Y/N)?: ");
        System.out.print("Do you want to delete this student (Y/N)?: ");
        answer = sc.next();
        if (answer.equalsIgnoreCase("Y")) {
            result.deleteRow();

            System.out.println("The student has been removed.");
        }

    }

    static void insertStudent(ResultSet result) throws SQLException {
        //   answer = console.readLine("Do you want to insert a new student (Y/N)?: ");
        System.out.print("Do you want to insert a new student (Y/N)?: ");
        answer = sc.next();
        if (answer.equalsIgnoreCase("Y")) {
            System.out.print("Update name:");
            String name = sc.next();//.readLine("\tUpdate name: ");
            System.out.print("Update email:");
            String email = sc.next();//console.readLine("\tUpdate email: ");
            System.out.print("Update major:");
            String major = sc.next();//console.readLine("\tUpdate major: ");

            result.moveToInsertRow();

            result.updateNull("student_id");
            result.updateString("name", name);
            result.updateString("email", email);
            result.updateString("major", major);

            result.insertRow();
            result.moveToCurrentRow();

            System.out.println("The student has been added.");
        }

    }

    static void saveChanges(CachedRowSet rowset, Connection conn) {
        //    answer = console.readLine("Do you want to save changes (Y/N)?: ");
        System.out.print("Do you want to save changes (Y/N)?: ");
        answer = sc.next();
        if (answer.equalsIgnoreCase("Y")) {
            try {
                rowset.acceptChanges(conn);
            } catch (SyncProviderException ex) {
                System.out.println("Error commiting changes to the database: " + ex);
            }
        }
    }

    static void askToQuit() {
        //    answer = console.readLine("Do you want to quit (Y/N)?: ");
        System.out.print("Do you want to quit (Y/N)?: ");
        answer = sc.next();
        quit = answer.equalsIgnoreCase("Y");
    }
} 