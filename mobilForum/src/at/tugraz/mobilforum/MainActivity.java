package at.tugraz.mobilforum;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	public static final String TAG = "mforum";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AccessDataBase db = new AccessDataBase(this);
		AccessDataBase.setInstance(db);
		db.getEntryList(1);
		setContentView(R.layout.activity_main);
		Log.e(TAG, "test");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Intent intent1 = new Intent(MainActivity.this, SwipeActivity.class);
        startActivity(intent1);
		return true;
	}

}
