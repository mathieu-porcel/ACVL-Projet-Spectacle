package vue;

import java.text.SimpleDateFormat;
import java.util.Collection;

import controleur.Controleur;
import modele.Reservation;

@SuppressWarnings("serial")
public class VueReservations extends AbstractVuePrincipale {
    public VueReservations(Collection<Reservation> reservations) {
        // TODO: controleur + verification representation expiree
        for (Reservation reservation : reservations) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH");
            addTexte(reservation.representation.spectacle.nom + " a " + dateFormat.format(reservation.representation.date) + "H");
            addTexte("(" + reservation.getPrix() + "�)");
            addBouton("Acheter", () -> Controleur.getInstance().preAchat(reservation));
            addBouton("Annuler", () -> Controleur.getInstance().annuleResevation(reservation));
            newLigne();
        }
    }
}
