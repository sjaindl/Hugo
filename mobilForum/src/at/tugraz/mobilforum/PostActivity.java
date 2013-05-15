package at.tugraz.mobilforum;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PostActivity extends Activity {
	public Toast toast;
	
	int topicId;
	
	@Override
	@SuppressLint("ShowToast")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		getTopicId();
		showTopicName();
	}
	
	void getTopicId()
	{
		Bundle b = getIntent().getExtras();
		if(null == b)
		{
			topicId = 0;
			return;
		}
		topicId = b.getInt("topicId");
	}
	
	void showTopicName()
	{
		//TODO get topic name from database
		
		TextView topicNameView = (TextView)findViewById(R.id.newPostTopicTitle);
		String topicTitle = "Topic title here";
		topicNameView.setText("Reply to: " + topicTitle);
	}
	
	void autoUpdateMenuItemState(Menu menu)
	{
		final MenuItem postButton = menu.findItem(R.id.btn_new_post);
		final EditText inputText = (EditText)findViewById(R.id.newPostText);
		
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
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent parentActivityIntent;
		
		switch (item.getItemId()) {
			case android.R.id.home:
				parentActivityIntent = new Intent(this, ReadForumActivity.class);
		        parentActivityIntent.addFlags(
		                Intent.FLAG_ACTIVITY_CLEAR_TOP |
		                Intent.FLAG_ACTIVITY_NEW_TASK);
		        startActivity(parentActivityIntent);
		        finish();
				return true;
				
			case R.id.btn_new_post:
				EditText inputText = (EditText)findViewById(R.id.newPostText);
				AccessDataBase.getInstance().postEntry(0, 0, inputText.getText().toString());
				
	            parentActivityIntent = new Intent(this, ReadForumActivity.class);
	            parentActivityIntent.addFlags(
	                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
	                    Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(parentActivityIntent);
	            finish();
				return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.post, menu);
		autoUpdateMenuItemState(menu);
		return true;
	}

}
