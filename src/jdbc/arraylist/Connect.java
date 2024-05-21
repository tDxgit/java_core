package jdbc.arraylist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connect {
	 private String url ;
	 private String pass ;
	 private String user ;
	 private Connection con;
   	 private Statement stmt;
	public Connect(){
	  };
	public Connect(String url, String user, String pass){
		  this.url = url;
		  this.user = user;
		  this.pass = pass;
	  }
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void ketNoi(){
  		try {
  			Class.forName("com.mysql.jdbc.Driver");
  			con = DriverManager.getConnection(url, user, pass);
    		stmt = con.createStatement();
  		} catch (ClassNotFoundException e) {
  			System.out.println("loi");
  		} catch (SQLException e) {
			System.out.println(e);
		}

	  }
    // Select du lieu do ra ArrayList
    public ArrayList<SinhVien> ResultArrList(String sql){
         ArrayList<SinhVien> arrL = new ArrayList<SinhVien>();
    	try {
    		if(con == null || con.isClosed()){
        		ketNoi();
        	}
            ResultSet kq = stmt.executeQuery(sql);
            try{
                while(kq.next()){
                	arrL.add(new SinhVien(kq.getString("tenSV"),kq.getString("ngaysinh"),kq.getString("quequan"),kq.getFloat("diemTb")));
                }
                } catch(Exception e){
                	System.out.println(e);
                }
                return arrL;
    		} catch (Exception e) {
    		 System.out.print(e);
    		}
    		return null;
    	}
    // Insert
      public boolean addSinhVien(SinhVien a){
    	  String sql = "insert into sinhvien values(\""+ a.getHoTen()+"\",\""+a.getNgaySinh()+"\",\""+a.getQueQuan()+"\","+a.getDiemTB()+")";
    	  //System.out.println(sql);
    	  try{
    		  if(con == null || con.isClosed()){
          		ketNoi();
          	  }
    		  stmt.execute(sql);
    	  }catch(Exception e){
    		  System.out.println(e);
    		  return false;
    	  }
    	  return true;
      }
      public boolean deleteSV(String name){
    	  String sql = "delete from sinhvien where tensv =\""+name+"\"";
    	 // System.out.println(sql);
    	  try{
    		  if(con == null || con.isClosed()){
          		ketNoi();
          	  }
    		  stmt.execute(sql);
    	  }catch(Exception e){
    		  System.out.println(e);
    		  return false;
    	  }
    	  return true;
      }

      public boolean suaSV(SinhVien a){
    	  String sql = "update sinhvien set ngaysinh= \""+a.getNgaySinh()+"\" ,quequan = \""+a.getQueQuan()+"\" ,DiemTB = \""+a.getDiemTB()+"\" where tensv = \""+a.getHoTen()+"\"";
    	  try{
    		  if(con == null || con.isClosed()){
    			  ketNoi();
    		  }
    		  stmt.execute(sql);
    		  return true;
    	  } catch(SQLException e){
    		  System.out.println(e);
    	  }
    	  return false;
      }


      public void closeConnect(){
    	  try{
    	     if(!con.isClosed()){
    		   con.close();
    		   };
    	  } catch(SQLException e){
    		  System.out.println(e);
    	  }
      }

}
