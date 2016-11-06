package vue;

import java.text.SimpleDateFormat;
import java.util.Collection;

import controleur.ControleurReservationsAchats;
import modele.Reservation;

@SuppressWarnings("serial")
public class VueReservations extends AbstractVuePrincipale {
    public VueReservations(Collection<Reservation> reservations) {
        for (Reservation reservation : reservations) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH");
            addTexte(reservation.representation.spectacle.nom + " à " + dateFormat.format(reservation.representation.date) + "H");
            addTexte("(" + reservation.getPrix() + "€)");
            addBouton("Acheter", () -> ControleurReservationsAchats.getInstance().preAchat(reservation));
            addBouton("Annuler", () -> ControleurReservationsAchats.getInstance().annuleResevation(reservation));
            newLigne();
        }
    }
}
