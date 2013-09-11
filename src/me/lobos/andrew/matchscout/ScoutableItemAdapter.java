package me.lobos.andrew.matchscout;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class ScoutableItemAdapter extends ArrayAdapter<ScoutableItem> {
	Context c;
	ArrayList<ScoutableItem> items;
	public ScoutableItemAdapter(Context c, ArrayList<ScoutableItem> items)
	{
		super(c, android.R.layout.simple_list_item_1, items);
		this.c = c;
		this.items = items;
	}
	
    public View getView(int position, View convertView, ViewGroup parent) {
    	View row = convertView;
    	ScoutableItem thisItem = items.get(position);
    	if ( row == null )
    	{
    		LayoutInflater inflater = ((Activity)c).getLayoutInflater();
    		row = inflater.inflate(thisItem.getLayoutResourceId(), parent, false);
    		row.setTag(thisItem);
    		thisItem.bind(row);
    	}
    	return row;
    }
}
