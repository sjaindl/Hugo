package at.tugraz.mobilforum;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ReadForumActivity extends Activity {

	private ListView lv;
	List<Entry> entries;
	private int topicid = 0;
	ReadForumBaseAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
<<<<<<< HEAD:mobilForum/src/at/tugraz/mobilforum/ReadForumActivity.java
		setContentView(R.layout.activity_read_forum);
=======
		this.topicid = getIntent().getIntExtra("topicid", 1);
		setContentView(R.layout.activity_read_entry);
>>>>>>> master:mobilForum/src/at/tugraz/mobilforum/ReadEntriesActivity.java
        lv = (ListView) findViewById(R.id.entryListView);
        AccessDataBase db = AccessDataBase.getInstance();
        /* TODO: gettopicid getcategory
         * 
         */
        
        ActionBar actionBar = getActionBar();
        //actionBar.setCustomView(view);
        
        entries = new ArrayList<Entry>();
        entries = db.getEntries(this.topicid);
        adapter = new ReadForumBaseAdapter(entries);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {
        	 
        	@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
<<<<<<< HEAD:mobilForum/src/at/tugraz/mobilforum/ReadForumActivity.java
				// TODO Auto-generated method stub
			}
				
=======
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
>>>>>>> master:mobilForum/src/at/tugraz/mobilforum/ReadEntriesActivity.java
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_forum, menu);
		return true;
	}

}
