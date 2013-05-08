package at.tugraz.mobilforum.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import at.tugraz.mobilforum.PostActivity;
import at.tugraz.mobilforum.R;

public class PostActTest extends ActivityInstrumentationTestCase2<PostActivity> {

	private Solo solo;
	
	public PostActTest() {
		super(PostActivity.class);
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
		EditText newPostText = (EditText)solo.getView(R.id.newPostText);
		String halloHugo = "Hallo Hugo!";
		
		solo.waitForView(newPostText);
		solo.enterText(newPostText, halloHugo);
		solo.waitForView(newPostText);
		assertEquals("Hugo sagt nicht 'Hallo'.", halloHugo, newPostText.getText().toString());
	}
	
	@Test
	public void testTextLength()
	{
		final EditText newPostText = (EditText)solo.getView(R.id.newPostText);
		View postButton = solo.getView(R.id.btn_new_post);
		int textLength = 1024;

		solo.waitForView(newPostText);
		solo.waitForView(postButton);
		
		solo.clearEditText(newPostText);
		solo.waitForView(newPostText);
		
		assertFalse("Button should not be enabled (no text)", postButton.isEnabled());
		
		StringBuilder textBuilder = new StringBuilder();
		for(int i = 0; i < textLength - 10; i++)
		{
			if((i % 10) == 9)
				textBuilder.append('\n');
			else
				textBuilder.append('a');
		}
		solo.enterText(newPostText, textBuilder.toString());
		
		solo.waitForView(newPostText);
		assertTrue("Button should be enabled", postButton.isEnabled());
		
		for(int i = 0; i < 15; i++)
		{
			solo.enterText(newPostText, "b");
			solo.waitForView(((PostActivity)getActivity()).toast.getView());
			assertTrue("Character count should be shown", getActivity().toast.getView().isShown());
		}

		solo.waitForView(newPostText);
		assertFalse("Button should not be enabled (text too long)", postButton.isEnabled());
	}
}
