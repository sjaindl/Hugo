package at.tugraz.mobilforum;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ReadTopicsActivity extends Activity {

	private int categoryid;
	private int topicid;
	private ListView lv;
	private List<Topic> topics;
	private ReadTopicsBaseAdapter readTopicsBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read_topics);
		if(!AccessDataBase.hasInstance()){
			AccessDataBase.setInstance(new AccessDataBase(this));
		}
		super.onCreate(savedInstanceState);
		this.topicid = getIntent().getExtras().getInt("topicId");
		this.categoryid = getIntent().getExtras().getInt("categoryId");
		setContentView(R.layout.activity_read_topics);
        lv = (ListView) findViewById(R.id.topicListView);
        AccessDataBase db = AccessDataBase.getInstance();
        topics = new ArrayList<Topic>();
        db.getTopicList(this.categoryid);
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