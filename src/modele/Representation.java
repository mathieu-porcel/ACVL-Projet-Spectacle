
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class Representation implements Serializable {

    public Date date;
    public ArrayList<Place> placesOccupee;
    public ArrayList<Reservation> reservations;
    public ArrayList<Dossier> dossiers;
    public Spectacle spectacle;

    public Representation(Date date, Spectacle spectacle) {
        this.date = date;
        this.spectacle = spectacle;
        this.placesOccupee = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.dossiers = new ArrayList<>();
    }

    public void reserve(Reservation reservation) {
        placesOccupee.addAll(reservation.places);
        reservations.add(reservation);
    }

    public void libere(Reservation reservation) {
        reservations.remove(reservation);
        placesOccupee.removeAll(reservation.places);
    }

    public void achat(Dossier dossier) {
        placesOccupee.addAll(dossier.getPlaces());
        dossiers.add(dossier);
    }

}
