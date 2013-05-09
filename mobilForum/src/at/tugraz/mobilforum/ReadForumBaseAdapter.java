package at.tugraz.mobilforum;

import java.util.List;
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
	        TextView entrytv = (TextView)vi.findViewById(R.id.entryTextView); 
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
	        datetv.setText(entry.getDate().toString());
	        //timetv.setText(entry.getTime());
	        
	       // imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
	        return vi;
	    }

}
