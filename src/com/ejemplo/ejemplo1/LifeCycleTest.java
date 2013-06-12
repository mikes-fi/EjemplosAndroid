package com.ejemplo.ejemplo1;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

public class LifeCycleTest extends Activity{
	StringBuilder builder=new StringBuilder(); //para construir un string
	TextView textView;//para mostrar en pantalla
	
	private void log(String text){
		Log.d("LifeCycleText",text);
		builder.append(text);
		builder.append('\n');
		textView.setText(builder.toString());
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		textView=new TextView(this);
		textView.setText(builder.toString());
		setContentView(textView);
		log("create");
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		log("resumed");
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		log("paused");
		if(isFinishing()){
			log("finishing");
		}
	}
}
