
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class Spectacle implements Serializable {
    
    public int numero;
    public String nom;

    public ArrayList<Representation> representations;

    public Spectacle(int numero, String nom) {
        this.numero = numero;
        this.nom = nom;
    }
    
    public void addRepresentation(Date date, ArrayList<Place> places){
        representations.add(new Representation(date, places, this));
    }
}
