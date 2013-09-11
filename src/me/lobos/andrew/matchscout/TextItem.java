package me.lobos.andrew.matchscout;

import android.view.View;
import android.widget.TextView;

public class TextItem implements ScoutableItem {

	String title;
	
	public TextItem(String title)
	{
		this.title = title;
	}
	
	
	@Override
	public int getLayoutResourceId() {
		return R.layout.activity_text_field;
	}

	@Override
	public void bind(View row) {
		((TextView)row.findViewById(R.id.itemTitle)).setText(title);
	}


	@Override
	public String getValue() {
		return "";
	}


	@Override
	public String getTitle() {
		return title;
	}
	
	public String getType()
	{
		return "TextItem";
	}

}
