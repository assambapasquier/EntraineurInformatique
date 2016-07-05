package ManagerDAO;

import metier.Parties;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PartieManager {
	public static final String CREATE_TABLE_PARTIES = "CREATE TABLE Parties("+
	"idPartie INTEGER PRIMARY KEY AUTOINCREMENT,"+
	"nomJoueur TEXT,"+
	"dateJeu VARCHAR(10),"+
	"points INTEGER,"+
	"somme INTEGER,"+
	"rang INTEGER,"+ 
	"classe INTEGER,"+ 
	"niveau INTEGER,"+
	"FOREIGN KEY(niveau) REFERENCES Niveaux(niveau)"+	
");";
						    
						    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
						    private SQLiteDatabase db;

						    // Constructeur
						    public PartieManager(Context context)
						        {
						        	maBaseSQLite = new MySQLite(context);
						        }

						    public void open()
						        {
						        	//on ouvre la table en lecture/écriture
						        	db = maBaseSQLite.getWritableDatabase();
						        }

						    public void close()
						        {
						        	//on ferme l'accès à la BDD
						        	db.close();
						        }

						    public long addPartie(Parties partie) {
						        // Ajout d'un enregistrement dans la table

							        ContentValues values = new ContentValues();
							        //values.put("idPartie", partie.getIdPartie());
							        values.put("nomJoueur", partie.getNomJoueur());
							        values.put("dateJeu", partie.getDate());
							        values.put("points", partie.getPoints());
							        values.put("somme", partie.getSomme());
							        values.put("rang", partie.getRang());
							        values.put("classe", partie.getClasse());
							        values.put("niveau", partie.getNiveau());
							        
							        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
							        return db.insert("Parties",null,values);
						        }

						    public int modPartie(Parties partie) {
							        // modification d'un enregistrement
							        // valeur de retour : (int) nombre de lignes affectées par la requête
							
						    	ContentValues values = new ContentValues();
						    	//values.put("idPartie", partie.getIdPartie());
						        values.put("nomJoueur", partie.getNomJoueur());
						        values.put("dateJeu", partie.getDate());
						        values.put("points", partie.getPoints());
						        values.put("somme", partie.getSomme());
						        values.put("rang", partie.getRang());
						        values.put("classe", partie.getClasse());
						        values.put("niveau", partie.getNiveau());
							        
							        String where = "idPartie = ?";
							        String[] whereArgs = {partie.getIdPartie()+""};
							
							        return db.update("Parties", values, where, whereArgs);
						        }

						    public int supPartie(Parties partie) {
							        // suppression d'un enregistrement
							        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon
							
							        String where = "idPartie = ?";
							        String[] whereArgs = {partie.getIdPartie()+""};
							
							        return db.delete("Parties", where, whereArgs);
						        }

						    public Parties getPartie(int idPartie) {
						        // Retourne l'animal dont l'id est passé en paramètre

						        Parties part=new Parties();

						        Cursor c = db.rawQuery("SELECT * FROM Parties WHERE idPartie="+idPartie, null);
						        if (c.moveToFirst()) {
						        	part.setIdPartie(c.getInt( c.getColumnIndex("idPartie")));
						        	part.setNomJoueur(c.getString(c.getColumnIndex("nomJoueur")));
						            part.setDate(c.getString(c.getColumnIndex("dateJeu")));
						            part.setPoints(c.getInt(c.getColumnIndex("points")));
						            part.setSomme(c.getInt(c.getColumnIndex("somme")));
						            part.setRang(c.getInt(c.getColumnIndex("rang")));
						            part.setClasse(c.getInt(c.getColumnIndex("classe")));
						            part.setNiveau(c.getInt(c.getColumnIndex("niveau")));
						            c.close();
						            }

						        return part;
						        }

						    public Cursor getPartie() {
						        // sélection de tous les enregistrements de la table
						        return db.rawQuery("SELECT * FROM Parties", null);
						        }

}
