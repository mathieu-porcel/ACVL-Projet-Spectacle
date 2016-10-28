
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class Representation implements Serializable {

    public Date date;
    public ArrayList<Place> placesLibre;
    public ArrayList<Reservation> reservations;
    public ArrayList<Dossier> dossiers;
    public Spectacle spectacle;

    public Representation(Date date, ArrayList<Place> placesLibre, Spectacle spectacle) {
        this.date = date;
        this.placesLibre = placesLibre;
        this.spectacle = spectacle;
        this.reservations = new ArrayList<>();
        this.dossiers = new ArrayList<>();
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
