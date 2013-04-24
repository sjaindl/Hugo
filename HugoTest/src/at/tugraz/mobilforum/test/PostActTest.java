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

}
