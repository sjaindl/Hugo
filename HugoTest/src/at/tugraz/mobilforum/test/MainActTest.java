package at.tugraz.mobilforum.test;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.LinearLayout;
import at.tugraz.mobilforum.MainActivity;
import at.tugraz.mobilforum.R;

public class MainActTest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	
	public MainActTest(Class<MainActivity> activityClass) {
		super(activityClass);
	}

	@Override
	public void setUp() throws Exception{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@UiThreadTest
	public void testCategoryView(){
		//LinearLayout categoryList = (LinearLayout)getActivity().findViewById(R.id.categoryList);
		//assertNotNull(categoryList);
		LinearLayout ll = (LinearLayout)solo.getView(R.id.categoryList);
		assertNotNull(ll);
	}
	
}
