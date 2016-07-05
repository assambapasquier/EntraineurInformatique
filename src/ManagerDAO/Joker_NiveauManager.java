package ManagerDAO;

import metier.Joker_Niveaux;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Joker_NiveauManager {
	public static final String CREATE_TABLE_BONNEREPONSES = "CREATE TABLE Jokers_Niveaux("+
	"joker INTEGER,"+
	"niveau INTEGER,"+
	"PRIMARY KEY (joker, niveau),"+
	"FOREIGN KEY(joker) REFERENCES Jokers(idJoker),"+
	"FOREIGN KEY(niveau) REFERENCES Niveaux(niveau)"+	
");";
	
	private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    
    public Joker_NiveauManager(Context context)
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

	public long addJoker_Niveau(Joker_Niveaux j_n) {
        // Ajout d'un enregistrement dans la table

	        ContentValues values = new ContentValues();
	        values.put("joker", j_n.getJoker());
	        values.put("niveau", j_n.getNiveau());     
	        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
	        return db.insert("Joker_Niveaux",null,values);
        }

    public int modJoker_Niveau(Joker_Niveaux j_n) {
	        // modification d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la requête
	
    	ContentValues values = new ContentValues();
    	values.put("joker", j_n.getJoker());
        values.put("niveau", j_n.getNiveau()); 
	        String where = "joker = ? AND niveau = ?";
	        String[] whereArgs = {j_n.getJoker()+"", j_n.getNiveau()+""};
	
	        return db.update("Joker_Niveaux", values, where, whereArgs);
        }

    public int supJoker_Niveau(Joker_Niveaux j_n) {
	        // suppression d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon
	
	        String where = "joker = ? AND niveau = ?";
	        String[] whereArgs = {j_n.getJoker()+"", j_n.getNiveau()+""};
	
	        return db.delete("Joker_Niveaux", where, whereArgs);
        }

    public Joker_Niveaux getJoker_Niveau(int joker, int niveau) {
        // Retourne l'animal dont l'id est passé en paramètre

    	Joker_Niveaux j_n = new Joker_Niveaux();

        Cursor c = db.rawQuery("SELECT * FROM Joker_Niveaux WHERE joker = "+joker+"AND niveau = "+niveau, null);
        if (c.moveToFirst()) {
        	j_n.setJoker(c.getInt(c.getColumnIndex("joker")));
        	j_n.setNiveau(c.getInt(c.getColumnIndex("niveau")));
       
            c.close();
            }

        return j_n;
        }

    public Cursor getJoker_Niveau() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM Joker_Niveaux", null);
        }
}
