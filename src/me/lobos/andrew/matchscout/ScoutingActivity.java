package me.lobos.andrew.matchscout;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;

public class ScoutingActivity extends ListActivity {
	int match,team;
	ArrayList<ScoutableItem> scoutableItems = new ArrayList<ScoutableItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		team = getIntent().getExtras().getInt("team");
		match = getIntent().getExtras().getInt("match");
		
		getActionBar().setTitle("Team "+team+" in Match "+match);
		
		String data = "";
		BufferedReader d = new BufferedReader(new InputStreamReader(ScoutingActivity.class.getResourceAsStream("config.json")));
		try {
			while ( d.ready() ) data += d.readLine()+"\n";
		} catch (Exception e){}
		
		try {
			JSONArray obj = new JSONArray(data);
			
			for ( int i = 0; i < obj.length()-1; i++ )
			{
				System.out.println(i);
				JSONObject thisObject = obj.getJSONObject(i);
				String title = thisObject.getString("title");
				if ( thisObject.getString("type").equals("IncrementingItem") ) 
				{
					scoutableItems.add(new IncrementingItem(title));
				}
				else if ( thisObject.getString("type").equals("OptionListItem") ) 
				{
					JSONArray options = thisObject.getJSONArray("options");
					String[] optionsArray = new String[options.length()];
					for ( int a = 0; a < optionsArray.length; a++ ) optionsArray[a] = options.getString(a);
					scoutableItems.add(new OptionListItem(title,optionsArray));
				}	
			}
			setListAdapter(new ScoutableItemAdapter(this,scoutableItems));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String toJSON()
	{
		String output = "[";
		
		for ( int i = 0; i < scoutableItems.size(); i++ )
		{
			ScoutableItem thisItem = scoutableItems.get(i);
			output += "{\"title\":\""+thisItem.getTitle()+"\", \"value\":\""+thisItem.getValue()+"\"}";
			if ( scoutableItems.size()-1 != i )
				output += ",\n";
		}
		output += "]";
		return output;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.scouting, menu);
		
		((MenuItem)menu.findItem(R.id.commitButton)).setOnMenuItemClickListener(new OnMenuItemClickListener(){

			@Override
			public boolean onMenuItemClick(MenuItem m) {
				System.out.println(toJSON());
				Intent i = new Intent(ScoutingActivity.this, MatchScout.class);
				startActivity(i);
				return true;
			}
			
		});
		return true;
	}
	
}
