package at.tugraz.mobilforum;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

public class ReadTopicsBaseAdapter extends BaseAdapter {
	private List<Topic> topics;
	private static LayoutInflater inflater=null;
	private Activity activity;

	public ReadTopicsBaseAdapter(Activity a, List<Topic> entries) {
		this.activity = a;
        this.topics = entries;  
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
	
	
	@Override
	public int getCount() {
		 return this.topics.size();		 
	}
	
	
	@Override
	public Topic getItem(int position) {
		return topics.get(position);
	}

	@Override
	public long getItemId(int position) {
		return topics.get(position).hashCode();
	}
	
	public void setData(List<Topic> topics){
		this.topics = topics;
	}
		
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	        View vi=convertView;
	        TextView tv;
	        if(convertView==null)
	        	vi = new TextView(activity);
	        
	        tv = (TextView)vi;
	        Topic t = this.getItem(position);
	        tv.setText(t.getTitle() + "\n" + " erstellt von " + t.getUsername() + " am " + t.getDate() + ", " + t.getTime());
	        tv.setMaxLines(2);
			return tv;
	    }

}
