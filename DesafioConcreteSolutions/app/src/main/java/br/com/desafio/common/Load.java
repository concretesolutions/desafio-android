package br.com.desafio.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import br.com.desafio.R;

@SuppressLint("InflateParams")
public class Load {
	private View load;

	public Load(final Activity activity) {
		LayoutInflater inflater = activity.getLayoutInflater(); 
		Window window = activity.getWindow();
		
		load =  inflater.inflate(R.layout.load, null);
		load.setVisibility(View.INVISIBLE); 

		window.addContentView(load, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
	}
	
	public void show() {
		if(load != null)
			load.setVisibility(View.VISIBLE);
	}
	
	public void hide() {
		if(load != null)
			load.setVisibility(View.GONE);
	}
	
	public void setOnClickListener(OnClickListener onClickListener) {
		if(load != null)
			load.setOnClickListener(onClickListener);
	}
}