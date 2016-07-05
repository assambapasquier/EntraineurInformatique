package ManagerDAO;

import metier.Jokers;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class JokerManager {
	public static final String CREATE_TABLE_BONNEREPONSES = "CREATE TABLE Jokers("+
	"idJoker INTEGER PRIMARY KEY AUTOINCREMENT,"+
	"libelleJoker TEXT,"+
	"imageJoker TEXT"+
");";
	
	private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    
    public JokerManager(Context context)
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

	public long addJoker(Jokers j) {
        // Ajout d'un enregistrement dans la table

	        ContentValues values = new ContentValues();
	        values.put("idJoker", j.getIdJoker());
	        values.put("libelleJoker", j.getLibelleJoker());
	        values.put("imageJoker", j.getImageJoker());
	        
	        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
	        return db.insert("Jokers",null,values);
        }

    public int modJoker(Jokers j) {
	        // modification d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la requête
	
    	ContentValues values = new ContentValues();
    	values.put("idJoker", j.getIdJoker());
        values.put("libelleJoker", j.getLibelleJoker());
        values.put("imageJoker", j.getImageJoker());
	        String where = "idJoker = ?";
	        String[] whereArgs = {j.getIdJoker()+""};
	
	        return db.update("Jokers", values, where, whereArgs);
        }

    public int supJoker(Jokers j) {
	        // suppression d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon
	
	        String where = "idJoker = ?";
	        String[] whereArgs = {j.getIdJoker()+""};
	        return db.delete("Jokers", where, whereArgs);
        }

    public Jokers getJoker(int idJoker) {
        // Retourne l'animal dont l'id est passé en paramètre

    	Jokers j = new Jokers();

        Cursor c = db.rawQuery("SELECT * FROM Jokers WHERE idReponse = "+idJoker, null);
        if (c.moveToFirst()) {
        	j.setIdJoker(c.getInt(c.getColumnIndex("idJoker")));
        	j.setLibelleJoker(c.getString(c.getColumnIndex("libelleJoker")));
        	j.setImageJoker(c.getString(c.getColumnIndex("imageJoker")));
            c.close();
            }

        return j;
        }

    public Cursor getJoker() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM Jokers", null);
        }
}
