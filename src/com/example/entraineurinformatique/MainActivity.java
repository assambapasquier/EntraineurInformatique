package com.example.entraineurinformatique;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import metier.Niveaux;
import metier.Parties;


import ManagerDAO.MusicManager;
import ManagerDAO.NiveauManager;
import ManagerDAO.PartieManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @author pasquierase
 *ceci est la grande fenêtre d'accueil
 *pour exporter en apk: clic gauche sur l'appli->export......
 */

public class MainActivity extends Activity implements OnClickListener{
	InputStream ins;
	Parties p;
	int clas=0;
	/*le cycle de vie d'une activité est donné par les étatpes onCreate, onDestroy
	onResume, OnPause... alors la bonne façon est de resortir toutes ces méthodes
	avant de commencer le codage*/
	
	private GridviewAdapter mAdapter;
    private ArrayList<String> listMenus;
    private ArrayList<Integer> listFlag;
    private GridView gridView;
    MediaPlayer mPlayer, click;
    
    
	//appele lorsque l'activité est créée
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accueil);
		mPlayer = MediaPlayer.create(this, R.raw.bruit_accueil);
		click = MediaPlayer.create(this, R.raw.click);
		mPlayer.setLooping(true);
		mPlayer.setVolume(50, 50);
        mPlayer.start();
		//Intent svc = new Intent(this, MusicManager.class); startService(svc);
		//----------------------------on charge les nouvelles questions ou thèmes -------
			//ajouterQuestions();
			//ajouterThemes();
		//---------------------------------------------------------------------
		
		prepareList();
		 
        // prepare l'arrayList et la passe à l'Adapter
        mAdapter = new GridviewAdapter(this,listMenus, listFlag);
 
        // Set custom adapter to gridview
        gridView = (GridView) findViewById(R.id.gridViewAccueil);
        gridView.setAdapter(mAdapter);
        
 
        // Implement On Item click listener
        gridView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                    long arg3) {
            	click.start();
                switch(position){
                	case 0:{
                		nouvellePartie();
                	}
                	break; 
                	case 1:{
                		chargerParties();
                	}
                	break;
                	case 2:{
                		aPropos();
                	}
                	break;
                	case 3:{
                		commentJouer();
                	}
                	break;
                	case 4:{
						onClick(arg1);
                	}
                	break;
                }
            }
           });
	}
	
	public void prepareList()
    {
          listMenus = new ArrayList<String>();
 
          listMenus.add("Nouvelle Partie");
          listMenus.add("Charger Une Partie");
          listMenus.add("A Propos");
          listMenus.add("Comment Jouer");
          listMenus.add("Quitter le Jeu");
          
 
          listFlag = new ArrayList<Integer>();
          listFlag.add(R.drawable.npartie);
          listFlag.add(R.drawable.load);
          listFlag.add(R.drawable.apropos);
          listFlag.add(R.drawable.aide);
          listFlag.add(R.drawable.sortir);
    }

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
		mPlayer.start();
	}
	
	//lorsque l'activité passe en arrière Plan
		@Override
		protected void onStop() {
			//le code se met ici avant onStop()
			mPlayer.stop();
			super.onStop();
		}
		
	//lorsque l'activité revient de la veille
		@Override
		protected void onRestart() {
			super.onRestart();
			mPlayer.start();
			//le code se met ici
		}
		
	//lorsque l'activité va en pause
		@Override
		protected void onPause(){
			//le code ici
			mPlayer.pause();
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
			mPlayer.start();
		}

		//permet d'afficher les toast
		public void afficheToast(String text)
		  {
		     Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
		  }
		
		//on désigne les actions a accomplir lorsqu'on clic sur un bouton
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					
					AlertDialog.Builder boite2;
	                boite2 = new AlertDialog.Builder(this);
	                boite2.setTitle("Confirmation de sortie");
	                boite2.setIcon(R.drawable.ic_launcher);
	                    boite2.setMessage("Voulez-Vous Quitter ce Jeu?");
	               
	            
	               boite2.setNegativeButton("Non", new DialogInterface.OnClickListener() {
	 	                  
		                   public void onClick(DialogInterface dialog, int which) {
		                    	dialog.dismiss();
		                   }
		                   }
		            );
		             
	             
	             boite2.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
	                   
	                    public void onClick(DialogInterface dialog, int which) {
	                    	finish();
	                    }
	                    }
	                );
	             
	            
	             
	             boite2.show();
					
				}//fin onclik
				
				protected void nouvellePartie(){
					
					
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
			        alertDialog.setIcon(R.drawable.ic_launcher);
			        alertDialog.setCancelable(true);
			        alertDialog.setTitle("Informations du joueur:");
			        LayoutInflater li = LayoutInflater.from(this);  
			        final View view = li.inflate(R.layout.enregchoixclasse, null); 
			        alertDialog.setView(view);
			        
			        Spinner spinner = (Spinner)view.findViewById(R.id.classe); 
			        //Ajout Listener sur sélection dans la liste 
			        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){ 
			            @Override 
			            public void onItemSelected(AdapterView<?> parent, View view, 
			                    int pos, long id) { 
			                /*Toast.makeText(parent.getContext(), 
			                      "The sport is " + parent.getItemAtPosition(pos).toString() + " (id=" + 
			                      getResources().getIntArray(R.array.classe_id)[pos] + ")", 
			                      Toast.LENGTH_LONG).show(); */
			                clas=getResources().getIntArray(R.array.classe_id)[pos];
			            } 
			            @Override 
			            public void onNothingSelected(AdapterView<?> arg0) { 

			            } 
			        }); 
			        
					alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	 	                  
		                   public void onClick(DialogInterface dialog, int which) {
		                	   EditText nom=(EditText)view.findViewById(R.id.nom);
		                       String nomJoueur = nom.getText().toString(); 
		                       String date = ""+(new Date().getYear()+1900)+"-"+(new Date().getMonth()+1)+"-"+new Date().getDate();
		                       p = new Parties();
		                       p.setNomJoueur(nomJoueur);
		                       p.setDate(""+date);
		                       p.setPoints(0);
		                       p.setSomme(0);
		                       p.setRang(0);
		                       p.setClasse(clas);
		                       p.setNiveau(1);
		                       
		                       if(nomJoueur.equals("")){
		                    	   afficheToast("Veuillez renseigner un nom SVP");
		                       }
		                       else{
		                    	 //on appel la vue
			                       Intent i = new Intent(MainActivity.this, JeuActivity.class);
			                       i.putExtra("partie", (Serializable) p);
			                       startActivityForResult(i,0);
			                       overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
			                       dialog.dismiss();
		                       }
		                       
		                   }
		                   }
		            );
			        
					alertDialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
	 	                  
		                   public void onClick(DialogInterface dialog, int which) {
		                    	dialog.dismiss();
		                   }
		                   }
		            );

					alertDialog.show();
					
				}//fin affichePartie
				
				protected void chargerParties(){
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
			        alertDialog.setIcon(R.drawable.ic_launcher);
			        alertDialog.setCancelable(true);
			        alertDialog.setTitle("Choisissez votre Partie:");
			        
			        ArrayList<String> listePartie = new ArrayList<String>();
			        final ArrayList<Parties> listeParties = new ArrayList<Parties>();
					PartieManager pm = new PartieManager(this);
					pm.open();
					Cursor c = pm.getPartie();
					while (c.moveToNext()){
						p = new Parties();
				    	//p.setIdPartie(c.getInt(c.getColumnIndex("idPartie")));
						p.setNomJoueur(c.getString(c.getColumnIndex("nomJoueur")));
						p.setDate(c.getString(c.getColumnIndex("dateJeu")));
						p.setPoints(c.getInt(c.getColumnIndex("points")));
						p.setSomme(c.getInt(c.getColumnIndex("somme")));
						p.setRang(c.getInt(c.getColumnIndex("rang")));
						p.setClasse(c.getInt(c.getColumnIndex("classe")));
						p.setNiveau(c.getInt(c.getColumnIndex("niveau")));
						listeParties.add(p);
						listePartie.add(p.getNomJoueur()+":    class="+p.getClasse()+" : niv="+p.getNiveau());
				    }
					c.close();
					pm.close();
					
					final ArrayAdapter <String> adapter = 
							new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listePartie);
					//lv.setAdapter(adapter);
					
					alertDialog.setAdapter(adapter, new DialogInterface.OnClickListener() {
	 	                  
		                   public void onClick(DialogInterface dialog, int which) {
		                	   //String strName = adapter.getItem(which);
		                	   //afficheToast(strName);
		                	   p = listeParties.get(which);
		                	   dialog.dismiss();
		                	   Intent i = new Intent(MainActivity.this, JeuActivity.class);
			            	   i.putExtra("partie", (Serializable) p);
			            	   startActivityForResult(i,1);
			            	   
		                   }
		                   }
		            );
					
					alertDialog.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
	 	                  
		                   public void onClick(DialogInterface dialog, int which) {
		                    	dialog.dismiss();
		                   }
		                   }
		            );
					
					
					
					alertDialog.show();
					
				}//fin affichePartie
				
				
				protected void commentJouer(){
					AlertDialog.Builder boite2;
	                boite2 = new AlertDialog.Builder(this);
	                boite2.setTitle("Comment Jouer?");
	                boite2.setIcon(R.drawable.ic_launcher);
	                String message = "Après avoir lancé une nouvelle partie ou " +
	                		"chargé une partie existante, Vous devez cocher une réponse juste parmi les " +
	                		"4 propositions.\n" +
	                		"vous commencez le jeu au niv 1. " +
	                		"Si à l'issu de ce niveau vous avez 20/20, vous passez au niv 2 " +
	                		"Si à l'issu de ce niveau vous obtenez encore 20/20 vous passez au niveau 3. " +
	                		"Si a l'issu de ce niveau vous avez encore 20/20, un diplome vous est délivré " +
	                		"et vous débloquez un expert contre qui vous jouerez. " +
	                		"Si vous le battez, vous débloquez un autre expert et ainsi de suite, " +
	                		"jusqu'au niveau 15 à l'issu duquel vous devenez un expert et pouvez challenger le concepteur du jeu en ligne " +
	                		"Bon Jeu et surtout Bonne Apprentissage de l'informatique.";
	                		
	                    boite2.setMessage(message);
	               
	            
	             boite2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                   
	                    public void onClick(DialogInterface dialog, int which) {
	                    	dialog.dismiss();
	                    }
	                    }
	                );
	             
	            
	             
	             boite2.show();
				}//fin comment jouer?
				
				protected void aPropos(){
					AlertDialog.Builder boite2;
	                boite2 = new AlertDialog.Builder(this);
	                boite2.setTitle("A Propos:");
	                boite2.setIcon(R.drawable.ic_launcher);
	                String message = "Jeu éducatif conçu en conformité avec le programme officiel" +
	                		"d'informatique dans le secondaire, afin de permettre aux élèves" +
	                		"des lycées et collèges de mieux préparer leurs examens en informatique " +
	                		"tout en restant arrimés à l'intégration des TIC dans nos usses.\n\n" +
	                		"Concepteur: ASSAMBA Pasquier\n " +
	                		"email: mikelferry2012@gmail.com\n" +
	                		"2015";
	                		
	                    boite2.setMessage(message);
	               
	            
	             boite2.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                   
	                    public void onClick(DialogInterface dialog, int which) {
	                    	dialog.dismiss();
	                    }
	                    }
	                );
	             
	            
	             
	             boite2.show();
				}//A propos
				
				protected void onActivityResult (int requestCode, int resultCode, Intent data) {
				      // on récupère le statut de retour de l'activité 2 c'est à dire l'activité numéro 1000
				      if(requestCode==0){
				    	  if(resultCode==3){
				    		  finish();
				    	  }
				    	  if(resultCode==4){
				    		  this.afficheToast("RETOUR A L'ACCUEIL");
				    	  }
				    
				      }
				      super.onActivityResult (requestCode, resultCode, data);
				  }
}
