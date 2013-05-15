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
import java.util.Random;

import android.content.Context;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;


public class AccessDataBase extends SQLiteOpenHelper{
	static final String USER_TABLE = "users";
	static final String ENTRY_TABLE = "entries";
	static final String TOPIC_TABLE = "topics";
	static final String CATEGORY_TABLE = "categories";
	static final String TAG = "AccessDB";
	
	@Override
	public void onCreate(SQLiteDatabase db) {
	  // TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+USER_TABLE+" (userid INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL, givenname TEXT, surname TEXT, profilepic TEXT, signature TEXT)");
		
		db.execSQL("CREATE TABLE "+CATEGORY_TABLE+" (catid INTEGER PRIMARY KEY AUTOINCREMENT, catname TEXT NOT NULL)");
		  
		db.execSQL("CREATE TABLE "+TOPIC_TABLE+" (topicid INTEGER PRIMARY KEY AUTOINCREMENT, categoryid INTEGER, userid INTEGER, title TEXT NOT NULL, date INTEGER, rating INTEGER, FOREIGN KEY(categoryid) REFERENCES "+ CATEGORY_TABLE +"(catid), FOREIGN KEY(userid) REFERENCES "+ USER_TABLE +" (userid))");
		
		db.execSQL("CREATE TABLE "+ENTRY_TABLE+" (entryid INTEGER PRIMARY KEY AUTOINCREMENT, topicid INTEGER, userid INTEGER, entrytext TEXT NOT NULL, date INTEGER, rating INTEGER, FOREIGN KEY(topicid) REFERENCES "+ TOPIC_TABLE +" (topicid), FOREIGN KEY(userid) REFERENCES "+ USER_TABLE +" (userid))");
		  
		db.execSQL("INSERT INTO "+ CATEGORY_TABLE +" VALUES (1,'Haustiere')");
		db.execSQL("INSERT INTO "+ CATEGORY_TABLE +" VALUES (2,'Lieblingsfilme')");
		db.execSQL("INSERT INTO "+ CATEGORY_TABLE +" VALUES (3,'Hugo')");
		db.execSQL("INSERT INTO "+ CATEGORY_TABLE +" VALUES (4,'ESSEN')");
		db.execSQL("INSERT INTO "+ CATEGORY_TABLE +" VALUES (5,'Trinken')");
		
		db.execSQL("INSERT INTO "+ USER_TABLE +" VALUES (1, 'Hugo', 'hugo123', 'Hugo', 'Mob', 'test/pic', 'my signature')");
		db.execSQL("INSERT INTO "+ TOPIC_TABLE +" VALUES (1, 4, 1, 'Kotlett', 190000000, 3)"); 
		Log.d(TAG, "db created");
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

	public int approveUser(String username, String password) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(USER_TABLE, new String[]{"userid"}, "username='"
				+ username + "' and password = '" + password+ "'", null, null, null, null);
		if (cursor.moveToNext()) {
			int userid = cursor.getInt(0);
			// auth: yes
			return userid;
		} else {
			return 0;
		}
	}

	public int registerUser(String username, String password, String profilepic) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(USER_TABLE, new String[]{"userid"}, "username='"
				+ username + "'", null, null, null, null);
		if (cursor.moveToNext()) {
			// auth: yes
			return -2;
		} else {
			db = getWritableDatabase();
			db.execSQL("Insert into users (username,password,profilepic) values ('"
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
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("Insert into topics (categoryid,userid,title) values ('"
						+ categoryid + "','" + userid + "','" + title + "')");
		return 0;
	}

	public int postEntry(int topicid, int userid, String entrytext) {
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL("Insert into entries (topicid,userid,entrytext) values ('"
						+ topicid + "','" + userid + "','" + entrytext + "')");
		return 0;
	}
	
	public Entry getRandomEntryFromTopic(int topicid){
		Random r = new Random();
		List<Entry> entrylist = this.getEntryList(topicid);	
		return entrylist.get(r.nextInt(entrylist.size()));
	}
	
	public int getRandomCategory(){
		List<Integer> keysAsArray = new ArrayList<Integer>(this.getCategoryList().keySet());
				Random r = new Random();

				return keysAsArray.get(keysAsArray.get(r.nextInt(keysAsArray.size())));
	}

	public int getRandomTopicFromCategory(int category){
		List<Integer> keysAsArray = new ArrayList<Integer>(this.getTopicList(category).keySet());
		Random r = new Random();
		return keysAsArray.get(keysAsArray.get(r.nextInt(keysAsArray.size())));
	}
	
	public Map<Integer, String> getCategoryList(){
		Map<Integer, String> categories = new HashMap<Integer, String>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TOPIC_TABLE, new String[]{"catid", "catname"},  null, null, null, null, null);
		while (cursor.moveToNext()) {
			categories.put(cursor.getInt(0),cursor.getString(1));
		}
		return categories;
	}
	
	public Map<Integer, String> getTopicList(int categoryid) {
		Map<Integer, String> topics = new HashMap<Integer, String>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(TOPIC_TABLE, new String[]{"topicid", "title"},  "categoryid='"
				+ categoryid + "'", null, null, null, null);
		while (cursor.moveToNext()) {
			topics.put(cursor.getInt(0),cursor.getString(1));
		}
		return topics;
	}

	public List<Entry> getEntryList(int topicid) {

		Log.d(TAG, "read db entries");
		List<Entry> entries = new ArrayList<Entry>();
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

		Log.d(TAG, "done");
		return entries;
	}

	public Cursor query(String query) {
		return getReadableDatabase().rawQuery(query, null);
	}
	
	public void execute(String query) {
		getWritableDatabase().execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

		Log.d(TAG, "db upgraded");
	}

}
