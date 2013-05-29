package at.tugraz.mobilforum.test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;
import android.widget.ListView;
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
	
	@Test
	public void testEntry()
	{
		ListView lv = (ListView)solo.getView(R.id.entryListView);
		List<Integer>hashes = new ArrayList<Integer>();
		List<Entry> entries = new ArrayList<Entry>();
		EditText newPostText = (EditText)solo.getView(R.id.newPostText);
		for(int i=0;i<50;i++){
			/*Entry entry = new Entry("hugo" + Integer.toString(i),"avatar2", "hugosig", 
					"Hugos message", new Date(), "00:00", 1);
			hashes.add(entry.hashCode());
			entries.add(entry);*/
			
		}
		ReadForumBaseAdapter adapter = new ReadForumBaseAdapter(entries);
		lv.setAdapter(adapter);
		boolean isvalid = false;
		/*
		for(int i;i<50;i++){
			solo.clickInList(i);
			if(!isvalid){
				assertFalse(true);
			}
		}*/ 
	    //select 'Product 1'
	    solo.clickInList(1);
	    //solo.waitForText(hashes)
	    assertTrue(solo.waitForText("Product 1 selected"));
	    solo.goBack();
	    
	    //select 'Product 6'
	    solo.clickInList(1);
	    assertTrue(solo.waitForText("Product 6 selected"));
	    solo.goBack();
	    
	    //scroll list to line 7
	    solo.scrollListToLine(0,7);
	 
	    //click on 'Product 8'
	    solo.clickInList(1);
	    assertTrue(solo.waitForText("Product 8 selected"));
	    solo.goBack();
	    
	    //scroll list to line 10
	    solo.scrollListToLine(0, 10);
	    
	    //select 'Product 11'
	    solo.clickInList(1);
	    assertTrue(solo.waitForText("Product 11 selected"));
	    solo.goBack();
	    
	    //select 'Product 7'
	    solo.scrollListToLine(0, 6);
	    solo.clickInList(1);
	    assertTrue(solo.waitForText("Product 7 selected"));
	    solo.goBack();
	    
	    //select 'Product 2'
	    solo.scrollListToLine(0, 1);
	    solo.clickInList(1);
	    assertTrue(solo.waitForText("Product 2 selected"));
	    solo.goBack();
		
		//assertEquals("Hugo sagt nicht 'Hallo'.", halloHugo, newPostText.getText().toString());
	}
}
