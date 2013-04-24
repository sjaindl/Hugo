package at.tugraz.mobilforum;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.util.Log;

import android.view.Menu;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
        TextView tv = (TextView) findViewById(R.id.logintitle);

        tv.setText("Mobile Forum");
        AccessDataBase db = new AccessDataBase();
        ResultSet rs = db.ReturnQuery("select count(*) as num from test where a='1'");
        while (rs.next()) {
            countRows = rs.getInt("num");
        }
        System.out.println(countRows);
        
        
        
        SharedPreferences prefs = this.getSharedPreferences(
        "at.tugraz.mobileforum", Context.MODE_PRIVATE);
        
        prefs.edit().putLong("validlogin", 1);
        
        
        
		//Log.e(TAG, "test");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
