package at.tugraz.mobilforum;

import java.io.File;
import java.net.URISyntaxException;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Camera;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TopicPostActivity extends Activity {
	public Toast toast;
	    
	private static final String imagefilepath = Environment.getExternalStorageDirectory().getAbsolutePath();
	private static final int REQUEST_CAMERA = 99;
	private static final int REQUEST_IMAGE_FILE = 1;
	private Uri uri = null;
	private int topicid = 1;
	private int userid = 1;
	
	private Entry entry = null;
	private String entry_title = null;
	@Override
	@SuppressLint("ShowToast")
	protected void onCreate(Bundle savedInstanceState) {
		this.topicid = getIntent().getIntExtra("topicid", 1);
		AccessDataBase.setInstance(new AccessDataBase(this.getApplicationContext()));
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_topic_post);
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		this.entry = new Entry();
		
		
		
		final TopicPostActivity pa = this;
		
		final Button postButton = (Button)this.findViewById(R.id.btn_newTopic);

       final EditText inputText = (EditText)findViewById(R.id.txt_topicTitle);
		
		toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, inputText.getTop() + 50);
		
		final TopicPostActivity ps = this;
		
		postButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				if(inputText.getText().length()>=1){
					 entry_title = inputText.getText().toString();
					 Log.d("DEBUG", "EntryTitle: "+entry_title);
					 AccessDataBase.getInstance().postTopic(entry_title, topicid, userid);
					Toast.makeText(ps, "Entry created", Toast.LENGTH_SHORT).show();
					ps.finish();
					
					Intent intent1 = new Intent(TopicPostActivity.this, SwipeActivity.class);
			        startActivity(intent1);
					
				}
				
			}
			
		});
		
		inputText.addTextChangedListener(new TextWatcher(){
	        public void afterTextChanged(Editable s) {
	        	int length = inputText.getText().length();
	        	
	        	if(length > 0 && length <= 60)
	        		postButton.setEnabled(true);
	        	else
	        		postButton.setEnabled(false);
	        	
	        	if(length > 60)
	        	{
	        		StringBuilder sb = new StringBuilder();
	        		sb.append(length);
	        		sb.append("/60");
	        		toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, inputText.getTop() + 50);
	        		
	        		if(length > 60)
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
		return true;
	}
	
	
	
	
}
