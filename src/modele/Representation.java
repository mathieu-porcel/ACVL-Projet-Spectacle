
package modele;

import java.util.ArrayList;
import java.util.Date;

public class Representation {

    public Date date;
    public ArrayList<Place> placesLibre;
    public ArrayList<Reservation> reservations;
    public ArrayList<Dossier> dossiers;

    public Representation(Date date, ArrayList<Place> placesLibre) {
        this.date = date;
        this.placesLibre = placesLibre;
    }
    
    public void reserve(Place place){
        //TODO
    }
    
    public void libere(Place place){
        //TODO
    }
    
    public void achat(Place place){
        //TODO
    }
    
}
