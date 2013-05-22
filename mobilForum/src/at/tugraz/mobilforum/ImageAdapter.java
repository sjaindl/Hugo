package at.tugraz.mobilforum;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{

	private Context context;
	private int resource;
	private Integer[] imageRessources;
	
	public ImageAdapter(Context context, int resourceId, Integer[] imageRessources) {
		//super(context, resourceId);
		
		// TODO Auto-generated constructor stub
		
		this.context = context;
		this.resource = resourceId;
		this.imageRessources = imageRessources;
		notifyDataSetChanged();
		
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("TestAdapter", "Adapter: getView");
		ViewHolder holder; 
		
		if (convertView == null){
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(resource, parent, false);
			holder = new ViewHolder();
			holder.avatar = (ImageView) convertView.findViewById(R.id.activity_register_grid_imageview);
			convertView.setTag(holder);
		} else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.avatar.setImageResource(imageRessources[position]);		
		
		return convertView;
	}
	
	public static class ViewHolder {
		private ImageView avatar;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
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
