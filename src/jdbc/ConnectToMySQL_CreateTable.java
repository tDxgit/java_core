package jdbc;
import java.sql.*;
public class ConnectToMySQL_CreateTable {
    public static void main(String args[]) throws SQLException {
        String urlMySQL = "jdbc:mysql://localhost/test";//port=3306
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
//        String createString;
//        createString = "Create Table Products " + "(COF_NAME VARCHAR(32)," +
//                            "SUP_ID INTEGER, " + "PRICE FLOAT, " +
//                            "SALES INTEGER, " +  "TOTAL INTEGER)"; 
       // CreateTable(con,createString);
        //
        InsertprepareStatement(con);
        SelectprepareStatement(con, "Coffee2");
        UpdateprepareStatement( con, "Coffee2", 1000.3);
        DeleteprepareStatement(con, "Coffee1");
    }
    public static void CreateTable(Connection conn, String createTable) throws SQLException{
          
        Statement stmt;
        stmt = conn.createStatement();// buoc 3           
        stmt.executeUpdate(createTable);            
        stmt.close();        
    }
    public static void InsertprepareStatement(Connection conn){
	String insertQuery = "INSERT INTO Products VALUES(?,?,?,?,?);";
	if (conn != null){
            try{
                    PreparedStatement prest = conn.prepareStatement(insertQuery);
                    prest.setString(1, "Coffee1");
                    prest.setInt(2, 2);
                    prest.setDouble(3, 3.1);
                    prest.setInt(4, 2);
                    prest.setInt(5, 2);                
                    prest.executeUpdate();
                    prest.setString(1, "Coffee2");
                    prest.setInt(2, 2);
                    prest.setDouble(3, 3.1);
                    prest.setInt(4, 2);
                    prest.setInt(5, 2); 
                    prest.executeUpdate();
                    prest.close();
            }catch(SQLException e){
                    e.printStackTrace();
            }
	}
    }
    public static void SelectprepareStatement(Connection conn, String nameProduct){
	String selectQuery = "Select * from Products where COF_NAME=?";
	if (conn != null){
            try{   
                    PreparedStatement prest = conn.prepareStatement(selectQuery);
                    prest.setString(1, nameProduct);
                  ResultSet rs =  prest.executeQuery();
		while (rs.next()){
			String name = rs.getString(1);
			double val = rs.getDouble(3);
			System.out.println("Name= "+name + "\t\t Price=" + val);
		}

                    prest.close();
            }catch(SQLException e){
                    e.printStackTrace();
            }
	}
    }
    public static void UpdateprepareStatement(Connection conn, String nameProduct, double newPrice){
	String selectQuery = "Update Products set PRICE=? where COF_NAME=?";
	if (conn != null){
            try{
                    PreparedStatement prest = conn.prepareStatement(selectQuery);
                    prest.setDouble(1, newPrice);
                    prest.setString(2, nameProduct);
                   prest.executeUpdate();		

                    prest.close();
            }catch(SQLException e){
                    e.printStackTrace();
            }
	}
    }
    public static void DeleteprepareStatement(Connection conn, String nameProduct){
	String selectQuery = "Delete from Products where COF_NAME=?";
	if (conn != null){
            try{
                   PreparedStatement prest = conn.prepareStatement(selectQuery);                  
                   prest.setString(1, nameProduct);
                   prest.executeUpdate();
                   prest.close();
            }catch(SQLException e){
                    e.printStackTrace();
            }
	}
    }
}

