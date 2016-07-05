package metier;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LireFichier {
	

	public static ArrayList<String> lireFichier(InputStream ins){
		
		ArrayList<String> ch = new ArrayList<String>();
		
		if(ins!=null){
				InputStreamReader isr = new InputStreamReader(ins);
				BufferedReader br = new BufferedReader(isr);
				String chaine;
				
				try {
					while((chaine = br.readLine())!=null){
						ch.add(chaine);
					} 
					//on ferme le fichier
					ins.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//fin while
		}//fin IF
		return ch;
	}
	
}
