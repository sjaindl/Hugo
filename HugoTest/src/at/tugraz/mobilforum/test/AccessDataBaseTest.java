package at.tugraz.mobilforum.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import at.tugraz.mobilforum.AccessDataBase;
import at.tugraz.mobilforum.Entry;
<<<<<<< HEAD
=======
import at.tugraz.mobilforum.Topic;
import android.database.Cursor;
>>>>>>> master
import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;

public class AccessDataBaseTest extends AndroidTestCase {

	// has to be in the DataBase!
	private static final String TEST_USER_NAME = "Hugo";
	private static final String TEST_USER_PASSWORD = "hugo123";
	private static final String TEST_USER_PIC = "hugopic";
	private static final String TEST_TOPIC_NAME = "hugotopic";
	private static final String TEST_ENTRY_TEXT = "Some random post entry text.";
	private static final int TEST_CAT_ID = 42;
	private static final int TEST_TOPIC_ID = 101;
	
	public void testInstance(){
		AccessDataBase instance = AccessDataBase.getInstance();
		assertNotNull(instance);
	}
	
	public void testConnection(){
		AccessDataBase instance = AccessDataBase.getInstance();
		instance.connect();
		assertTrue(instance.isConnected());
		instance.close();
		assertFalse(instance.isConnected());
	}
	
	public void testUserRegistration(){
		AccessDataBase instance = AccessDataBase.getInstance();
		connect();
		int userID = instance.registerUser(TEST_USER_NAME, TEST_USER_PASSWORD, TEST_USER_PIC);
		assertTrue("Create user with existing user name",userID<=0);
		userID = instance.registerUser(TEST_USER_NAME+"@"+System.currentTimeMillis(), TEST_USER_PASSWORD, TEST_USER_PIC);
		assertTrue("Cannot register new user",userID>0);
	}
	
	public void testUserApproval(){
		connect();
		AccessDataBase instance = AccessDataBase.getInstance();
		int userID = instance.approveUser(TEST_USER_NAME, "falsePW");
		assertTrue("False password works for user approval",userID<=0);
		userID = instance.approveUser(TEST_USER_NAME, TEST_USER_PASSWORD);
		assertTrue("Cannot approve existing user",userID>0);
	}
	
	public void testPostTopic(){
		connect();
		int errorCode = AccessDataBase.getInstance().postTopic(TEST_TOPIC_NAME, TEST_CAT_ID, 42);
		assertEquals("Cannot post Topic", 0, errorCode);
		List<Topic> topics =  AccessDataBase.getInstance().getTopicList(TEST_CAT_ID);
		topics = AccessDataBase.getInstance().getTopicList(TEST_CAT_ID);
		boolean isInList = false;
		for(int i=0;i<topics.size();i++){
			if(topics.get(i).getTitle().equals(TEST_TOPIC_NAME)){
				isInList = true;
				break;
			}
		}
		assertTrue("Topic was not created or cannot be read", isInList);
	}
	
	public void testPostEntry(){
		connect();
		int errorCode = AccessDataBase.getInstance().postEntry(TEST_TOPIC_ID, 42, TEST_ENTRY_TEXT);
		assertEquals("Cannot post Entry", 0, errorCode);
		List<Entry> entries = AccessDataBase.getInstance().getEntryList(TEST_CAT_ID, TEST_TOPIC_ID);
		boolean foundEntry = false;
		for(Entry entry : entries){
			if(entry.getEntrytext().equals(TEST_ENTRY_TEXT)){
				foundEntry = true;
				break;
			}
		}
		assertTrue("Entry was not created or cannot be read",foundEntry);
	}
	
	private void connect(){
		AccessDataBase instance = AccessDataBase.getInstance();
		if(!instance.isConnected()){
			instance.connect();
		}
	}
<<<<<<< HEAD

=======
	
	public void testGetAvatarFilenames(){
		List<String> avatars = AccessDataBase.getInstance().getAvatarFilenames();
		List<String> ret_avatars = new ArrayList<String>();
		Cursor cursor = AccessDataBase.getInstance().query("SELECT profilepic FROM users");
		while (cursor.moveToNext()) {
			ret_avatars.add(cursor.getString(0));
		}		
		assertEquals(avatars,ret_avatars);
		
	}
	
	
>>>>>>> master
}
