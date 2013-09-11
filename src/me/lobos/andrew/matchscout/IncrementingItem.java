package me.lobos.andrew.matchscout;


import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class IncrementingItem implements ScoutableItem {
	View thisView;
	String title;
	int value;
	
	public IncrementingItem(String title)
	{
		this.title = title;
	}
	
	public void bind(View row)
	{
		this.thisView = row;
		
		((TextView)row.findViewById(R.id.itemTitle)).setText(title);
		((TextView)row.findViewById(R.id.itemValue)).setText("0");
		
		((Button)row.findViewById(R.id.buttonPlus)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				value++;
				((TextView)thisView.findViewById(R.id.itemValue)).setText(String.valueOf(value));
			}
		});
		
		((Button)row.findViewById(R.id.buttonMinus)).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				value--;
				if ( value < 0 )
					value = 0;
				((TextView)thisView.findViewById(R.id.itemValue)).setText(String.valueOf(value));
			}
		});
	}
	
	public int getLayoutResourceId()
	{
		return R.layout.activity_incrementing_item;
	}

	@Override
	public String getValue() 
	{
		return String.valueOf(value);
	}

	@Override
	public String getTitle() {
		return title;
	}
	
	public String getType()
	{
		return "IncrementingItem";
	}
}
