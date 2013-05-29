package at.tugraz.mobilforum;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class RegisterAvatarActivity extends Activity {
	
	public static final String TAG = "RegisterAvatarActivity";
	private ImageAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_avatar_register);
		
		final GridView avatar_grid = (GridView) findViewById(R.id.avatar_grid);
		
		final Integer[] avatarPictures = {
		R.drawable.avatar2, R.drawable.bob, R.drawable.calimero,
				R.drawable.daisy, R.drawable.elefant, R.drawable.ente,
				R.drawable.maus, R.drawable.mickey, R.drawable.minnie };
		
		adapter = new ImageAdapter(this,
				R.layout.activity_avatar_register);
		
		avatar_grid.setAdapter(adapter);
	

		avatar_grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				avatar_grid.getAdapter().getView(arg2, arg1, null);
				
			}
		});
		/*
		final Button button = (Button) findViewById(R.id.activity_avatar_button_next);
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				adapter.notifyDataSetChanged();
			}
		});*/
	}
}
