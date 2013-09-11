package me.lobos.andrew.matchscout;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class OptionListItem implements ScoutableItem {

	String title;
	String[] options;
	Spinner selector;
	public OptionListItem(String title, String[] options)
	{
		this.title = title;
		this.options = options;
	}
	
	@Override
	public int getLayoutResourceId() {
		return R.layout.activity_options_item;
	}

	@Override
	public void bind(View row) {
		((TextView)row.findViewById(R.id.itemTitle)).setText(title);
		
		String[] options = new String[this.options.length+1];
		options[0] = "Select One";
		for ( int i = 0; i < this.options.length; i++ ) options[i+1] = this.options[i];
		
		ArrayAdapter<String> a = new ArrayAdapter<String>(row.getContext(), android.R.layout.simple_list_item_1, options);
		((Spinner)row.findViewById(R.id.optionSpinner)).setAdapter(a);
		selector = (Spinner)row.findViewById(R.id.optionSpinner);
	}

	@Override
	public String getValue() {
		return selector.getSelectedItem().toString();
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	public String getType()
	{
		return "OptionListItem";
	}

}