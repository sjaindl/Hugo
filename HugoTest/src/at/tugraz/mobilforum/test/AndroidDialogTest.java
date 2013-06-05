package at.tugraz.mobilforum.test;

import org.junit.Test;

import at.tugraz.mobilforum.AccessDataBase;
import at.tugraz.mobilforum.AndroidDialog;
import com.jayway.android.robotium.solo.Solo;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import at.tugraz.mobilforum.R;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestCase;
import android.test.ActivityInstrumentationTestCase2;

public class AndroidDialogTest extends ActivityInstrumentationTestCase2<AndroidDialog> {

	private Solo solo;
	
	public AndroidDialogTest(Class<AndroidDialog> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@Test
	public void testLoginCorrect()
	{
		EditText newUserInput = (EditText)solo.getView(R.id.txt_name);
		EditText newPasswordInput = (EditText)solo.getView(R.id.password);
		String testName = "Hugo";
		String testPassword = "hugo123";
		
		solo.waitForView(newUserInput);
		solo.waitForView(newPasswordInput);
		solo.enterText(newUserInput, testName);
		solo.enterText(newPasswordInput, testPassword);
		assertEquals("Hugo", testName, newUserInput.getText().toString());
		assertEquals("hugo123", testPassword, newPasswordInput.getText().toString());
		
		//Button newButtonSubmit = (Button)solo.getButton(R.id.btn_login);
		String testToastSuccessful = "Loggon Successful";
		solo.clickOnButton(R.id.btn_login);
		
		TextView newToastSubmit = (TextView)solo.getText(R.string.log_success);
		assertEquals("Loggon Successful", testToastSuccessful, newToastSubmit.getText().toString());
	}
	
	@Test
	public void testLoginIncorrect()
	{
		EditText newUserInput = (EditText)solo.getView(R.id.txt_name);
		EditText newPasswordInput = (EditText)solo.getView(R.id.password);
		String testName = "Hugo";
		String testPassword = "hugo123";
		
		solo.waitForView(newUserInput);
		solo.waitForView(newPasswordInput);
		solo.enterText(newUserInput, testName);
		solo.enterText(newPasswordInput, testPassword);
		assertEquals("Robert", testName, newUserInput.getText().toString());
		assertEquals("robert123", testPassword, newPasswordInput.getText().toString());
		
		solo.clickOnButton(R.id.btn_login);
		
		TextView newToastSubmit = (TextView)solo.getText(R.string.log_incorrect);
		assertEquals("Incorrect Login", newToastSubmit.getText().toString());
	}
	
	@Test
	public void testCancel()
	{
		solo.clickOnButton(R.id.btn_cancel);
		
		TextView newToastSubmit = (TextView)solo.getText(R.string.test_cancel);
		assertEquals("Cancel", newToastSubmit.getText().toString());
	}
}
