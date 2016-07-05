package com.example.entraineurinformatique;

import java.io.Serializable;

import metier.Parties;
import ManagerDAO.PartieManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChangeNivActivity extends Activity{
	
	Parties p;
	TextView textePoints, passernivsup;
	Button continuer, sauvegarder;
	MediaPlayer mPlayer, appl, click;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//le code se met ici
		setContentView(R.layout.niveausup);
		mPlayer = MediaPlayer.create(this, R.raw.bruit_accueil);
		appl = MediaPlayer.create(this, R.raw.applause);
		click = MediaPlayer.create(this, R.raw.click);
		mPlayer.setLooping(true);
		mPlayer.setVolume(50, 50);
        appl.start();
        mPlayer.start();
		
		p = new Parties();
		//int points = 0;
		//points = getIntent().getIntExtra("POINTS", points);
		p = (Parties) getIntent().getSerializableExtra("partie");
		p.setNiveau((p.getNiveau()+1));
		
		textePoints = (TextView)findViewById(R.id.textePoints);
		passernivsup = (TextView)findViewById(R.id.passernivsup);
		
		continuer = (Button)findViewById(R.id.continuer);
		sauvegarder = (Button)findViewById(R.id.sauvegarder);
		
		textePoints.setText(""+p.getPoints()+"/20");
		textePoints.setTextColor(Color.RED);
		passernivsup.setText("VOUS PASSEZ AU NIVEAU : "+p.getNiveau()+"\n "+p.getNomJoueur());
		
		if(p.getNiveau()<3){
			continuer.setOnClickListener( new OnClickListener() {
	            public void onClick( View view ) {
	            	click.start();
	            	Intent i = new Intent(ChangeNivActivity.this, JeuActivity.class);
	    	 		i.putExtra("partie", (Serializable) p);
	    	 		startActivity(i);
	    	 		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	    	 		finish();
	           }
	       });
			
			sauvegarder.setOnClickListener( new OnClickListener() {
	            public void onClick( View view ) {
	            	click.start();
	            	
	            	long id = enregistrerPartie(p);
	            	if(id!=-1){
	            		afficheToast("Partie Sauvegardée avec succès");
	            	}
	           	 
	           }
	       });
		}//fin if
		else{
			continuer.setOnClickListener( new OnClickListener() {
	            public void onClick( View view ) { 
	            	achatAppli();
	           }
	       });
			
			sauvegarder.setOnClickListener( new OnClickListener() {
	            public void onClick( View view ) {
	            	
	            	
	           	 
	           }
	       });
		}
		
		
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
			mPlayer.start();
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

		protected void achatAppli() {
			// TODO Auto-generated method stub
	    	AlertDialog.Builder boite2;
	        boite2 = new AlertDialog.Builder(this);
	        boite2.setTitle("Acheter Via MTN Mobile Money");
	        boite2.setCancelable(true);
	        boite2.setIcon(R.drawable.ic_launcher);
	            boite2.setMessage("Acheter le jeu pour continuer: \nVotre compte MTN Mobile Money sera débité de 250 FCFA");
	        
	        boite2.setNegativeButton("Plus tard", new DialogInterface.OnClickListener() {
		           
	            public void onClick(DialogInterface dialog, int which) {
	            	dialog.dismiss();
	            	finish();
	            }
	            }
	        );
	        
	     boite2.setNeutralButton("SMS au Propriétaire", new DialogInterface.OnClickListener() {
	           
	            public void onClick(DialogInterface dialog, int which) {
	            	
	            }
	            }
	     );
	     
	     boite2.setPositiveButton("Acheter", new DialogInterface.OnClickListener() {
	           
	            public void onClick(DialogInterface dialog, int which) {
	            	
	            }
	            }
	        );
	     
	     boite2.show();
		}
		
		public long enregistrerPartie(Parties p){
			PartieManager pm = new PartieManager(this);
			long id = -1;
          	 pm.open();
          	 id = pm.addPartie(p);
          	 pm.close();
          	 return id;
		}
		
		//permet d'afficher les toast
			public void afficheToast(String text)
			  {
			     Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
			  }
			
}
