
package controleur;

import java.util.ArrayList;
import java.util.Date;
import modele.Dossier;
import modele.Place;
import modele.Representation;
import modele.Reservation;
import modele.ReservationImpossible;
import modele.TypeCompte;
import vue.InterfaceGraphique;
import vue.VueAchats;
import vue.VueChoixPlaces;
import vue.VuePreAchat;
import vue.VueRecutAchat;
import vue.VueReservations;

public class ControleurReservationsAchats {
    
    private static ControleurReservationsAchats instance;
    
    private Controleur controleur;
    
    /**
     * Singleton
     */
    public static ControleurReservationsAchats getInstance() {
        if (instance == null) {
            instance = new ControleurReservationsAchats();
        }
        return instance;
    }
    
    private ControleurReservationsAchats() {
        controleur = Controleur.getInstance();
    }
    
    private boolean verifiePlaceDisponible(Representation representation, ArrayList<Place> places) {
        for (Place place : places) {
            if (!representation.salle.getAllPlace().contains(place) || representation.getPlacesReserver().contains(place)
                    || representation.getPlacesAcheter().contains(place)) {
                return false;
            }
        }
        return true;
    }

    public void annuleResevation(Reservation reservation) {
        if (controleur.verifieTypeCompte(TypeCompte.Client) && controleur.verifieNotNull(reservation) && reservation.compte == controleur.currentUser) {
            reservation.libereAll();
            controleur.defaultClient();
        }
    }

    public void choixPlaces(Representation representation, boolean isAchat) {
        if (controleur.verifieTypeCompte(TypeCompte.Client) && representation != null) {
            ArrayList<Place> occupe = representation.getPlacesAcheter();
            occupe.addAll(representation.getPlacesReserver());
            InterfaceGraphique.getInstance().setVuePrincipale(new VueChoixPlaces(representation, occupe, isAchat));
        }
    }

    public void voirRecut(Dossier dossier) {
        if (dossier != null && controleur.verifieTypeCompte(TypeCompte.Client) && controleur.currentUser == dossier.compte) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueRecutAchat(dossier));
        }
    }

    public void listeReservations() {
        if (controleur.verifieTypeCompte(TypeCompte.Client)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueReservations(controleur.currentUser.getReservations()));
        }
    }

    public void listeAchats() {
        if (controleur.verifieTypeCompte(TypeCompte.Client)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueAchats(controleur.currentUser.achats));
        }
    }

    public void preAchat(Representation representation, ArrayList<Place> places) {
        if (controleur.verifieNotNull(representation, places) && places.size() >= 1) {
            preAchat(representation, places, null);
        }
    }

    public void preAchat(Reservation reservation) {
        if (controleur.verifieNotNull(reservation) && reservation.compte == controleur.currentUser) {
            ArrayList<Place> places = reservation.places;
            Representation representation = reservation.representation;
            preAchat(representation, places, reservation);
        }
    }

    private void preAchat(Representation representation, ArrayList<Place> places, Reservation reservation) {
        if (controleur.verifieTypeCompte(TypeCompte.Client)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VuePreAchat(representation, places, reservation));
        }
    }

    public void achatDirect(Representation representation, ArrayList<Place> places) {
        if (controleur.verifieTypeCompte(TypeCompte.Client) && controleur.verifieNotNull(places) && places.size() >= 1 && verifiePlaceDisponible(representation, places) && new Date().getTime() <= representation.date.getTime()) {
            Dossier d = controleur.modele.createDossier(controleur.currentUser, places, representation);
            InterfaceGraphique.getInstance().setVuePrincipale(new VueRecutAchat(d));
        }
    }

    public void achatReservation(Reservation reservation) {
        if (controleur.verifieTypeCompte(TypeCompte.Client) && controleur.verifieNotNull(reservation) && reservation.compte == controleur.currentUser && new Date().getTime() <= reservation.representation.date.getTime() && reservation.representation.getReservations().contains(reservation)) {
            @SuppressWarnings("unchecked")
            ArrayList<Place> places = (ArrayList<Place>) reservation.places.clone();
            Representation representation = reservation.representation;
            reservation.libereAll();
            Dossier d = controleur.modele.createDossier(controleur.currentUser, places, representation);
            InterfaceGraphique.getInstance().setVuePrincipale(new VueRecutAchat(d));
        }
    }

    // Client
    public void reservePlace(Representation representation, ArrayList<Place> places) {
        if (controleur.verifieTypeCompte(TypeCompte.Client) && controleur.verifieNotNull(representation, places) && places.size() >= 1 && verifiePlaceDisponible(representation, places)) {
            try {
                new Reservation(controleur.currentUser, places, representation);
                controleur.defaultClient();
            } catch (ReservationImpossible ex) {
                // Reservation impossible
                // CurrenteDate + h >= dateResa
            }
        }
    }
}
