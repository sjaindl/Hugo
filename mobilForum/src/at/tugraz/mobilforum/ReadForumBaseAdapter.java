package at.tugraz.mobilforum;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

public class ReadForumBaseAdapter extends BaseAdapter {
	private List<Entry> entries;
	private static LayoutInflater inflater=null;
	private Activity activity;
	private List<Boolean> isExpanded;

	public ReadForumBaseAdapter(Activity a, List<Entry> entries) {
		this.activity = a;
        this.entries = entries;  
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.isExpanded = new ArrayList<Boolean>();
        for(int index=0;index<getCount();index++){
        	isExpanded.add(false);
        }
    }

	public void setExpanded(int position){
		for(int index=0;index<this.getCount();index++){
			this.isExpanded.set(index, false);
		}
		this.isExpanded.set(position, true);
	}
	
	@Override
	public int getCount() {
		 return this.entries.size();		 
	}
	
	@Override
	public Entry getItem(int position) {
		return entries.get(position);
	}

	@Override
	public long getItemId(int position) {
		return entries.get(position).hashCode();
	}
	
	public void setData(List<Entry> entries){
		this.entries = entries;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	        View vi=convertView;
	        if(convertView==null && this.isExpanded.get(position))
	            vi = inflater.inflate(R.layout.list_entry, null);
	        if(!this.isExpanded.get(position)){
	        	vi = inflater.inflate(R.layout.list_entry_expanded, null);
	        }
	        TextView entrytv = (TextView)vi.findViewById(R.id.entryTextView); 
	        TextView nametv = (TextView)vi.findViewById(R.id.name);
	        ImageView avatariv = (ImageView)vi.findViewById(R.id.avatarView);
	        TextView datetv = (TextView)vi.findViewById(R.id.dateTextView);
	        //TextView timetv = (TextView)vi.findViewById(R.id.time);
	        
	        Entry entry = this.getItem(position);
	        entrytv.setText(entry.getEntrytext());
	        nametv.setText(entry.getUsername());
	        /* TODO: get image name from database */
	        avatariv.setImageResource(R.drawable.avatar2);

	        //Bitmap bmap = BitmapFactory.decodeFile()
	        datetv.setText(entry.getDate().toString());
	        //timetv.setText(entry.getTime());
	        
	       // imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
	        return vi;
	    }

}
