package at.tugraz.mobilforum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ReadTopicsActivity extends Activity {
	
	private int categoryid;
	private ListView lv;
	private List<Topic> topics;
	private ReadTopicsBaseAdapter readTopicsBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_topics);
		if(!AccessDataBase.hasInstance()){
			AccessDataBase.setInstance(new AccessDataBase(this));
			//AccessDataBase.isDefined = false;
		}
		super.onCreate(savedInstanceState);
		//Bundle b = getIntent().getExtras();
		/** TODO: get topic id from read topic activity */
		//this.topicid = b.getInt("topicId");
		this.categoryid = 1; 
		setContentView(R.layout.activity_read_topics);
        lv = (ListView) findViewById(R.id.entryListView);
        AccessDataBase db = AccessDataBase.getInstance();
        /* TODO: gettopicid getcategory
         * 
         */
        
        
        topics = new ArrayList<Topic>();
        Topic t1 = new Topic(1,"1. Topic", 320459235, "user1");
        Topic t2 = new Topic(2, "2. Topic", 234234523, "user2");
        topics.add(t1);
        topics.add(t2);
        
        readTopicsBaseAdapter = new ReadTopicsBaseAdapter(this,topics);
        
        lv.setAdapter(readTopicsBaseAdapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent i = new Intent();
				
				i.putExtra("topicid", readTopicsBaseAdapter.getItem(arg2).getTopicid());
				i.setClass(getApplicationContext(), ReadEntriesActivity.class);
				startActivity(i);
			}
			});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_topics, menu);
		return true;
	}

}
