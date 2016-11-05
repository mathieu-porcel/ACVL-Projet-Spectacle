
package vue;

import controleur.Controleur;
import controleur.ControleurReservationsAchats;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import modele.Place;
import modele.Representation;
import modele.Reservation;

@SuppressWarnings("serial")
public class VuePreAchat extends AbstractVuePrincipale {
    public VuePreAchat(Representation representation, ArrayList<Place> places, Reservation reservation){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy à HH");
        addTexte("Achat pour le spectacle "+representation.spectacle.nom+ " n°"+representation.spectacle.numero+" du "+dateFormat.format(representation.date));
        newLigne();
        addTexte("Place souhaitée : ");
        newLigne();
        float somme = 0;
        for (Place p : places){
            addTexte("");
            addTexte("Place de la zone "+p.zone.numero+", rang "+p.rang+", numero "+p.numero+", au tarif : "+p.zone.categorie.tarif+"€");
            newLigne();
            somme += p.zone.categorie.tarif;
        }
        addTexte("Prix total : "+somme+"€");
        newLigne();
        newLigne();
        addBouton("Payer", () -> {
            if (reservation==null){
                ControleurReservationsAchats.getInstance().achatDirect(representation, places);
            } else {
                ControleurReservationsAchats.getInstance().achatReservation(reservation);
            }
        });
    }
}
