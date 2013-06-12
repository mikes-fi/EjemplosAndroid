package com.ejemplo.ejemplo1;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.widget.TextView;

public class SingleTouchTest extends Activity implements OnTouchListener {
	StringBuilder builder=new StringBuilder(); //para construir un string
	TextView textView;//para mostrar en pantalla
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		textView=new TextView(this);
		textView.setText("Toca y arrastra ¡(un dedo solamente)!");
		textView.setOnTouchListener(this);
		setContentView(textView);//los eventos se reciben en un view
	}
	
	@Override
	public boolean  onTouch(View v, MotionEvent event){
		builder.setLength(0);//limpio el string
		
		//los posibles eventos
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			builder.append("down, ");
			break;
			
			case MotionEvent.ACTION_MOVE:
				builder.append("move, ");
			break;
			
			case MotionEvent.ACTION_CANCEL:
				builder.append("cancel, ");
			break;
			
			case MotionEvent.ACTION_UP:
				builder.append("up, ");
			break;
		}
		
		builder.append(event.getX());
		builder.append(", ");
		builder.append(event.getY());
		//paso el contenido de builder a un string
		String text=builder.toString();
		//muestro el string en el textview y en el Log
		Log.d("TouchText",text);
		textView.setText(text);
		return true;
	}
}
