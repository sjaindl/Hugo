package at.tugraz.mobilforum.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import at.tugraz.mobilforum.AccessDataBase;
import at.tugraz.mobilforum.Entry;
import at.tugraz.mobilforum.Topic;
import android.database.Cursor;
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


	public void testUserRegistration(){
		AccessDataBase instance = AccessDataBase.getInstance();
		int userID = instance.registerUser(TEST_USER_NAME, TEST_USER_PASSWORD, TEST_USER_PIC);
		assertTrue("Create user with existing user name",userID<=0);
		userID = instance.registerUser(TEST_USER_NAME+"@"+System.currentTimeMillis(), TEST_USER_PASSWORD, TEST_USER_PIC);
		assertTrue("Cannot register new user",userID>0);
	}

	public void testUserApproval(){
		AccessDataBase instance = AccessDataBase.getInstance();
		int userID = instance.approveUser(TEST_USER_NAME, "falsePW");
		assertTrue("False password works for user approval",userID<=0);
		userID = instance.approveUser(TEST_USER_NAME, TEST_USER_PASSWORD);
		assertTrue("Cannot approve existing user",userID>0);
	}

	public void testPostTopic(){
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

	@Override
	protected void setUp() throws Exception {
		AccessDataBase.setInstance(new AccessDataBase(getContext()));
		super.setUp();
	}


	public void testPostEntry(){
		int errorCode = AccessDataBase.getInstance().postEntry(TEST_TOPIC_ID, 42, TEST_ENTRY_TEXT);
		assertEquals("Cannot post Entry", 0, errorCode);
		List<Entry> entries = AccessDataBase.getInstance().getEntryList(TEST_TOPIC_ID);
		boolean foundEntry = false;
		for(Entry entry : entries){
			if(entry.getEntrytext().equals(TEST_ENTRY_TEXT)){
				foundEntry = true;
				break;
			}
		}
		assertTrue("Entry was not created or cannot be read",foundEntry);
	}

	public void testCategories(){
		Map<Integer, String> categories = AccessDataBase.getInstance().getCategoryList();
		Cursor cursor = AccessDataBase.getInstance().query("SELECT COUNT(*) FROM categories");
		cursor.moveToNext();
		int catCount = cursor.getInt(0);
		assertEquals("Cannot get categories", catCount, categories.size());
		assertTrue("No categories!",catCount!=0);
	}

	public void testGetAvatarFilenames(){
		List<String> avatars = AccessDataBase.getInstance().getAvatarFilenames();
		List<String> ret_avatars = new ArrayList<String>();
		Cursor cursor = AccessDataBase.getInstance().query("SELECT profilepic FROM users");
		while (cursor.moveToNext()) {
			ret_avatars.add(cursor.getString(0));
		}		
		assertEquals(avatars,ret_avatars);

	}


}