
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
        this.representations = new ArrayList<>();
    }
    
    public void addRepresentation(Date date){
        representations.add(new Representation(date, this));
    }
}
