package at.tugraz.mobilforum;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ImageView;

public class ReadEntriesBaseAdapter extends BaseAdapter {
	private List<Entry> entries;
	private static LayoutInflater inflater=null;
	private Activity activity;
	private List<Boolean> isExpanded;

	public ReadEntriesBaseAdapter(Activity a, List<Entry> entries) {
		this.activity = a;
        this.entries = entries;  
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.isExpanded = new ArrayList<Boolean>();
        for(int index=0;index<getCount();index++){
        	isExpanded.add(false);
        }
    }


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

	        Log.d("getView","*************************************" + Integer.toString(position));
	        if(convertView==null)
	            vi = inflater.inflate(R.layout.list_entry, null);

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
	        ImageView img = (ImageView)vi.findViewById(R.id.entry_image);
	        Log.d("TAG","---->IMAGE PATH: " + entry.getUploadedImageURI());
	        if(
	        	(entry.getUploadedImageURI() != null)
	        	){
	        	  Options opt = new Options();
			    	opt.inScaled = true;
			    	opt.inSampleSize = 4;
		        Bitmap mImageBitmap = BitmapFactory.decodeFile(entry.getUploadedImageURI(), opt);
		        if(mImageBitmap != null){
		        img.setImageBitmap(mImageBitmap);
		        img.bringToFront();
		    	img.setMaxHeight(500);
		    	img.setMaxWidth(500);
		    	img.setPadding(5, 5, 5, 5);
		    	img.setVisibility(View.VISIBLE);
		        }
		        else{
		        	img.setMaxHeight(0);
		        	img.setMaxWidth(0);     
		        	img.setVisibility(View.GONE);
		        }
	        }
	        else{
	        	img.setMaxHeight(0);
	        	img.setMaxWidth(0);     
	        	img.setVisibility(View.GONE);
	        }
	        
	        
	        if(entry.getUsername().isEmpty()){
	        	Log.d("TAG","USERNAME EMPTY-> userid: " + entry.getUserid());
	        	nametv.setText(AccessDataBase.getInstance().getUserName(entry.getUserid()));
	        }
	        nametv.setText(entry.getUsername());
	        
	        int image_id = AccessDataBase.getInstance().getAvatarId(entry.userid);
	        avatariv.setImageResource(image_id);
	        
	        datetv.setText(entry.getDate());
	        timetv.setText(entry.getTime());
	        //timetv.setText(entry.getTime());

	       // imageLoader.DisplayImage(song.get(CustomizedListView.KEY_THUMB_URL), thumb_image);
	        return vi;
	    }

}