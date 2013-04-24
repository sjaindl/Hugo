package at.tugraz.mobilforum.test;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.LinearLayout;
import at.tugraz.mobilforum.MainActivity;

public class MainActTest extends ActivityInstrumentationTestCase2<MainActivity> {

	public MainActTest(Class<MainActivity> activityClass) {
		super(activityClass);
	}

	@UiThreadTest
	public void testCategoryView(){
		LinearLayout categoryList = (LinearLayout)getActivity().findViewById(at.tugraz.mobilforum.R.id.categoryList);
		assertNotNull(categoryList);
	}
	
}
