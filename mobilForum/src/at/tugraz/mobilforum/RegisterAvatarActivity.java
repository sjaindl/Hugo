package at.tugraz.mobilforum;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class RegisterAvatarActivity extends Activity {
	
	public static final String TAG = "RegisterAvatarActivity";
	private ImageAdapter adapter;
	private String username;
	private String password;
	private int topicid = 0;
	private int categoryid = 0;
	public Toast toast;
	
	@Override
	@SuppressLint("ShowToast")
	protected void onCreate(Bundle savedInstanceState) {
		this.username = "";
		this.password = "";
		toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
		
		  this.topicid = getIntent().getIntExtra("topicid", 1);
		  this.categoryid = getIntent().getIntExtra("categoryid", 1);
		  this.username = this.getIntent().getExtras().getString("username");
	      this.password = this.getIntent().getExtras().getString("password");	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avatar_register);
		
		final GridView avatar_grid = (GridView) findViewById(R.id.avatar_grid);
		
		
		adapter = new ImageAdapter(this,
				R.layout.activity_avatar_register);
		
		avatar_grid.setAdapter(adapter);
		
		final RegisterAvatarActivity inst = this;

		avatar_grid.setOnItemClickListener(new OnItemClickListener() {

			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(!AccessDataBase.hasInstance()){
					AccessDataBase.setInstance(new AccessDataBase(inst));
					AccessDataBase.isDefined = false;
				}
				AccessDataBase db = AccessDataBase.getInstance();
				avatar_grid.getAdapter().getView(arg2, arg1, null);
				String image_name = (String)avatar_grid.getAdapter().getItem(arg2);
				Log.d("TAG","REGISTER USER: username: " + username + " password: " + password + " Image: " + image_name);
				db.registerUser(username, password, image_name);
				Intent i = new Intent();
				SharedPreferences sp=getSharedPreferences("Login", 0);
		    	SharedPreferences.Editor Ed=sp.edit();
		    	int userid = db.getUserId(username);
		    	Log.d("TAG", "USER ID: " + userid);
		    	Ed.putString("userId", Integer.toString(userid));                 
		    	Ed.commit(); 
		    	Toast.makeText(inst, "Erfolgreich registriert!", Toast.LENGTH_SHORT).show();
				i.setClass(getApplicationContext(), SwipeActivity.class);
				startActivity(i);
				finish();
			}
		});
		
	}
}
