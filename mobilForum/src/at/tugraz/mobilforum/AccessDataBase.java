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
	public static boolean isDefined = true;

	@Override
	public void onCreate(SQLiteDatabase db) {
		
		initDatabase();
		if (isDefined == false) {
			//fillDatabase();
			isDefined = true;
		}
		Log.d(TAG, "db created");
	}
		
	private void fillDatabase() {
		db.execSQL("INSERT INTO " + CATEGORY_TABLE
				+ " VALUES (1,'Haustiere')");
		db.execSQL("INSERT INTO " + CATEGORY_TABLE
				+ " VALUES (2,'Lieblingsfilme')");
		db.execSQL("INSERT INTO " + CATEGORY_TABLE + " VALUES (3,'Hugo')");
		db.execSQL("INSERT INTO " + CATEGORY_TABLE + " VALUES (4,'ESSEN')");
		db.execSQL("INSERT INTO " + CATEGORY_TABLE
				+ " VALUES (5,'Trinken')");

		db.execSQL("INSERT INTO "
				+ USER_TABLE
				+ " VALUES (1, 'Hugo', 'hugo123', 'Hugo', 'Mob', 'test/pic', 'my signature')");

		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (1, 1, 1, 'Katze', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (2, 1, 1, 'Hund', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (3, 1, 1, 'Panda', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (4, 1, 1, 'Kroko', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (5, 1, 1, 'BŠr', 190000000, 3)");

		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (1, 2, 1, 'Matrix', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (2, 2, 1, 'Stirb Langsam 1', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (3, 2, 1, 'Stirb Langsam 2', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (4, 2, 1, 'Stirb Langsam 3', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (5, 2, 1, 'Stirb Langsam 4.0', 190000000, 3)");

		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (1, 3, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (2, 3, 1, 'Schnitzl', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (3, 3, 1, 'Pizza', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (4, 3, 1, 'Lasagne', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (5, 3, 1, 'Ente', 190000000, 3)");

		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (1, 3, 1, 'Bier', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (2, 3, 1, 'Wien', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (3, 3, 1, 'Wasser', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (4, 3, 1, 'Saft', 190000000, 3)");
		db.execSQL("INSERT INTO " + TOPIC_TABLE
				+ " VALUES (5, 3, 1, 'Cola', 190000000, 3)");

		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (1, 1, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (2, 1, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (3, 1, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (4, 1, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (5, 1, 1, 'Kotlett', 190000000, 3)");

		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (2, 2, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (3, 3, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (4, 4, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (5, 1, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (6, 1, 1, 'Kotlett', 190000000, 3)");
		db.execSQL("INSERT INTO " + ENTRY_TABLE
				+ " VALUES (7, 1, 1, 'Kotlett', 190000000, 3)");
	}

	public void initDatabase(){
		db.execSQL("CREATE TABLE IF NOT EXISTS "
				+ USER_TABLE
				+ " (userid INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL, givenname TEXT, surname TEXT, profilepic TEXT, signature TEXT)");

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
				+ " (entryid INTEGER PRIMARY KEY AUTOINCREMENT, topicid INTEGER, userid INTEGER, entrytext TEXT NOT NULL, date INTEGER, rating INTEGER, FOREIGN KEY(topicid) REFERENCES "
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
			db.execSQL("Insert into users (username,password,profilepic) values ('"
					+ username + "','" + password + "','" + profilepic + "')");
			return this.approveUser(username, password);
		}
	}

	public int postTopic(String title, int categoryid, int userid) {
		db.execSQL("Insert into topics (categoryid,userid,title) values ('"
				+ categoryid + "','" + userid + "','" + title + "')");
		return 0;
	}

	public int postEntry(int topicid, int userid, String entrytext) {
		db.execSQL("Insert into entries (topicid,userid,entrytext) values ('"
				+ topicid + "','" + userid + "','" + entrytext + "')");
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
					.getLong(3), cursor.getString(2)));
		}
		return topics;
	}

	public List<Entry> getEntryList(int topicid) {

		Log.d(TAG, "read db entries");
		List<Entry> entries = new ArrayList<Entry>();
		Cursor cursor = db.query(ENTRY_TABLE, new String[] { "userid",
				"rating", "entrytext", "date" }, "topicid='" + topicid + "'",
				null, null, null, null);
		while (cursor.moveToNext()) {
			int userid = cursor.getInt(0);
			Entry entry = new Entry();
			entry.setRating(cursor.getInt(1));
			entry.setEntrytext(cursor.getString(2));

			entry.setDate(cursor.getLong(3));

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

	public boolean isUsernameTaken(String username) {
		Cursor cursor = db.query(USER_TABLE, new String[] { "count(*)" },
				"username='" + username + "'", null, null, null, null);

		Log.d("DT", cursor.getString(0));
		if (cursor.moveToFirst()) {
			Integer userID = cursor.getInt(0);
			if (userID > 0) {
				return true;
			}
		}
		return false;
	}

}