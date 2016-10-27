
package modele;

import java.util.ArrayList;

public class Reservation {
    
    public ArrayList<Place> places;
    public Representation representation;
    public Compte compte;

    public Reservation( Compte compte, ArrayList<Place> places, Representation representation) {
        this.places = places;
        this.representation = representation;
        this.compte = compte;
    }

    public void libere(Place place){
        //TODO
    }
    
}
