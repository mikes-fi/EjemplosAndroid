package com.ejemplo.ejemplo1;

import android.app.Activity;
import android.view.View.OnKeyListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.util.Log;
import android.widget.TextView;

public class KeyTest extends Activity implements OnKeyListener{
	StringBuilder builder=new StringBuilder();
	TextView textView;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		textView =new TextView(this);
		textView.setText("Pulsa teclas y observa el resultado en la pantalla");
		textView.setOnKeyListener(this);
		textView.setFocusableInTouchMode(true);
		textView.requestFocus();
		setContentView(textView);
	}
	
	@Override
	public boolean onKey(View view,int keyCode,KeyEvent event){
		//limpio el string
		builder.setLength(0);
		switch(event.getAction()){
			case KeyEvent.ACTION_DOWN:
				builder.append("down, ");
			break;
			case KeyEvent.ACTION_UP:
				builder.append("up, ");
			break;
		}
		
		//muestra el KeyCode en pantalla
		builder.append(event.getKeyCode());
		builder.append(", ");
		//se muestra el caracter unicode
		builder.append((char)event.getUnicodeChar());
		String text=builder.toString();
		Log.d("KeyTest",text);
		//mostramos en pantalla
		textView.setText(text);
		
		//para que se pueda cerrar la activity con la tecla Back
		if(event.getKeyCode()==KeyEvent.KEYCODE_BACK)
			return false;
		else
			return true;
	}
}
