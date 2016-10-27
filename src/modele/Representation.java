
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
    
    public void reserve(Reservation reservation){
        placesLibre.removeAll(reservation.places);
        reservations.add(reservation);
    }
    
    public void libere(Reservation reservation){
        reservations.remove(reservation);
        placesLibre.addAll(reservation.places);
    }
    
    public void achat(Dossier dossier){
        placesLibre.removeAll(dossier.getPlaces());
        dossiers.add(dossier);
    }
    
}
