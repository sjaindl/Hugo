package at.tugraz.mobilforum;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class TopicListAdapter extends ArrayAdapter<Map<Integer, String>> {
	private ArrayList<Map<Integer, String>> items;
	private Context context;

	public TopicListAdapter(Context context, int textViewResourceId,
			ArrayList<Map<Integer, String>> objects) {
		super(context, textViewResourceId, objects);
		
		this.context = context;
		this.items = objects;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		View view = new View(context);
		return view;
	}

}
