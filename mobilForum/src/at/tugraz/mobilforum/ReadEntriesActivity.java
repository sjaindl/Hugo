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

public class ReadEntriesActivity extends Activity {

	private ListView lv;
	List<Entry> entries;
	private int topicid = 1;

	ReadEntriesBaseAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if(!AccessDataBase.hasInstance()){
			AccessDataBase.setInstance(new AccessDataBase(this));
			AccessDataBase.isDefined = false;
		}
		super.onCreate(savedInstanceState);
		this.topicid = getIntent().getIntExtra("topicid", 1);
		setContentView(R.layout.activity_read_entry);
        lv = (ListView) findViewById(R.id.entryListView);
        AccessDataBase db = AccessDataBase.getInstance();
        /* TODO: gettopicid getcategory
         * 
         */
        
        ActionBar actionBar = getActionBar();
        //actionBar.setCustomView(view);
        
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
		getMenuInflater().inflate(R.menu.read_forum, menu);

		return true;
	}

	public int getTopicid() {
		return topicid;
	}

	public void setTopicid(int topicid) {
		this.topicid = topicid;
	}

}