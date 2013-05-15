package at.tugraz.mobilforum.test;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;
import android.widget.ListView;
import at.tugraz.mobilforum.AccessDataBase;
import at.tugraz.mobilforum.Entry;
import at.tugraz.mobilforum.R;
import at.tugraz.mobilforum.ReadForumActivity;
import at.tugraz.mobilforum.ReadForumBaseAdapter;


public class ReadForumTest extends ActivityInstrumentationTestCase2<ReadForumActivity>{


	private Solo solo;
	
	public ReadForumTest() {
		super(ReadForumActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
    
	/** Testing Listview by checking its item count */
	@SmallTest
    public void testListViewEntryCounter(){
    	ListView lv = (ListView)solo.getView(R.id.entryListView);
    	int category_id = AccessDataBase.getInstance().getRandomCategory();
    	int topic_id = AccessDataBase.getInstance().getRandomTopicFromCategory(category_id);
    	int expectedCount = AccessDataBase.getInstance().getEntryListCounter(category_id, topic_id);
        int actualCount =lv.getAdapter().getCount();
        assertEquals(expectedCount, actualCount);
		assertEquals(true,true);
    }
	
	/** Testing listview by getting a random entry and look for the entry in the database */
	@SmallTest
    public void testRandomListViewEntry(){
    	ListView lv = (ListView)solo.getView(R.id.entryListView);
    	//getEntryListCounter(int categoryid, int topicid)
    	int category_id = AccessDataBase.getInstance().getRandomCategory();
    	int topic_id = AccessDataBase.getInstance().getRandomTopicFromCategory(category_id);
    	Entry random_entry = AccessDataBase.getInstance().getRandomEntryFromTopic(topic_id);
    	String entrytext = random_entry.toString();
    	boolean isentryinlist = false;
    	for(int i=0;i<lv.getChildCount();i++){
    	Entry entry = (Entry)lv.getItemAtPosition(i);
    	if(entry.toString()==entrytext.toString()){
    		isentryinlist = true;
    		break;
    		}
    	}
        assertEquals(isentryinlist, true);
    }
    
}
