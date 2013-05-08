package at.tugraz.mobilforum;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PostActivity extends Activity {
	public Toast toast;
	
	@Override
	@SuppressLint("ShowToast")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
	}
	
	void autoUpdateMenuItemState(Menu menu)
	{
		final EditText inputText = (EditText)findViewById(R.id.newPostText);
		final MenuItem postButton = menu.findItem(R.id.btn_new_post);
		toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, inputText.getTop() + 50);
		
		inputText.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	int length = inputText.getText().length();
	        	
	        	if(length > 0 && length <= 1024)
	        		postButton.setEnabled(true);
	        	else
	        		postButton.setEnabled(false);
	        	
	        	if(length > 1014)
	        	{
	        		StringBuilder sb = new StringBuilder();
	        		sb.append(length);
	        		sb.append("/1024");
	        		toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, inputText.getTop() + 50);
	        		
	        		if(length > 1024)
	        			sb.append(" (TOO LONG!)");
	        		
	        		toast.setText(sb.toString());
	        		toast.show();
	        	}
	        }

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {}
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post, menu);
		autoUpdateMenuItemState(menu);
		return true;
	}

}
