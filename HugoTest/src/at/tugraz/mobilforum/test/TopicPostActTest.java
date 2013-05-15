package at.tugraz.mobilforum.test;

import static org.junit.Assert.*;

import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import at.tugraz.mobilforum.PostActivity;
import at.tugraz.mobilforum.R;
import at.tugraz.mobilforum.TopicPostActivity;

import com.jayway.android.robotium.solo.Solo;

public class TopicPostActTest extends ActivityInstrumentationTestCase2<TopicPostActivity>{
 
	Solo solo;
	public TopicPostActTest() {
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
	public void testTextInput()
	{
		EditText newPostText = (EditText)solo.getView(R.id.newTopicText);
		String halloHugo = "Hallo Hugo!";
		
		solo.waitForView(newPostText);
		solo.enterText(newPostText, halloHugo);
		solo.waitForView(newPostText);
		assertEquals("Hugo sagt nicht 'Hallo'.", halloHugo, newPostText.getText().toString());
	}
	@Test
	public void testTitelInput()
	{
		EditText newPostText = (EditText)solo.getView(R.id.newTopicTitle);
		String halloHugo = "Hallo Hugo!";
		
		solo.waitForView(newPostText);
		solo.enterText(newPostText, halloHugo);
		solo.waitForView(newPostText);
		assertEquals("Hugo sagt nicht 'Hallo'.", halloHugo, newPostText.getText().toString());
	}
	
	@Test
	public void testTextLength()
	{
		final EditText newTopicText = (EditText)solo.getView(R.id.newTopicText);
		final EditText newTopicTitel = (EditText)solo.getView(R.id.newTopicTitle);
		View postButton = solo.getView(R.id.btn_new_topic);
		int textLength = 1024;

		solo.waitForView(newTopicText);
		solo.waitForView(newTopicTitel);
		solo.waitForView(postButton);
		
		solo.clearEditText(newTopicText);
		solo.clearEditText(newTopicTitel);
		solo.waitForView(newTopicText);
		
		assertFalse("Button should not be enabled (no text and titel)", postButton.isEnabled());
		
		solo.enterText(newTopicTitel, "a");
		solo.waitForView(newTopicTitel);
		assertFalse("Button should not be enabled (no text)", postButton.isEnabled());
		
		
		
		StringBuilder textBuilder = new StringBuilder();
		for(int i = 0; i < textLength - 10; i++)
		{
			if((i % 10) == 9)
				textBuilder.append('\n');
			else
				textBuilder.append('a');
		}
		solo.enterText(newTopicText, textBuilder.toString());

		solo.waitForView(newTopicText);

		assertTrue("Button should be enabled", postButton.isEnabled());
		
		solo.clearEditText(newTopicTitel);
		assertFalse("Button should not be enabled (no titel)", postButton.isEnabled());
		
		for(int i = 0; i < 15; i++)
		{
			solo.enterText(newTopicText, "b");
			solo.waitForView(((TopicPostActivity)getActivity()).toast.getView());
			assertTrue("Character count should be shown", getActivity().toast.getView().isShown());
		}

		solo.waitForView(newTopicText);
		assertFalse("Button should not be enabled (text too long)", postButton.isEnabled());
	}
}
