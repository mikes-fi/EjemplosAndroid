package com.ejemplo.ejemplo1;

import android.app.Activity;
import android.hardware.SensorEventListener;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.content.Context;
import android.widget.TextView;

public class AccelerometerTest extends Activity implements SensorEventListener{
	StringBuilder builder=new StringBuilder();
	TextView textView;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		textView =new TextView(this);
		setContentView(textView);
		
		//solicitamos el servicio del sensor
		SensorManager manager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
		//comprovamos si hay un sensor disponible
		if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size()==0){
			textView.setText("No hay un acelerometro instalado");
		}
		//si esta disponible el acelerometro, se usa el primero
		else{
			Sensor accelerometer=manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			if(!manager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_GAME)){
				textView.setText("No se ha podido registrar el sensor listener");
			}
		}
	}
	
	//muestra los valores de x,y,z del acelerometro
	@Override
	public void onSensorChanged(SensorEvent event){
		//limpio el string
		builder.setLength(0);
		builder.append("x: ");
		builder.append(event.values[0]);
		builder.append("y: ");
		builder.append(event.values[1]);
		builder.append("z: ");
		builder.append(event.values[2]);
		//se manda el string a la pantalla
		textView.setText(builder.toString());
		
	}
	
	@Override
	public void onAccuracyChanged(Sensor sensor,int accuracy){
		//nada que hacer, solo es para implementar todos los metodos
	}
}
