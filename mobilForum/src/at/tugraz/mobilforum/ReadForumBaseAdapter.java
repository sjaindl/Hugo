package at.tugraz.mobilforum;

import java.util.List;
<<<<<<< HEAD:mobilForum/src/at/tugraz/mobilforum/ReadForumBaseAdapter.java
=======

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
>>>>>>> master:mobilForum/src/at/tugraz/mobilforum/ReadEntriesBaseAdapter.java
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

public class ReadForumBaseAdapter extends BaseAdapter {
	private List<Entry> entries;
	
	public ReadForumBaseAdapter(List<Entry> entries) {
        this.entries = entries;  
    }
<<<<<<< HEAD:mobilForum/src/at/tugraz/mobilforum/ReadForumBaseAdapter.java

	     
=======
	
	
>>>>>>> master:mobilForum/src/at/tugraz/mobilforum/ReadEntriesBaseAdapter.java
	@Override
	public int getCount() {
		 return this.entries.size();		 
	}
	
	public void toggle(int position){
		if (this.isExpanded.get(position) == false){
		for(int index=0;index<this.getCount();index++){
			Log.d("TAG", "**************");
			this.isExpanded.set(index, false);
		}
		this.isExpanded.set(position, true);
		}
		else {
			for(int index=0;index<this.getCount();index++){
				Log.d("TAG", "-------------");
				this.isExpanded.set(index, false);
			}
		}
		
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
	
	public void setExpandedStatus(boolean status, int position){
		if(status){
			for(int index=0;index<this.isExpanded.size();index++){
				this.isExpanded.set(index, false);
			}
		}
		this.isExpanded.set(position, status);
		this.notifyDataSetChanged();
	}
	
	public boolean isChildExpanded(int position){
		return this.isExpanded.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	        View vi=convertView;
<<<<<<< HEAD:mobilForum/src/at/tugraz/mobilforum/ReadForumBaseAdapter.java
=======
	        
	        Log.d("getView","*************************************" + Integer.toString(position));
	        if(convertView==null)
	            vi = inflater.inflate(R.layout.list_entry, null);
	        
>>>>>>> master:mobilForum/src/at/tugraz/mobilforum/ReadEntriesBaseAdapter.java
	        TextView entrytv = (TextView)vi.findViewById(R.id.entryTextView); 
		       
	        if(this.isExpanded.get(position)){
	        	Log.d("getView","expanded. !");
	        	entrytv.setMaxLines(Integer.MAX_VALUE);
	        }
	        else{
	        	Log.d("getView","NOT expanded. !");
	        	
	        	entrytv.setMaxLines(2);
	        }
	        TextView nametv = (TextView)vi.findViewById(R.id.name);
	        ImageView avatariv = (ImageView)vi.findViewById(R.id.avatarView);
	        TextView datetv = (TextView)vi.findViewById(R.id.dateTextView);
	        TextView timetv = (TextView)vi.findViewById(R.id.time);
	        
	        Entry entry = this.getItem(position);
	        entrytv.setText(entry.getEntrytext());
	        nametv.setText(entry.getUsername());
	        /* TODO: get image name from database */
	        avatariv.setImageResource(R.drawable.avatar2);

	        //Bitmap bmap = BitmapFactory.decodeFile()
	        datetv.setText(entry.getDate());
	        timetv.setText(entry.getTime());
	        //timetv.setText(entry.getTime());
	        
	       // imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
	        return vi;
	    }

}
