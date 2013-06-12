package com.ejemplo.ejemplo1;

import android.app.ListActivity; //para listar las activity
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UnoActivity extends ListActivity {

	String pruebas[]={"LifeCycleTest","SingleTouchTest","MultiTouchTest","KeyTest",
			"AccelerometerTest","AssetsTest","ExternalStrorageTest","SoundPoolTest",
			"MediaPlayerTest","FullScreenTest","RenderViewTest","FontText","SurfaceViewTest"};
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//nombre de la activity,API UI de android,array que quiero mostrar
		setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pruebas));
	}
	
	@Override
	protected void onListItemClick(ListView list,View view,int position,long id){
		super.onListItemClick(list, view, position, id);
		//guardo el elemento que ha sido pulsado
		String nombrePrueba=pruebas[position];
		try{
			//usa un comodin
			Class<?> clazz=Class.forName("com.ejemplo.ejemplo1."+nombrePrueba);
			Intent intent=new Intent(this,clazz);
			startActivity(intent);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
    
}
