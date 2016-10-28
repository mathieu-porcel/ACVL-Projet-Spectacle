
package modele;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Reservation implements Serializable {
    
    public ArrayList<Place> places;
    public Representation representation;
    public Compte compte;

    public Reservation( Compte compte, ArrayList<Place> places, Representation representation) {
        this.places = places;
        this.representation = representation;
        this.compte = compte;
    }

    public void libere(Place place){
        representation.libere(this);
        compte.libere(this);
    }
    
}
