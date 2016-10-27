
package modele;

import java.util.ArrayList;
import java.util.Date;

public class Spectacle {
    
    public int numero;
    public String nom;

    public ArrayList<Representation> representations;

    public Spectacle(int numero, String nom) {
        this.numero = numero;
        this.nom = nom;
    }
    
    public void addRepresentation(Date date, ArrayList places){
        representations.add(new Representation(date, places));
    }
}
