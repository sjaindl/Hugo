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
import android.widget.Toast;

public class TopicPostActivity extends Activity {

	public Toast toast;
	@Override
	@SuppressLint("ShowToast")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topic_post);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
	}
	
	void autoUpdateMenuItemState(Menu menu)
	{
		final MenuItem postButton = menu.findItem(R.id.btn_new_topic);
		final EditText inputText = (EditText)findViewById(R.id.newTopicText);
		final EditText inputTitel =(EditText)findViewById(R.id.newTopicTitle);
		toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, inputText.getTop() + 50);
		
		inputText.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        }

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {}

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
	        	validateText(postButton, inputText,inputTitel);
	        	}
	        });
		inputTitel.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				validateText(postButton, inputText, inputTitel);
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	void validateText(MenuItem postButton, EditText inputText, EditText inputTitel)
	{
		
		int textlength = inputText.getText().length();
		int titellength = inputTitel.getText().length();
		Boolean textready = false;
		Boolean titelready = false;
    	
    	
    	textready = textlength > 0 && textlength <= 1024;
    	if(textlength > 1014)
    	{
    		StringBuilder sb = new StringBuilder();
    		sb.append(textlength);
    		sb.append("/1024");
    		toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, inputText.getTop() + 50);
    		
    		if(textlength > 1024)
    			sb.append(" (TOO LONG!)");
    		
    		toast.setText(sb.toString());
    		toast.show();
    	}
    	
    	titelready = titellength > 0;
    	
    	postButton.setEnabled(titelready && textready);
    	
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.topic_post, menu);
		autoUpdateMenuItemState(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent parentActivityIntent;
		switch (item.getItemId()) {
	        case android.R.id.home:
	            // This is called when the Home (Up) button is pressed
	            // in the Action Bar.
	            parentActivityIntent = new Intent(this, ReadEntriesActivity.class);
	            parentActivityIntent.addFlags(
	                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
	                    Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(parentActivityIntent);
	            finish();
	            return true;
	            
	        case R.id.btn_new_topic:
				EditText inputText = (EditText)findViewById(R.id.newTopicText);
				EditText inputTitle = (EditText)findViewById(R.id.newTopicTitle);
				AccessDataBase.getInstance().postTopic(inputTitle.getText().toString(), 0, 0);
				
				parentActivityIntent = new Intent(this, ReadEntriesActivity.class);
	            parentActivityIntent.addFlags(
	                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
	                    Intent.FLAG_ACTIVITY_NEW_TASK);
	            startActivity(parentActivityIntent);
	            finish();
				
				return true;
		}
		
		return super.onOptionsItemSelected(item);
	}
}
