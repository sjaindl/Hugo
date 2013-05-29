package at.tugraz.mobilforum;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class RegisterAvatarActivity extends Activity {
	
	public static final String TAG = "RegisterAvatarActivity";
	private ImageAdapter adapter;
	private String username;
	private String password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.username = "";
		this.password = "";
		if(savedInstanceState != null){
			this.username = this.getIntent().getExtras().getString("username");
			this.password = this.getIntent().getExtras().getString("password");	
		}
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
				db.registerUser(inst.username, inst.password, image_name);
				/* TODO: start category view */
			}
		});
		
	}
}