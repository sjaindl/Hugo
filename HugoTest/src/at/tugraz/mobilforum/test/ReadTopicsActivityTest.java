package at.tugraz.mobilforum.test;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import at.tugraz.mobilforum.AccessDataBase;
import at.tugraz.mobilforum.ReadEntriesActivity;
import at.tugraz.mobilforum.ReadTopicsActivity;

public class ReadTopicsActivityTest extends ActivityInstrumentationTestCase2<ReadTopicsActivity> {

	private Solo solo;
	private int topic_id;
	private int category_id;
	
	public ReadTopicsActivityTest(){
		super(ReadTopicsActivity.class);
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

	
	
	/**
	 * 
	 * TODO: implement Topics test cases
	 * 
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
