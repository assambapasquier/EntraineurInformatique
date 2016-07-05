package ManagerDAO;

import metier.Niveaux;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NiveauManager {
	public static final String CREATE_TABLE_BONNEREPONSES = "CREATE TABLE Niveaux("+
	"niveau  INTEGER PRIMARY KEY AUTOINCREMENT"+
");";
	
	private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;
    
    public NiveauManager(Context context)
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

	public long addNiveau(Niveaux n) {
        // Ajout d'un enregistrement dans la table

	        ContentValues values = new ContentValues();
	        values.put("niveau", n.getNiveau());
	        // insert() retourne l'id du nouvel enregistrement inséré, ou -1 en cas d'erreur
	        return db.insert("Niveaux",null,values);
        }

    public int modNiveau(Niveaux n) {
	        // modification d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la requête
	
    	ContentValues values = new ContentValues();
    	values.put("niveau", n.getNiveau());
	        String where = "niveau = ?";
	        String[] whereArgs = {n.getNiveau()+""};
	
	        return db.update("Niveaux", values, where, whereArgs);
        }

    public int supNiveau(Niveaux n) {
	        // suppression d'un enregistrement
	        // valeur de retour : (int) nombre de lignes affectées par la clause WHERE, 0 sinon
	
	        String where = "niveau = ?";
	        String[] whereArgs = {n.getNiveau()+""};
	
	        return db.delete("Niveaux", where, whereArgs);
        }

    public Niveaux getNiveau(int niveau) {
        // Retourne l'animal dont l'id est passé en paramètre

    	Niveaux n = new Niveaux();

        Cursor c = db.rawQuery("SELECT * FROM Niveaux WHERE idReponse = "+niveau, null);
        if (c.moveToFirst()) {
        	n.setNiveau(c.getInt(c.getColumnIndex("niveau")));
            c.close();
            }

        return n;
        }

    public Cursor getNiveau() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM Niveaux", null);
        }
}
