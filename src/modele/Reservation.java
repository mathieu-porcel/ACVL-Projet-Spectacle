
package modele;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Reservation implements Serializable {
    
    public ArrayList<Place> places;
    public Representation representation;
    public Compte compte;

    public Reservation(Compte compte, ArrayList<Place> places, Representation representation) throws ReservationImpossible {
        if (!representation.reserve(this)){
            throw new ReservationImpossible();
        }
        compte.reserve(this);
        this.places = places;
        this.representation = representation;
        this.compte = compte;
    }

    public void libere(Place place){
        places.remove(place);
        if (places.isEmpty()){
            representation.libere(this);
            compte.libere(this);
        }
    }
    
    public void libereAll(){
        places.clear();
        representation.libere(this);
        compte.libere(this);
    }
}
