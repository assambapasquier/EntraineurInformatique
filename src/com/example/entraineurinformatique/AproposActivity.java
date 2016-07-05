package com.example.entraineurinformatique;

import android.app.Activity;
import android.os.Bundle;

public class AproposActivity extends Activity{
	
	//appele lorsque l'activité est créée
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			//le code se met ici
			setContentView(R.layout.apropos);
		}
			
		//lorsque l'activité a termine son cycle de vie
		//on fait les nettoyage et les vidange (de la memoire utilisé)
		@Override
		protected void onDestroy() {
			//le code se met ici avant destroy()
			super.onDestroy();
		}
		
		//lorsque l'activité démarre
		@Override
		protected void onStart() {
			super.onStart();
			//le code se met ici apres start()
		}
		
		//lorsque l'activité passe en arrière Plan
			@Override
			protected void onStop() {
				//le code se met ici avant onStop()
				super.onStop();
			}
			
		//lorsque l'activité revient de la veille
			@Override
			protected void onRestart() {
				super.onRestart();
				//le code se met ici
			}
			
		//lorsque l'activité va en pause
			@Override
			protected void onPause(){
				//le code ici
				super.onPause();
			}
			
		//lorsque l'activité revient de la pause
			@Override
			protected void onResume(){
				super.onResume();
				//le code ici
			}
			
		//sauvegarder les donnée importantes
			@Override
			protected void onSaveInstanceState(Bundle savedInstanceState){
				//le code ici
				super.onSaveInstanceState(savedInstanceState);
			}
			
		//apres onCreate on recharge les données
			@Override
			protected void onRestoreInstanceState(Bundle savedInstanceState){
				super.onRestoreInstanceState(savedInstanceState);
				//le code ici
			}
	
}
