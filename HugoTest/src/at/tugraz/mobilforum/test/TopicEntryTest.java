package at.tugraz.mobilforum.test;

import org.junit.Test;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import at.tugraz.mobilforum.R;
import at.tugraz.mobilforum.TopicPostActivity;
import com.jayway.android.robotium.solo.Solo;

public class TopicEntryTest extends ActivityInstrumentationTestCase2<TopicPostActivity> {

	private Solo solo;
	
	public TopicEntryTest() {
		super(TopicPostActivity.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	
	@Test
	public void testTextALength()
	{
		final EditText newPostText = (EditText)solo.getView(R.id.txt_topicTitle);
		View postButton = solo.getView(R.id.btn_newTopic);
		int textLength = 64;

		solo.waitForView(newPostText);
		solo.waitForView(postButton);
		
		solo.clearEditText(newPostText);
		solo.waitForView(newPostText);
		
		assertFalse("Button should not be enabled (no text)", postButton.isEnabled());
		
		StringBuilder textBuilder = new StringBuilder();
		for(int i = 0; i < textLength-5; i++)
		{
				textBuilder.append('a');
		}
		solo.enterText(newPostText, textBuilder.toString());
		assertTrue("Busson should be enbabled", postButton.isEnabled());
		
		textBuilder = new StringBuilder();
		for(int i = 0; i < textLength; i++)
		{
				textBuilder.append('a');
		}
		solo.enterText(newPostText, textBuilder.toString());
		assertFalse("Busson should not be enbabled", postButton.isEnabled());
	}
	
	@Test
	public void testTextInput()
	{
		EditText newPostText = (EditText)solo.getView(R.id.txt_topicTitle);
		String testEntryString = "Entry created";
		
		solo.waitForView(newPostText);
		solo.enterText(newPostText, testEntryString);
		solo.waitForView(newPostText);
		solo.clickOnButton(0);
		
		assertTrue(solo.searchText(testEntryString));
	}
	
}
