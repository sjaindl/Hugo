package at.tugraz.mobilforum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class TopicListAdapter extends ArrayAdapter<String> {
    private ArrayList<String> items;
    private Context context;

    public TopicListAdapter(Context context, int textViewResourceId,
                            ArrayList<String> objects) {
        super(context, textViewResourceId, objects);

        this.context = context;
        this.items = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.topic_in_topic_list, null);
        }

        TextView rootTextView = (TextView) view.findViewById(R.id.topic_in_topic_list);

        rootTextView.setText(items.get(position));
        return view;
    }

}
