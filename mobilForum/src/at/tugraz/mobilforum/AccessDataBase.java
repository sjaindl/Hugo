package at.tugraz.mobilforum;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.SQLException;

public class AccessDataBase {

	private String dburl = "sql2.freesqldatabase.com";
	private String dbuser = "sql26597";
	private String dbpassword = "zV5!sC7% ";
	private String Driver = "com.mysql.jdbc.Driver";
	private Connection connection;
	private static AccessDataBase instance = new AccessDataBase();

	private AccessDataBase() {
	}

	public static AccessDataBase getInstance() {
		return instance;
	}

	public int approveUser(String username, String password) {
		ResultSet rs = this
				.ReturnQuery("Select userid from users where username="
						+ username + " and password =" + password);
		String userid = "";
		try {
			while (rs.next()) {
				userid = rs.getString("userid");
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			return -1;
		}
		if (userid != null) {
			// auth: yes
			int iuserid = Integer.parseInt(userid);

			return iuserid;
		} else {
			return 0;
		}
	}

	public int registerUser(String username, String password, String profilepic) {
		ResultSet rs = this
				.ReturnQuery("Select userid from users where username='"
						+ username + "'");
		String userid = "";
		try {
			while (rs.next()) {
				userid = rs.getString("userid");
			}
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
		if (userid != null) {
			// auth: yes
			return -2;
		} else {
			rs = this
					.ReturnQuery("Insert into users (username,password,profilepic) values ('"
							+ username
							+ "','"
							+ password
							+ "','"
							+ profilepic
							+ "')");
			return this.approveUser(username, password);
		}
	}

	public int postTopic(String title, int categoryid, int userid) {
		ResultSet rs = this
				.ReturnQuery("Insert into topics (categoryid,userid,title) values ('"
						+ categoryid + "','" + userid + "','" + title + "')");
		return 0;
	}

	public int postEntry(int topicid, int userid, String entrytext) {
		ResultSet rs = this
				.ReturnQuery("Insert into entries (topicid,userid,entrytext) values ('"
						+ topicid + "','" + userid + "','" + entrytext + "')");
		return 0;
	}

	public ArrayList<String> getTopicList(int categoryid) {
		ArrayList<String> topics = new ArrayList<String>();
		ResultSet rs = this
				.ReturnQuery("select title from topics where categoryid='"
						+ categoryid + "'");
		try {
			while (rs.next()) {
				topics.add(rs.getString("title"));
			}
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return topics;
	}

	public ArrayList<Entry> getEntries(int topicid) {

		ArrayList<Entry> entries = new ArrayList<Entry>();
		ResultSet rs = this.ReturnQuery("select * from entries where topicid='"
				+ topicid + "'");
		try {
			while (rs.next()) {
				int userid = Integer.parseInt(rs.getString("userid"));
				Entry entry = new Entry();
				entry.setRating(Integer.parseInt(rs.getString("rating")));
				entry.setEntrytext(rs.getString("entrytext"));

				entry.setDate(rs.getDate("date")); // Problem!!!

				ResultSet rs1 = this
						.ReturnQuery("select * from users where userid='"
								+ userid + "'");

				while (rs1.next()) {
					entry.setUsername(rs1.getString("username"));
					entry.setUserpicture(rs1.getString("profilepic"));
					entry.setUsersignature(rs1.getString("signature"));
				}

				entries.add(entry);
			}
			return entries;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void Close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (Exception e) {
			}
		}
	}
	
	public boolean isConnected(){
		boolean connected = false;
		try {
			connected = connection != null && connection.isValid(0);
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return connected;
	}

	public void connect() {
		try {
			Class.forName(this.Driver);
			this.connection = DriverManager.getConnection(this.dburl,
					this.dbuser, this.dbpassword);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Connecting with User:" + this.dbuser
					+ " and Password:" + this.dbpassword);
		}
	}

	public ResultSet ReturnQuery(String query) {
		try {
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean RunQuery(String query) {
		try {
			Statement stmt = this.connection.createStatement();
			return stmt.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
