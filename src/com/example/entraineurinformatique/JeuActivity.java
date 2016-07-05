package com.example.entraineurinformatique;

import java.io.Serializable;
import java.util.ArrayList;

import metier.Parties;
import metier.Questions;

import ManagerDAO.QuestionManager;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class JeuActivity extends Activity implements RadioGroup.OnCheckedChangeListener{

	//appele lorsque l'activité est créée
	ArrayList<Questions> questions = new ArrayList<Questions>();
	ArrayList<Integer> aleas = new ArrayList<Integer>();
	ArrayList<Integer> rep_aleatoire_num = new ArrayList<Integer>();
	
	Parties p; /* celle en cours de jeu*/
	Questions q;
	boolean pasdequestions=true;
	
	//les widgets
	TextView question, numQuestion, nomJoueur, infosJoueur, correction;
	RadioGroup rep;
	RadioButton rp1, rp2, rp3, rp4;
	Button btnValiderRep, questionSuivante;
	Resources res;
	
	String[] rep_aleatoire = new String[4];
	int cmpteur = 1, points=0, niveau=1, hasard, longueur, k=0;
	String[] propositions;
	int checkedIndex = -1;
	String aleatoire = null;
	String reponse=null;
	MediaPlayer mPlayer, click, bonnerep, mauvaiserep;
	
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				
				super.onCreate(savedInstanceState);
				setContentView(R.layout.jeu);
				mPlayer = MediaPlayer.create(this, R.raw.jeu);
				click = MediaPlayer.create(this, R.raw.click);
				bonnerep = MediaPlayer.create(this, R.raw.bonnerep);
				mauvaiserep = MediaPlayer.create(this, R.raw.mauvaiserep);
				
				mPlayer.setLooping(true);
				
				bonnerep.setVolume(180, 180);
				mauvaiserep.setVolume(100, 100);
				mPlayer.setVolume(100, 100);
				mPlayer.start();
		        
				p = new Parties();
				//les widgets de notre ecran
				question = (TextView)findViewById(R.id.question);
				nomJoueur = (TextView)findViewById(R.id.nomJoueur);
				infosJoueur = (TextView)findViewById(R.id.infosJoueur);
				correction = (TextView)findViewById(R.id.correction);
				numQuestion = (TextView)findViewById(R.id.numQuestion);
				rep = (RadioGroup)findViewById(R.id.reponses);
				rp1 = (RadioButton)findViewById(R.id.rep1);
				rp2 = (RadioButton)findViewById(R.id.rep2);
				rp3 = (RadioButton)findViewById(R.id.rep3);
				rp4 = (RadioButton)findViewById(R.id.rep4);
				btnValiderRep = (Button)findViewById(R.id.repondre);
				questionSuivante = (Button)findViewById(R.id.suivant);
				
				//je recupere l'objet Partie
				p = (Parties) getIntent().getSerializableExtra("partie");
				//afficheToast(""+p.getNomJoueur());
				
				nomJoueur.setText("Bienvenu  "+ p.getNomJoueur()+ "!");
				niveau = p.getNiveau();
				
				//questionSuivante.setText("Question Suivante>>>");
				//infosJoueur.setText("Question n°: "+cmpteur+"/20 --- points: "+ points+"/20 \n Classe: "+p.getClasse()+" --- Niveau: "+niveau);
				//on desactive le boutton suivant pour l'instant
				annulerBoutton1();
				
				res = getResources();
				
			    rep.setOnCheckedChangeListener( this );
			 
				//je charge les questions
			    if(!questions.isEmpty()) questions.clear();
			    questions = recupereQuestions(p.getClasse(), p.getNiveau()); //je stocke mes questions
			    
			    
			    if(!pasdequestions){
					
					//------------------------------------------------------
						//je fabrique la liste des 20 questions aléatoires
						longueur = questions.size();
						int hasard;
						for(int i=0; i<20;i++){
							hasard = (int)(Math.random()*longueur);
							while (aleas.contains(hasard)){
								hasard = (int)(Math.random()*longueur);
							}
							aleas.add(hasard);
						}
					//------------------------------------------------------------------------
						//aleatoire pour les propositions des reponses
						int h;
						if(!rep_aleatoire_num.isEmpty()) rep_aleatoire_num.clear();
						for(int i=0; i<4;i++){
							h = (int)(Math.random()*4);
							while (rep_aleatoire_num.contains(h)){
								h = (int)(Math.random()*4);
							}
							rep_aleatoire_num.add(h);
						}
						//-------------------fin aleas reponse--------------------------------
						
						
						//numQuestion.setText("Question N°: "+1+"/20");
						//numQuestion.setTextColor(Color.RED); //couleur rouge pour le num
						q = new Questions();
						question.setTextColor(Color.BLUE); //les questions en bleus
						q = questions.get(aleas.get(k));
						rep_aleatoire[0] = q.getProp1();
						rep_aleatoire[1] = q.getProp2();
						rep_aleatoire[2] = q.getProp3();
						rep_aleatoire[3] = q.getBonneRep();
						
						question.setText(q.getLibelleQuestion());
						rp1.setText(rep_aleatoire[rep_aleatoire_num.get(0)]);
						rp2.setText(rep_aleatoire[rep_aleatoire_num.get(1)]);
						rp3.setText(rep_aleatoire[rep_aleatoire_num.get(2)]);
						rp4.setText(rep_aleatoire[rep_aleatoire_num.get(3)]);
						
						infosJoueur.setText("Question n°: "+cmpteur+"/20 --- points: "+ points+"/20 \n Classe: "+p.getClasse()+" --- Niveau: "+niveau);
						
						//clic sur le boutton valider
						btnValiderRep.setOnClickListener( new OnClickListener() {
				             @SuppressWarnings("static-access")
							public void onClick( View view ) { 
				            	 click.start();
				            	  if(checkedIndex==-1){
				            		  afficheToast("Cocher une proposition");
				            	  }
				            	  else{
				            		  activerButton1();
				            		  annulerBoutton2();
				            		  reponse = ""+((RadioButton) findViewById(checkedIndex)).getText();
				            		  if(reponse.equals(q.getBonneRep())){
				            			  correction.setTextColor(Color.GREEN);
				            			  //((RadioButton) findViewById(checkedIndex)).setTextColor(Color.GREEN);
				            			  correction.setText("Bonne réponse!");
				            			  bonnerep.start();
				            			  points++;
				            		  }
				            		  else{
				            			  correction.setTextColor(Color.RED);
				            			  //((RadioButton) findViewById(checkedIndex)).setTextColor(Color.RED);
				            			  correction.setText("Mauvaise réponse!");
				            			  mauvaiserep.start();
				            		  }
				            		  
				            	  }
				              }//fin public void
				        });
						
						//on clique sur le bouton suivant
						questionSuivante.setOnClickListener( new OnClickListener() {
				             public void onClick( View view ) { 
				            	 click.start();
				            	 	cmpteur++;
				            	 	k++;
				            	 	
				            	 	//si on n'est pas encore a la 20e question
				            	 	if(cmpteur<21){
				            	 		rep.clearCheck(); //on décoche ce qui était coché
					            		  question.setText("");
					  					  rp1.setText("");
					  					  rp2.setText("");
					  					  rp3.setText("");
					  					  correction.setText("");
					  					//((RadioButton) findViewById(checkedIndex)).setTextColor(Color.BLACK);
					            		  raffraichiEcran(k);
				            	 	}
				            	 	else{
				            	 		if(points==20){//le joueur a eu 20/20
				            	 			p.setPoints(points);
					            	 		Intent i = new Intent(JeuActivity.this, ChangeNivActivity.class);
					            	 		i.putExtra("partie", (Serializable) p);
					            	 		startActivityForResult(i,10);
					            	 		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					            	 		finish();
				            	 		}
				            	 		else{//il n'a pas eu 20/20
				            	 			p.setPoints(points);
					            	 		Intent i = new Intent(JeuActivity.this, ResultatActivity.class);
					            	 		i.putExtra("partie", (Serializable) p);
					            	 		startActivityForResult(i,20);
					            	 		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
					            	 		finish();
				            	 		}
				            	 		
				            	 	}
				            		  
				            }
				        });
						
					}
					else{//aucune question trouvée
						afficheToast("Pas de questions pour le moment SVP");
						finish();
					}
				
				p = (Parties) getIntent().getSerializableExtra("partie");
				//afficheToast(""+p.getNomJoueur());
				nomJoueur.setText("Bienvenu  "+ p.getNomJoueur());
			}

			

			//lorsque l'activité a termine son cycle de vie
			//on fait les nettoyage et les vidange (de la memoire utilisé)
			@Override
			protected void onDestroy() {
				//le code se met ici avant destroy()
				//questions20.clear();
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
					//Intent i = new Intent(this, MainActivity.class);
					//startActivity(i);
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
			
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					checkedIndex = checkedId;
				}
				
				//on rafraichie l'ecran de jeu
				public void raffraichiEcran(int index){
					checkedIndex = -1;
					infosJoueur.setText("Question n°: "+cmpteur+"/20 --- points: "+ points+"/20 \n Classe: "+p.getClasse()+" --- Niveau: "+niveau);
					annulerBoutton1();
					activerButton2();
					//((RadioButton) findViewById(checkedIndex)).setTextColor(Color.BLACK);
					//aleatoire pour les propositions des reponses--------------------------
					int h;
					if(!rep_aleatoire_num.isEmpty()) rep_aleatoire_num.clear();
					for(int i=0; i<4;i++){
						h = (int)(Math.random()*4);
						while (rep_aleatoire_num.contains(h)){
							h = (int)(Math.random()*4);
						}
						rep_aleatoire_num.add(h);
					}
					//-------------------fin aleas reponse--------------------------------
					
					q = new Questions();
					//question.setTextColor(Color.BLUE); //les questions en bleus
					q = questions.get(aleas.get(index));
					rep_aleatoire[0] = q.getProp1();
					rep_aleatoire[1] = q.getProp2();
					rep_aleatoire[2] = q.getProp3();
					rep_aleatoire[3] = q.getBonneRep();
					
					question.setText(q.getLibelleQuestion());
					rp1.setText(rep_aleatoire[rep_aleatoire_num.get(0)]);
					rp2.setText(rep_aleatoire[rep_aleatoire_num.get(1)]);
					rp3.setText(rep_aleatoire[rep_aleatoire_num.get(2)]);
					rp4.setText(rep_aleatoire[rep_aleatoire_num.get(3)]);
				}
				
				
				protected void onActivityResult (int requestCode, int resultCode, Intent data) {
				      // on récupère le statut de retour de l'activité 2 c'est à dire l'activité numéro 1000
				      if(requestCode==10){
				    	  if(resultCode==3){
				    		  setResult(3);
				    		  finish();
				    	  }
				    	  else{
				    		  
				    	  }
				    	  
				      }
				      else{
				    	  if(requestCode==20){
				    		  if(resultCode==3){
					    		  setResult(3);
					    		  finish();
					    	  }
					    	  else{
					    		  finish();
					    	  }
				    	  }
				      }
				      super.onActivityResult (requestCode, resultCode, data);
				   }
				
				private ArrayList<Questions> recupereQuestions(int classe, int niveau) {
					// TODO Auto-generated method stub
					QuestionManager question = new QuestionManager(this);
					ArrayList<Questions> quest = new ArrayList<Questions>();
					question.open();
					Questions q;
					Cursor c = question.getQuestionClasseNiv(p.getClasse(), p.getNiveau());
				    while (c.moveToNext()){
				    	pasdequestions=false;
				    	q = new Questions();
				    	q.setIdQuestion(c.getInt(c.getColumnIndex("idQuestion")));
				    	q.setLibelleQuestion(c.getString(c.getColumnIndex("libelleQuestion")));
				    	q.setProp1(c.getString(c.getColumnIndex("prop1")));
				    	q.setProp2(c.getString(c.getColumnIndex("prop2")));
				    	q.setProp3(c.getString(c.getColumnIndex("prop3")));
				    	q.setBonneRep(c.getString(c.getColumnIndex("bonneRep")));
				    	q.setClasse(c.getInt(c.getColumnIndex("classe")));
				    	q.setNiveau(c.getInt(c.getColumnIndex("niveau")));
				    	
				    	//afficheToast(q.getLibelleQuestion());
				    	quest.add(q);
				    }
					
					c.close(); // fermeture du curseur
					question.close();
					return quest;
				}
				
				//desactive les bouttons
				public void annulerBoutton1(){
					questionSuivante.setEnabled(false);
					questionSuivante.setBackgroundResource(R.drawable.suivant_noir);
				}
				public void annulerBoutton2(){
					btnValiderRep.setEnabled(false);
					btnValiderRep.setBackgroundResource(R.drawable.repondre_noir);
				}
				
				//on réactive les bouttons
				public void activerButton1(){
					questionSuivante.setEnabled(true);
					questionSuivante.setBackgroundResource(R.drawable.suivant);
				
				}
				public void activerButton2(){
					btnValiderRep.setEnabled(true);
					btnValiderRep.setBackgroundResource(R.drawable.repondre);
				
				}
				
				
				//permet d'afficher les toast
				public void afficheToast(String text)
				   {
				      Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
				   }
				
}
