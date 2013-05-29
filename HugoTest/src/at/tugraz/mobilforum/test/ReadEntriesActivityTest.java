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
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import at.tugraz.mobilforum.AccessDataBase;
import at.tugraz.mobilforum.Entry;
import at.tugraz.mobilforum.R;
import at.tugraz.mobilforum.ReadEntriesActivity;
import at.tugraz.mobilforum.ReadEntriesBaseAdapter;


public class ReadEntriesActivityTest extends ActivityInstrumentationTestCase2<ReadEntriesActivity>{


	private Solo solo;
	private int topic_id;
	private int category_id;

	public ReadEntriesActivityTest() {
		super(ReadEntriesActivity.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception
	{
		AccessDataBase.setInstance(new AccessDataBase(this.getActivity().getApplicationContext()));
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
    	this.category_id = AccessDataBase.getInstance().getRandomCategory();
    	// TODO: implement random test
    	//this.topic_id = AccessDataBase.getInstance().getRandomTopicFromCategory(category_id);
    	this.topic_id = 1;
	}

    
	/** Testing Listview by checking its item count */
	@SmallTest
    public void testListViewEntryCounter(){
		this.getActivity().setTopicid(this.topic_id);
    	ListView lv = (ListView)solo.getView(R.id.entryListView);
    	int expectedCount = AccessDataBase.getInstance().getEntryList(topic_id).size();
        int actualCount = lv.getChildCount();
        assertEquals(expectedCount, actualCount);
		//assertEquals(true,true);
    }

	/** Testing listview by getting a random entry and look for the entry in the database */
	@SmallTest
    public void testRandomListViewEntry(){
    	ListView lv = (ListView)solo.getView(R.id.entryListView);
    	this.getActivity().setTopicid(this.topic_id);
    	Entry random_entry = AccessDataBase.getInstance().getRandomEntryFromTopic(topic_id);
    	String entrytext = random_entry.toString();
    	boolean isentryinlist = false;
    	for(int i=0;i<lv.getChildCount();i++){
    	Entry entry = (Entry)lv.getItemAtPosition(i);
    	Log.d("TAG", "entry: " + entry.toString() + "listentry: " + entrytext);
    	if(entry.toString().equals(entrytext)){
    		isentryinlist = true;
    		break;
    		}
    	}
        assertEquals(isentryinlist, true);
    }
    
}