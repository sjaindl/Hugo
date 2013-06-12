package at.tugraz.mobilforum;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.ClipData.Item;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReadEntriesActivity extends Activity {

	private ListView lv;
	List<Entry> entries;
	private int topicid = 1;
	private int categoryid = 1;



	ReadEntriesBaseAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		if(!AccessDataBase.hasInstance()){
			AccessDataBase.setInstance(new AccessDataBase(this));
		}
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_read_entry);
		
		this.topicid = getIntent().getIntExtra("topicid", 1);
        lv = (ListView) findViewById(R.id.entryListView);
        AccessDataBase db = AccessDataBase.getInstance();
        
        int cid = db.getCategoryFromTopic(topicid);
        
        this.categoryid = cid;
        String category = db.getCategoryFromId(cid);
        String topicName = db.getTopicFromId(topicid);

        TextView topicsText = (TextView)findViewById(R.id.Topic);
        TextView categoryText = (TextView)findViewById(R.id.Category);
        
        topicsText.setText(topicName);
        categoryText.setText(category);

        entries = new ArrayList<Entry>();
        entries = db.getEntryList(this.topicid);
        
        adapter = new ReadEntriesBaseAdapter(this,entries);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if(adapter.isChildExpanded(arg2)){
					TextView tv1 = (TextView)arg1.findViewById(R.id.entryTextView);
					tv1.setMaxLines(2);
					adapter.setExpandedStatus(false, arg2);
				}else{
					TextView tv2 = (TextView)arg1.findViewById(R.id.entryTextView);
					tv2.setMaxLines(Integer.MAX_VALUE);
					adapter.setExpandedStatus(true, arg2);
				}
			}
			});
	}

	@Override
	public boolean onPrepareOptionsMenu (Menu menu){
		SharedPreferences sp = this.getSharedPreferences("Login", 0);
		menu.clear();
		
		String user = sp.getString("userId", ""); 
		final ReadEntriesActivity rea = this;
		
		
		Log.d("TAG","MENU CHECK USERID: " + user);
		
			menu.add("Reply");
			menu.add("Logout");
			menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener(){

				@Override
				public boolean onMenuItemClick(MenuItem arg0) {
					// TODO reply
					
					
					return false;
				}
				
				
			});
			
			menu.getItem(1).setOnMenuItemClickListener(new OnMenuItemClickListener(){

				@Override
				public boolean onMenuItemClick(MenuItem arg0) {
					// TODO logout
					SharedPreferences sp = rea.getSharedPreferences("Login", 0);
					SharedPreferences.Editor Ed=sp.edit();
		  	  	    Ed.clear();
			  	  	Ed.commit(); 
			  	  	Intent i = new Intent();
					i.setClass(getApplicationContext(), SwipeActivity.class);
					
					startActivity(i);
					return false;
				}
				
				
			});
			
		
		menu.add("login");
		menu.add("register");	

		
		menu.getItem(2).setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				//login
				Intent i = new Intent();
				i.putExtra("topicid",rea.topicid);
				i.putExtra("categorycid",rea.categoryid);
				i.setClass(getApplicationContext(), AndroidDialog.class);
				startActivity(i);
				return false;
			}
			
			
		});
		
		menu.getItem(3).setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				//register
				Intent i = new Intent();
				i.putExtra("topicid",rea.topicid);
				i.putExtra("categorycid",rea.categoryid);
				i.setClass(getApplicationContext(), RegisterActivity.class);
				startActivity(i);
				return false;
				}
			
			
		});
		
		int userid = 0;
				
		if(user.equals("")){
			userid = 0;
		}
		else{
			userid = Integer.parseInt(user);
		}
		
		if(userid != 0)
		{
			// logged in
			menu.getItem(0).setVisible(true);
			menu.getItem(1).setVisible(true);
			menu.getItem(2).setVisible(false);
			menu.getItem(3).setVisible(false);	
		}
		else{
			menu.getItem(0).setVisible(false);
			menu.getItem(1).setVisible(false);
			menu.getItem(2).setVisible(true);
			menu.getItem(3).setVisible(true);	
			
		}
		
		
		
		


		return true;
		
	} 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		getMenuInflater().inflate(R.menu.activity_read_entry, menu);
		
				return true;
	}


	public int getTopicid() {
		return topicid;
	}

	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}

}