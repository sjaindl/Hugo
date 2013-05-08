package at.tugraz.mobilforum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
				.returnQuery("Select userid from users where username="
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
				.returnQuery("Select userid from users where username='"
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
					.returnQuery("Insert into users (username,password,profilepic) values ('"
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
				.returnQuery("Insert into topics (categoryid,userid,title) values ('"
						+ categoryid + "','" + userid + "','" + title + "')");
		return 0;
	}

	public int postEntry(int topicid, int userid, String entrytext) {
		ResultSet rs = this
				.returnQuery("Insert into entries (topicid,userid,entrytext) values ('"
						+ topicid + "','" + userid + "','" + entrytext + "')");
		return 0;
	}

	public List<Entry> getEntryList(int categoryid, int topicid) {
		
		List<Entry> entries = new LinkedList<Entry>();
		ResultSet rs = this
				.returnQuery("select * from topics where categoryid='"
						+ categoryid + "' and topicid='" +topicid+"'");
		try {
			while(rs.next())
			{
				ResultSet rs_user = this.returnQuery("select * from users where userid='"
						+ rs.getInt("userid") + "'");
				Entry actual_entry = new Entry(rs_user.getString("username"), rs_user.getString("userpicture"),
						rs_user.getString("signature"), rs.getString("entrytext"), rs.getDate("date"), rs.getInt("rating"));
				entries.add(actual_entry);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entries;
	}
	
	public Map<Integer, String> getTopicList(int categoryid) {
		Map<Integer, String> topics = new HashMap<Integer, String>();
		ResultSet rs = this
				.returnQuery("select title from topics where categoryid='"
						+ categoryid + "'");
		try {
			while (rs.next()) {
				topics.put(rs.getInt("id"),rs.getString("title"));
			}
		} catch (java.sql.SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return topics;//todo make it a map
	}

	public ArrayList<Entry> getEntries(int topicid) {

		ArrayList<Entry> entries = new ArrayList<Entry>();
		ResultSet rs = this.returnQuery("select * from entries where topicid='"
				+ topicid + "'");
		try {
			while (rs.next()) {
				int userid = Integer.parseInt(rs.getString("userid"));
				Entry entry = new Entry();
				entry.setRating(Integer.parseInt(rs.getString("rating")));
				entry.setEntrytext(rs.getString("entrytext"));

				entry.setDate(rs.getDate("date")); // Problem!!!

				ResultSet rs1 = this
						.returnQuery("select * from users where userid='"
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
			e.printStackTrace();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void close() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (Exception e) {
				e.printStackTrace();
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

	public ResultSet returnQuery(String query) {
		try {
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean runQuery(String query) {
		try {
			Statement stmt = this.connection.createStatement();
			return stmt.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
