package at.tugraz.mobilforum;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
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
		Bundle b = getIntent().getExtras();
		/** TODO: get topic id from topic activity */
		//this.topicid = b.getInt("topicId");
		this.topicid = 1; 
		setContentView(R.layout.activity_read_forum);
        lv = (ListView) findViewById(R.id.entryListView);
        AccessDataBase db = AccessDataBase.getInstance();
        /* TODO: gettopicid getcategory
         * 
         */
        
        entries = new ArrayList<Entry>();
        entries = db.getEntryList(this.topicid);
        
        adapter = new ReadEntriesBaseAdapter(this,entries);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
					
				 	
				// TODO Auto-generated method stub
				
			}
        	 
        	
				
			});
 

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.read_forum, menu);
		
		return true;
	}

}
