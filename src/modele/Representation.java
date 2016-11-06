package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@SuppressWarnings("serial")
public class Representation implements Serializable {

    public Date date;
    public Salle salle;
    private ArrayList<Reservation> reservations;
    public ArrayList<Dossier> dossiers;
    public Spectacle spectacle;

    public boolean isAnnuler;

    public Representation(Date date, Spectacle spectacle, Salle salle) {
        this.date = date;
        this.spectacle = spectacle;
        this.salle = salle;
        this.reservations = new ArrayList<>();
        this.dossiers = new ArrayList<>();
        this.isAnnuler = false;
    }

    public boolean reserve(Reservation reservation) {
        if (!isEndReservation() && !isAnnuler) {
            getReservations().add(reservation);
            return true;
        } else {
            return false;
        }
    }

    public void libere(Reservation reservation) {
        getReservations().remove(reservation);
    }

    public void achat(Dossier dossier) {
        dossiers.add(dossier);
    }

    public float getBenefices() {
        float total = 0;
        for (Dossier dossier : dossiers) {
            total += dossier.getPrix();
        }
        return total;
    }

    public boolean isEndReservation() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR_OF_DAY, 1);
        return cal.getTime().getTime() >= date.getTime();
    }

    public ArrayList<Reservation> getReservations() {
        if (isEndReservation() && !reservations.isEmpty()) {
            for (Reservation r : reservations) {
                r.libereAll();
            }
            reservations.clear();
        }
        return reservations;
    }

    public ArrayList<Place> getPlacesReserver() {
        ArrayList<Place> ret = new ArrayList<>();
        for (Reservation r : getReservations()) {
            ret.addAll(r.places);
        }
        return ret;
    }

    public ArrayList<Place> getPlacesAchetees() {
        ArrayList<Place> ret = new ArrayList<>();
        for (Dossier d : dossiers) {
            ret.addAll(d.getPlaces());
        }
        return ret;
    }

    public void annuler() {
        isAnnuler = true;
    }

}
