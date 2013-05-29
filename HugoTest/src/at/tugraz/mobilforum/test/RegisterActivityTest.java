package at.tugraz.mobilforum.test;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import at.tugraz.mobilforum.MainActivity;
import at.tugraz.mobilforum.R;
import at.tugraz.mobilforum.RegisterActivity;

public class RegisterActivityTest extends ActivityInstrumentationTestCase2<RegisterActivity> {

	private Solo solo;
	
	public RegisterActivityTest() {
		super(RegisterActivity.class);
	}

	@Override
	public void setUp() throws Exception{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testRegistration(){
		RelativeLayout relativeLayout = (RelativeLayout)solo.getView(R.id.activity_register_layout);
		assertNotNull(relativeLayout);
		
		EditText userName = (EditText)solo.getView(R.id.activity_register_username);
		assertNotNull(userName);
		
		EditText userPassword = (EditText)solo.getView(R.id.activity_register_edit_password);
		assertNotNull(userPassword);
		
		EditText userPasswordConfirm = (EditText)solo.getView(R.id.activity_register_edit_password_confirm);
		assertNotNull(userPasswordConfirm);
		
		Button nextButton = (Button)solo.getView(R.id.activity_register_button_next);
		
		solo.clearEditText(userName);
		solo.enterText(userName, "TestUser");
		solo.clearEditText(userPassword);
		solo.enterText(userPassword, "test");
		solo.clearEditText(userPasswordConfirm);
		solo.enterText(userPasswordConfirm, "test");
		assertTrue(nextButton.isEnabled());
		solo.clickOnButton(0);
	}
	
	public void testRegistrationAlertUserExists(){
		RelativeLayout relativeLayout = (RelativeLayout)solo.getView(R.id.activity_register_layout);
		assertNotNull(relativeLayout);
		
		EditText userName = (EditText)solo.getView(R.id.activity_register_username);
		assertNotNull(userName);
		
		EditText userPassword = (EditText)solo.getView(R.id.activity_register_edit_password);
		assertNotNull(userPassword);
		
		EditText userPasswordConfirm = (EditText)solo.getView(R.id.activity_register_edit_password_confirm);
		assertNotNull(userPasswordConfirm);
		
		Button nextButton = (Button)solo.getView(R.id.activity_register_button_next);

		solo.clearEditText(userName);
		solo.enterText(userName, "Test");
		solo.clearEditText(userPassword);
		solo.enterText(userPassword, "test");
		solo.clearEditText(userPasswordConfirm);
		solo.enterText(userPasswordConfirm, "test");
		assertTrue(nextButton.isEnabled());
		solo.clickOnButton(0);
		assertTrue(solo.searchText(solo.getString(R.string.alert_reg_username)));
		
	}
	
	public void testRegistrationAlertWrongInput(){
		RelativeLayout relativeLayout = (RelativeLayout)solo.getView(R.id.activity_register_layout);
		assertNotNull(relativeLayout);
		
		EditText userName = (EditText)solo.getView(R.id.activity_register_username);
		assertNotNull(userName);
		
		EditText userPassword = (EditText)solo.getView(R.id.activity_register_edit_password);
		assertNotNull(userPassword);
		
		EditText userPasswordConfirm = (EditText)solo.getView(R.id.activity_register_edit_password_confirm);
		assertNotNull(userPasswordConfirm);
		
		Button nextButton = (Button)solo.getView(R.id.activity_register_button_next);

		solo.clearEditText(userName);
		solo.enterText(userName, "Test");
		solo.clearEditText(userPassword);
		solo.enterText(userPassword, "test");
		solo.clearEditText(userPasswordConfirm);
		solo.enterText(userPasswordConfirm, "");
		assertTrue(nextButton.isEnabled());
		solo.clickOnButton(0);
		assertTrue(solo.searchText(solo.getString(R.string.title_reg_input_error)));
		
		solo.clickOnButton(0);
		
		solo.clearEditText(userName);
		solo.enterText(userName, "Test");
		solo.clearEditText(userPassword);
		solo.enterText(userPassword, "");
		solo.clearEditText(userPasswordConfirm);
		solo.enterText(userPasswordConfirm, "test");
		assertTrue(nextButton.isEnabled());
		solo.clickOnButton(0);
		assertTrue(solo.searchText(solo.getString(R.string.title_reg_input_error)));
		
		solo.clickOnButton(0);
		
		solo.clearEditText(userName);
		solo.enterText(userName, "");
		solo.clearEditText(userPassword);
		solo.enterText(userPassword, "test");
		solo.clearEditText(userPasswordConfirm);
		solo.enterText(userPasswordConfirm, "test");
		assertTrue(nextButton.isEnabled());
		solo.clickOnButton(0);
		assertTrue(solo.searchText(solo.getString(R.string.title_reg_input_error)));
		
		solo.clickOnButton(0);
		
	}
}
