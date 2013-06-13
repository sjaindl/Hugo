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

public class AccessDataBase extends SQLiteOpenHelper {
	static final String USER_TABLE = "users";
	static final String ENTRY_TABLE = "entries";
	static final String TOPIC_TABLE = "topics";
	static final String CATEGORY_TABLE = "categories";
	static final String TAG = "AccessDB";
	final static String DATABASE_NAME = "Forum.db";
	private static AccessDataBase instance;
	private SQLiteDatabase db;
	public static boolean isDefined;
	private Integer[] mThumbIds = {
 			R.drawable.avatar2, R.drawable.bob, R.drawable.calimero,
			R.drawable.daisy, R.drawable.elefant, R.drawable.ente,
			R.drawable.maus, R.drawable.mickey, R.drawable.minnie,
			R.drawable.gary, R.drawable.sandy, R.drawable.spongebob
};
	
	 private String[] imageTitle = {
			 "avatar","bob","calimero","daisy","elefant","ente","maus","mickey","minnie","gary","sandy","spongebob"
	 };

	@Override
	public void onCreate(SQLiteDatabase db) {
		initDatabase(db);
		this.db = db;
		if(!isUsernameTaken("Hugo")){
			fillDatabase(db);
		}
		this.db = null;
		Log.d(TAG, "db created");
	}
		
	private void fillDatabase(SQLiteDatabase db) {
		db.execSQL("INSERT INTO " + CATEGORY_TABLE
				+ " VALUES (1,'Haustiere')");
		db.execSQL("INSERT INTO " + CATEGORY_TABLE
				+ " VALUES (2,'Lieblingsfilme')");
		db.execSQL("INSERT INTO " + CATEGORY_TABLE + " VALUES (3,'Hugo')");
		db.execSQL("INSERT INTO " + CATEGORY_TABLE + " VALUES (4,'Essen')");
		db.execSQL("INSERT INTO " + CATEGORY_TABLE
				+ " VALUES (5,'Trinken')");

		db.execSQL("INSERT INTO "
				+ USER_TABLE
				+ " VALUES (1, 'Hugo', 'hugo123', 'Hugo', 'Mob', 'avatar', '-- \nHugo')");
		
		db.execSQL("INSERT INTO "
				+ USER_TABLE
				+ " VALUES (2, 'Hansi', 'hansi123', 'Hugo', 'Mob', 'elefant', '-- \nHansi')");

		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (1, 1, 1, 'Katze', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (2, 1, 1, 'Hund', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (3, 1, 1, 'Panda', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (4, 1, 1, 'Kroko', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (5, 1, 1, 'Maus', 190000000, 3)");

		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (6, 2, 1, 'Simpsons', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (7, 2, 1, 'Tom und Jerry', 190000000, 3)");
		

		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (11, 4, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (12, 4, 1, 'Schnitzl', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (13, 4, 1, 'Pizza', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (14, 4, 1, 'Lasagne', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (15, 4, 1, 'Ente', 190000000, 3)");

		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (16, 5, 1, 'Himbeersaft', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (17, 5, 1, 'Fanta', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (18, 5, 1, 'Wasser', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (19, 5, 1, 'Sprite', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (20, 5, 1, 'Cola', 190000000, 3)");
		

		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (21, 3, 1, 'Best Forum EVER', 190000000, 3)");

		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (1, 1, 1, 'Fell \n \n Meine Katze hat ein sehr weiches Fell und ist ganz schwarz. \n \n \n \n\n Welche Farbe hat eure Katze?', NULL, 190000000, 3)");
		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (2, 1, 2, 'Meine Katze ist ganz weiß.', NULL, 190000000, 3)");
		
	}

	public void initDatabase(SQLiteDatabase db){
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ USER_TABLE
				+ " (userid INTEGER PRIMARY KEY AUTOINCREMENT, " +
				" username TEXT NOT NULL, password TEXT NOT NULL, givenname TEXT, surname TEXT, profilepic TEXT, signature TEXT)");

		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ CATEGORY_TABLE
				+ " (catid INTEGER PRIMARY KEY AUTOINCREMENT, catname TEXT NOT NULL)");

		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ TOPIC_TABLE
				+ " (topicid INTEGER PRIMARY KEY AUTOINCREMENT, categoryid INTEGER, userid INTEGER, title TEXT NOT NULL, date INTEGER, rating INTEGER, FOREIGN KEY(categoryid) REFERENCES "
				+ CATEGORY_TABLE + "(catid), FOREIGN KEY(userid) REFERENCES "
				+ USER_TABLE + " (userid))");

		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ ENTRY_TABLE
				+ " (entryid INTEGER PRIMARY KEY AUTOINCREMENT, topicid INTEGER, userid INTEGER, entrytext TEXT NOT NULL, imageuri TEXT, date INTEGER, rating INTEGER, FOREIGN KEY(topicid) REFERENCES "
				+ TOPIC_TABLE + " (topicid), FOREIGN KEY(userid) REFERENCES "
				+ USER_TABLE + " (userid))");
	}

	public static boolean hasInstance() {
		return instance != null;
	}

	// should only be called once in the Main Activity
	public static void setInstance(AccessDataBase inst) {
		instance = inst;
	}

	public AccessDataBase(Context context) {
		super(context, DATABASE_NAME, null, 1);
		//context.deleteDatabase("Forum.db"); // FIXXXME: remove after database reset /** TODO: remove after database reset */
		db = getWritableDatabase();
	}

	public static AccessDataBase getInstance() {
		return instance;
	}

	public int approveUser(String username, String password) {
		Cursor cursor = db
				.query(USER_TABLE, new String[] { "userid" }, "username='"
						+ username + "' and password = '" + password + "'",
						null, null, null, null);
		if (cursor.moveToNext()) {
			int userid = cursor.getInt(0);
			// auth: yes
			return userid;
		} else {
			return 0;
		}
	}

	public int registerUser(String username, String password, String profilepic) {
		Cursor cursor = db.query(USER_TABLE, new String[] { "userid" },
				"username='" + username + "'", null, null, null, null);
		if (cursor.moveToNext()) {
			// auth: yes
			return -2;
		} else {
			cursor.close();
			String insertstring = "Insert into users (username,password,givenname,surname,profilepic,signature) values ('"
					+ username + "','" + password + "','" + "Vorname" + "','" + "Nachname" + "','"+ profilepic + "','" +  "Signature" + "')";
			
			db.execSQL("Insert into users (username,password,givenname,surname,profilepic,signature) values ('"
					+ username + "','" + password + "','" + "Vorname" + "','" + "Nachname" + "','"+ profilepic + "','" +  "Signature" + "')");
			Log.d("TAG","Insert -> user: " + username + "password: " + password + "profilepic: " + profilepic);
			Log.d("TAG","Insert String: " + insertstring);
			int ret = this.approveUser(username, password);
			Log.d("TAG","User valid (!=0): " + ret);
			return ret;
		}
	}
	
	public String getUserName(int userid){
		Cursor cursor = db.query(USER_TABLE, new String[] { "username" },
				"userid='" + userid + "'", null, null, null, null);
		cursor.moveToNext();
		return cursor.getString(0);
	}
	
	public int getUserId(String username){
		Cursor cursor = db.query(USER_TABLE, new String[] { "userid" },
				"username='" + username + "'", null, null, null, null);
		cursor.moveToNext();
		return cursor.getInt(0);
	}

	public int postTopic(String title, int categoryid, int userid, String username) {
		db.execSQL("Insert into topics (categoryid,userid,title) values ('"
				+ categoryid + "','" + userid + "','" + title + "')");
		return 0;
	}

	public int postEntry(int topicid, int userid, Entry entry) {
		db.execSQL("Insert into entries (topicid,userid,entrytext,imageuri) values ('"
				+ topicid + "','" + userid + "','" + entry.getEntrytext() + "','" + entry.getUploadedImageURI() + "')");
		return 0;
	}

	public Entry getRandomEntryFromTopic(int topicid) {
		Random r = new Random();
		List<Entry> entrylist = this.getEntryList(topicid);
		return entrylist.get(r.nextInt(entrylist.size()));
	}

	public int getRandomCategory() {
		List<Integer> keysAsArray = new ArrayList<Integer>(this
				.getCategoryList().keySet());
		Random r = new Random();

		return keysAsArray.get(r.nextInt(keysAsArray.size()));
	}

	public int getRandomTopicFromCategory(int category) {
		/* TODO: change return type to Topic */
		// List<Topics> keysAsArray = new
		// ArrayList<Integer>(this.getTopicList(category).keySet());
		Random r = new Random();
		return r.nextInt(this.getTopicList(category).size());
	}

	public Map<Integer, String> getCategoryList() {
		Map<Integer, String> categories = new HashMap<Integer, String>();
		Cursor cursor = db.query(CATEGORY_TABLE, new String[] { "catid",
				"catname" }, null, null, null, null, null);
		while (cursor.moveToNext()) {
			categories.put(cursor.getInt(0), cursor.getString(1));
		}
		return categories;
	}

	public List<Topic> getTopicList(int categoryid) {
		List<Topic> topics = new ArrayList<Topic>();
		Cursor cursor = db.query(TOPIC_TABLE, new String[] { "topicid",
				"title", "userid", "date" }, "categoryid='" + categoryid + "'",
				null, null, null, null);
		while (cursor.moveToNext()) {
			topics.add(new Topic(cursor.getInt(0), cursor.getString(1), cursor
					.getLong(3), getUserName(cursor.getInt(2))));
			
		}
		return topics;
	}
	
	public int getCategoryFromTopic(int topicid) {
		Cursor cursor = db.query(TOPIC_TABLE, new String[] { "categoryid",
				"title", "userid", "date" }, "topicid='" + topicid + "'",
				null, null, null, null);
		cursor.moveToNext();
		return cursor.getInt(0);
	}

	public String getTopicFromId(int topicid) {
		Cursor cursor = db.query(TOPIC_TABLE, new String[] { "title"},
		"topicid='" + topicid + "'",
				null, null, null, null);
		cursor.moveToNext();
		return cursor.getString(0);
	}
	
	public String getCategoryFromId(int categoryid) {
		Cursor cursor = db.query(CATEGORY_TABLE, new String[] { "catname"},
		"catid='" + categoryid + "'",
				null, null, null, null);
		cursor.moveToNext();
		return cursor.getString(0);
	}

	public List<Entry> getEntryList(int topicid) {


		Log.d(TAG, "read db entries");
		List<Entry> entries = new ArrayList<Entry>();
		Cursor cursor = db.query(ENTRY_TABLE, new String[] { "userid",
				"rating", "entrytext", "imageuri", "date" }, "topicid='" + topicid + "'",
				null, null, null, null);
		while (cursor.moveToNext()) {
			int userid = cursor.getInt(0);
			Entry entry = new Entry();
			entry.setUserid(userid);
			entry.setRating(cursor.getInt(1));
			entry.setEntrytext(cursor.getString(2));
			entry.setUploadedImageURI(cursor.getString(3));
			entry.setDate(cursor.getLong(4));
				Cursor cuser = db.query(USER_TABLE, new String[] { "username",
					"profilepic", "signature" }, "userid='" + userid + "'",
					null, null, null, null);

			while (cuser.moveToNext()) {
				entry.setUsername(cuser.getString(0));
				entry.setUserpicture(cuser.getString(1));
				entry.setUsersignature(cuser.getString(2));
			}

			entries.add(entry);
		}

		return entries;
	}

	public Cursor query(String query) {
		Cursor c = db.rawQuery(query, null);
		return c;
	}

	public void execute(String query) {
		db.execSQL(query);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

		Log.d(TAG, "db upgraded");
	}

	public List<String> getAvatarFilenames() {
		List<String> avatars = new ArrayList<String>();
		// Query (select profilepic from user)
		Cursor cursor = db.query(USER_TABLE, new String[] { "profilepic" },
				null, null, null, null, null);
		while (cursor.moveToNext()) {
			avatars.add(cursor.getString(0));
		}
		return avatars;
	}
	
	public int getAvatarId(int userid){
		Cursor cursor = db.query(USER_TABLE, new String[] { "profilepic" },
				"userid='" + userid + "'", null, null, null, null);
		cursor.moveToNext();
		String img = cursor.getString(0);
		
		if(img.equals(imageTitle[0])){ return mThumbIds[0];}
		if(img.equals(imageTitle[1])){ return mThumbIds[1];}
		if(img.equals(imageTitle[2])){ return mThumbIds[2];}
		if(img.equals(imageTitle[3])){ return mThumbIds[3];}
		if(img.equals(imageTitle[4])){ return mThumbIds[4];}
		if(img.equals(imageTitle[5])){ return mThumbIds[5];}
		if(img.equals(imageTitle[6])){ return mThumbIds[6];}
		if(img.equals(imageTitle[7])){ return mThumbIds[7];}
		if(img.equals(imageTitle[8])){ return mThumbIds[8];}
		if(img.equals(imageTitle[9])){ return mThumbIds[9];}
		if(img.equals(imageTitle[10])){ return mThumbIds[10];}
		
		return mThumbIds[0];
		
	}

	public boolean isUsernameTaken(String username) {
		Cursor cursor = db.query(USER_TABLE, new String[] { "count(*)" },
				"username='" + username + "'", null, null, null, null);
		if (cursor.moveToFirst()) {
			Integer cnt = cursor.getInt(0);
			if (cnt > 0) {
				cursor.close();
				return true;
			}
		}
		cursor.close();
		return false;
	}

}