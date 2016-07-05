package com.example.entraineurinformatique;

import java.io.Serializable;

import metier.Parties;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultatActivity extends Activity{
	TextView texteVousAvezEu, textePoints;
	Button btnNewPartie, btnQuitter;
	Parties p;
	MediaPlayer mPlayer, click;
	
	//appele lorsque l'activité est créée
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				//le code se met ici
				setContentView(R.layout.resultat);
				mPlayer = MediaPlayer.create(this, R.raw.bruit_accueil);
				click = MediaPlayer.create(this, R.raw.click);
				mPlayer.setLooping(true);
				mPlayer.setVolume(50, 50);
		        mPlayer.start();
				
				p = new Parties();
				//int points = 0;
				//points = getIntent().getIntExtra("POINTS", points);
				p = (Parties) getIntent().getSerializableExtra("partie");
				
				
					texteVousAvezEu = (TextView)findViewById(R.id.texteVousAvezEu);
					textePoints = (TextView)findViewById(R.id.textePoints);
					btnNewPartie = (Button)findViewById(R.id.btnNewPartie);
					btnQuitter = (Button)findViewById(R.id.btnQuitter);
					
					textePoints.setText(""+p.getPoints()+"/20");
					textePoints.setTextColor(Color.RED);
					
					btnNewPartie.setText("rejouer le niveau "+p.getNiveau());
					
					//on clique sur le bouton Nouvelle Partie
					btnNewPartie.setOnClickListener( new OnClickListener() {
			             public void onClick( View view ) { 
			            	 click.start();
			            	 Intent i = new Intent(ResultatActivity.this, JeuActivity.class);
			            	 i.putExtra("partie", (Serializable) p);
			            	 startActivity(i);
			            	 overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			            	 finish();
			            }
			        });
					
					btnQuitter.setOnClickListener( new OnClickListener() {
			             public void onClick( View view ) { 
			            	 setResult(4);
			            	 finish();
			            }
			        });
				
			}
				
			//lorsque l'activité a termine son cycle de vie
			//on fait les nettoyage et les vidange (de la memoire utilisé)
			@Override
			protected void onDestroy() {
				//le code se met ici avant destroy()
				mPlayer.stop();
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
					mPlayer.stop();
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
