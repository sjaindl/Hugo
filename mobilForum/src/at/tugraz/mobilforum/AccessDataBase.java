package at.tugraz.mobilforum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;

public class AccessDataBase {

	private String dburl = "sql2.freesqldatabase.com";
	private String dbuser = "sql26597";
	private String dbpassword = "zV5!sC7% ";
	private String Driver = "com.mysql.jdbc.Driver";
	private Connection connection;
	AccessDataBase(){
		this.Connect();
	}
	public int approveUser(String username, String password){
		ResultSet rs =  this.ReturnQuery("Select userid from users where username=" + username + " and password =" + password);
		String userid = "";
		try {
			while(rs.next()){
				userid = rs.getString("userid");
			}
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		if(userid!=null){
			//auth: yes
			int iuserid = Integer.parseInt(userid);
			
			return iuserid;
		}
		else{
			return 0;
		}
	}
	
	public int registerUser(String username, String password, String profilepic){
		ResultSet rs =  this.ReturnQuery("Select userid from users where username='" + username + "'");
		String userid = "";
		try {
			while(rs.next()){
				userid = rs.getString("userid");
			}
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		if(userid!=null){
			//auth: yes
			return -2;
		}
		else{
			rs = this.ReturnQuery("Insert into users (username,password,profilepic) values ('" + username + "','" + password + "','" + profilepic + "')");
			try {
				while(rs.next()){
					userid = rs.getString("userid");
				}
			} catch (java.sql.SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1;
			}
				
		}
		
		return 0;
		
	}
	
	
	
	
	
	
	  public void Close() {
	        if (this.connection != null) {
	            try {
	                this.connection.close();
	            } catch (Exception e) {
	            }
	        }
	    }
	 public void Connect() {
	        try {
	            Class.forName(this.Driver);
	            this.connection = DriverManager.getConnection(this.dburl,
	                    this.dbuser, this.dbpassword);
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Error Connecting with User:" + this.dbuser + " and Password:" + this.dbpassword);
	        }
	    }
	 
	    public ResultSet ReturnQuery(String query) {
	        try {
	            Statement stmt = this.connection.createStatement();
	            ResultSet rs = stmt.executeQuery(query);
	            return rs;
	        } catch (SQLException e) {
	            System.err.println(e.toString());
	            return null;
	        }
	    }
	 
	   public boolean RunQuery(String query) {
	        try {
	            Statement stmt = this.connection.createStatement();
	            return stmt.execute(query);
	        } catch (Exception e) {
	            //  e.printStackTrace();
	            return false;
	        }
	    }
	   
	
}
