
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@SuppressWarnings("serial")
public class Representation implements Serializable {

    public Date date;
    public Salle salle;
    public ArrayList<Reservation> reservations;
    public ArrayList<Dossier> dossiers;
    public Spectacle spectacle;

    public Representation(Date date, Spectacle spectacle, Salle salle) {
        this.date = date;
        this.spectacle = spectacle;
        this.salle = salle;
        this.reservations = new ArrayList<>();
        this.dossiers = new ArrayList<>();
    }

    public void reserve(Reservation reservation) {
        reservations.add(reservation);
    }

    public void libere(Reservation reservation) {
        reservations.remove(reservation);
    }

    public void achat(Dossier dossier) {
        dossiers.add(dossier);
    }
    
    public ArrayList<Place> getPlacesReserver(){
        ArrayList<Place> ret = new ArrayList();
        for (Reservation r : reservations){
            ret.addAll(r.places);
        }
        return ret;
    }
    
    public ArrayList<Place> getPlacesAcheter(){
        ArrayList<Place> ret = new ArrayList();
        for (Dossier d : dossiers){
            ret.addAll(d.getPlaces());
        }
        return ret;
    }

}
