package at.tugraz.mobilforum.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jayway.android.robotium.solo.Solo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import at.tugraz.mobilforum.PostEntryActivity;
import at.tugraz.mobilforum.R;

public class PostEntryTest extends ActivityInstrumentationTestCase2<PostEntryActivity> {


	private Solo solo;
	public PostEntryTest() {
	super(PostEntryActivity.class);
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
		Log.d("blah", "blahblabla");
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
			solo.waitForView(((PostEntryActivity)getActivity()).toast.getView());
			assertTrue("Character count should be shown", getActivity().toast.getView().isShown());
		}

		solo.waitForView(newPostText);
		assertFalse("Button should not be enabled (text too long)", postButton.isEnabled());
	}
	
}
