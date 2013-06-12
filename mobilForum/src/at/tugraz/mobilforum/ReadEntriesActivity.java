package at.tugraz.mobilforum;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.ClipData.Item;
import android.view.Menu;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_read_entry, menu);
//		View button_post_entry = findViewById(R.id.item1);
//
//		
//		
//		button_post_entry.setOnClickListener(new OnClickListener(){
//
//			@Override
//			public void onClick(View v) {
//				Intent i = new Intent();
//				i.putExtra("topicid", topicid);
//				i.setClass(getApplicationContext(), PostEntryActivity.class);
//				startActivity(i);
//			}
//			
//		});
		

		return true;
	}

	public int getTopicid() {
		return topicid;
	}

	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}

}