package at.tugraz.mobilforum;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{

	private Context context;
	private int resource;
	private Integer[] imageRessources;
	private List<ImageView> images;
	
	 private Integer[] mThumbIds = {
			 			R.drawable.avatar2, R.drawable.bob, R.drawable.calimero,
						R.drawable.daisy, R.drawable.elefant, R.drawable.ente,
						R.drawable.maus, R.drawable.mickey, R.drawable.minnie
	    };
	
	public ImageAdapter(Context context, int resourceId) {
		//super(context, resourceId);
		
		// TODO Auto-generated constructor stub
		
		this.context = context;
		this.resource = resourceId;
		//this.imageRessources = imageRessources;
		notifyDataSetChanged();
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(this.context);
            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
	}
	
	public static class ViewHolder {
		private ImageView avatar;
	}

	@Override
	public int getCount() {
		 return mThumbIds.length;
	}

	@Override
	public Object getItem(int position) {
		
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
