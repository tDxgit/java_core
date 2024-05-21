package jdbc.rowSet;

import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JoinableExample { 
private final static String DB_URL = "jdbc:mysql://localhost:3306/example_db";
private final static String USR = "root";
private final static String PWD = "root";
private final static String EMPLOYEE_TABLE = "emp_table";
private final static String ADDRESS_TABLE = "emp_addr_table";
private final static String ID = "empid";
private final static String NAME = "name";
private final static String CITY = "city";

public static void main(String [] args)
                throws Exception {

        Connection con = getDbConnection();

        CachedRowSet crs1 = getCachedRowset();
        populateRowset(crs1, con, EMPLOYEE_TABLE, ID, NAME);

        CachedRowSet crs2 = getCachedRowset();
        populateRowset(crs2, con, ADDRESS_TABLE, ID, CITY);

        JoinRowSet jrs = getJoinRowset(crs1, crs2);
        queryJoinRowset(jrs);

        crs1.close();
        crs2.close();
        jrs.close();
        System.out.println("- Close rowsets.");
}

private static Connection getDbConnection()
                throws SQLException {

        System.out.println("- Get connection to database: " + DB_URL);
        Connection con = DriverManager.getConnection(DB_URL, USR, PWD);
        return con;
}

private static CachedRowSet getCachedRowset()  throws SQLException { 
        System.out.println("- Create cached rowset");
        RowSetFactory rsFactory = RowSetProvider.newFactory();
        CachedRowSet rowset = rsFactory.createCachedRowSet();
        return rowset;
}

private static void populateRowset(CachedRowSet crs, Connection con, String tableName,
        String col1, String col2)  throws SQLException { 
        String sql = "SELECT * FROM " + tableName;
        crs.setCommand(sql);
        crs.execute(con);
        crs.last(); 
        System.out.println("Total rows: " + crs.getRow());

        queryCrs(crs, col1, col2);
}

private static void queryCrs(CachedRowSet crs, String col1, String col2)
                throws SQLException { 
        crs.beforeFirst(); 
        while (crs.next()) {

                String s1 = crs.getString(col1);
                String s2 = crs.getString(col2);
                System.out.println("[" + col1 + ", " + col2 + "]: " + s1 + ", " + s2);
        }
}

private static JoinRowSet getJoinRowset(CachedRowSet crs1, CachedRowSet crs2)
                throws SQLException { 
        System.out.println("- Create join rowset");
        RowSetFactory rsFactory = RowSetProvider.newFactory();
        JoinRowSet jrs = rsFactory.createJoinRowSet(); 
        System.out.println("- Add two cached rowsets, joined on: " + ID);
        crs1.setMatchColumn(ID);
        jrs.addRowSet(crs1);
        jrs.addRowSet(crs2, ID);

        return jrs;
}

private static void queryJoinRowset(JoinRowSet jrs)
                throws SQLException {

        System.out.println("- Query join rowset:");

        jrs.last();
        System.out.println("Total rows: " + jrs.getRow());

        jrs.beforeFirst();

        while (jrs.next()) {

                String s1 = jrs.getString(ID);
                String s2 = jrs.getString(NAME);
                String s3 = jrs.getString(CITY);
                System.out.println("[" + ID + ", " + NAME + ", " + CITY + "]: " + 
                                s1 + ", " + s2 + ", " + s3);
        }
}
}