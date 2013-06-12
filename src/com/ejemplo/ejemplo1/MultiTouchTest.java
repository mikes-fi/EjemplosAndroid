package com.ejemplo.ejemplo1;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.widget.TextView;

public class MultiTouchTest extends Activity implements OnTouchListener {
	StringBuilder builder=new StringBuilder();
	TextView textView;
	
	//arreglos para contener las coordenadas de los dedos
	float[] x=new float[10];
	float[] y=new float[10];
	//para saber si el dedo esta tocando la pantalla
	boolean[] tocado=new boolean[10];
	
	//permite saber el estado actual de los dedos
	private void updateTextView() {
		builder.setLength(0);
		for(int i=0; i<10; i++){
			builder.append(tocado[i]);
			builder.append(", ");
			builder.append(x[i]);
			builder.append(", ");
			builder.append(y[i]);
			builder.append("\n");
		}
		//se muestra el resultado en el textView
		textView.setText(builder.toString());
	}
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		textView=new TextView(this);
		textView.setText("Toca y arrastra ¡(Soporta multiples dedos)!");
		textView.setOnTouchListener(this);
		setContentView(textView);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event){
		int action=event.getAction() & MotionEvent.ACTION_MASK;
		//operacion logica AND
		@SuppressWarnings("deprecation")
		int pointerIndex=(event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >>
		MotionEvent.ACTION_POINTER_ID_SHIFT;
		
		//se obtiene el pointerId
		int pointerId=event.getPointerId(pointerIndex);
		
		switch(action){
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN: //para varios dedos
				tocado[pointerId]=true;
				x[pointerId]=(int)event.getX(pointerIndex);
				y[pointerId]=(int)event.getY(pointerIndex);
			break;
			
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP: //cuando un dedo es retirado y hay mas dedos tocando la pantalla
			case MotionEvent.ACTION_CANCEL:
				tocado[pointerId]=false;
				x[pointerId]=(int)event.getX(pointerIndex);
				y[pointerId]=(int)event.getY(pointerIndex);
			break;
				
			case MotionEvent.ACTION_MOVE:
				//para saber cuantos dedos tocan la pantalla
				int pointerCount=event.getPointerCount();
				for(int i=0; i<pointerCount; i++){
					pointerIndex=i;
					pointerId=event.getPointerId(pointerIndex);
					x[pointerId]=(int)event.getX(pointerIndex);
					y[pointerId]=(int)event.getY(pointerIndex);
				}
			break;
		}
		//se actualiza el textView
		updateTextView();
		return true;
	}
}
