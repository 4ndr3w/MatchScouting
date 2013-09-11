package me.lobos.andrew.matchscout;


import android.view.View;

public interface ScoutableItem {
	public int getLayoutResourceId();
	public void bind(View v);
	public String getType();
	public String getTitle();
	public String getValue();
}
