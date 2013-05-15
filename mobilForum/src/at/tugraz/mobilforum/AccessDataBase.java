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

import android.content.Context;
import android.database.*;
import android.database.sqlite.*;


public class AccessDataBase extends SQLiteOpenHelper{
	static final String USER_TABLE = "users";
	static final String ENTRY_TABLE = "entries";
	static final String TOPIC_TABLE = "topics";
	static final String CATEGORY_TABLE = "categories";
	SQLiteDatabase database;
	@Override
	public void onCreate(SQLiteDatabase db) {
	  // TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+USER_TABLE+" (userid INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL, givenname TEXT, surname TEXT, profilepic TEXT, signature TEXT)");
		
		db.execSQL("CREATE TABLE "+CATEGORY_TABLE+" (catid INTEGER PRIMARY KEY AUTOINCREMENT, catname TEXT NOT NULL");
		  
		db.execSQL("CREATE TABLE "+TOPIC_TABLE+" (topicid INTEGER PRIMARY KEY AUTOINCREMENT, FOREIGN KEY(categoryid) REFERENCES"+ CATEGORY_TABLE +"(categoryid), 			FOREIGN KEY(userid) REFERENCES"+ USER_TABLE +"(userid), title TEXT NOT NULL, date DATE, rating INTEGER");
		
		db.execSQL("CREATE TABLE "+ENTRY_TABLE+" (entryid INTEGER PRIMARY KEY AUTOINCREMENT, FOREIGN KEY(topicid) REFERENCES"+ TOPIC_TABLE +"(topicid), FOREIGN 			KEY(userid) REFERENCES"+ USER_TABLE +"(userid), entrytext TEXT NOT NULL, date DATE, rating INTEGER");
		  
		db.execSQL("INSERT INTO "+ CATEGORY_TABLE +" VALUES (\"Haustiere\")");
		db.execSQL("INSERT INTO "+ CATEGORY_TABLE +" VALUES (\"Lieblingsfilme\")");
		db.execSQL("INSERT INTO "+ CATEGORY_TABLE +" VALUES (\"Hugo\")");
		db.execSQL("INSERT INTO "+ CATEGORY_TABLE +" VALUES (\"ESSEN\")");
		db.execSQL("INSERT INTO "+ CATEGORY_TABLE +" VALUES (\"Trinken\")");
		
		db.execSQL("INSERT INTO "+ USER_TABLE +" VALUES (\"Hugo\", \"hugo123\")");
		db.execSQL("INSERT INTO "+ TOPIC_TABLE +" VALUES (4, 1, \"Kotlett\")"); 
		database  = db;
	 }

	final static String DATABASE_NAME = "Forum.db";
	private static AccessDataBase instance;
	

	public static boolean hasInstance(){
		return instance != null;
	}

	// should only be called once in the Main Activity
	public static void setInstance(AccessDataBase inst){
		instance = inst;
	}
	
	public AccessDataBase(Context context) {
		  super(context, DATABASE_NAME, null,1); 
	}

	public static AccessDataBase getInstance() {
		return instance;
	}

	/*public int approveUser(String username, String password) {
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
	}*/

	/*public List<Entry> getEntryList(int categoryid, int topicid) {
		
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
*/
	public ArrayList<Entry> getEntries(int topicid) {

		ArrayList<Entry> entries = new ArrayList<Entry>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(ENTRY_TABLE, new String[]{"userid","rating","entrytext","date"}, "topicid='"+ topicid + "'", null, null, null, null);
		while (cursor.moveToNext()) {
			int userid = cursor.getInt(0);
			Entry entry = new Entry();
			entry.setRating(cursor.getInt(1));
			entry.setEntrytext(cursor.getString(2));

			entry.setDate(cursor.getLong(3));

			Cursor cuser = db.query(USER_TABLE, new String[]{"username","profilepic","signature"}, "userid='"+ userid + "'", null, null, null, null);

			while (cuser.moveToNext()) {
				entry.setUsername(cuser.getString(0));
				entry.setUserpicture(cuser.getString(1));
				entry.setUsersignature(cuser.getString(2));
			}

			entries.add(entry);
		}
		return entries;
	}

	/*public void close() {
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
	}*/

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
