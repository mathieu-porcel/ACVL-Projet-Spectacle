
package vue;

import java.text.SimpleDateFormat;

import controleur.ControleurReservationsAchats;
import modele.AchatPlace;
import modele.Dossier;

@SuppressWarnings("serial")
public class VueRecutAchat extends AbstractVuePrincipale {
    public VueRecutAchat(Dossier dossier) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH");
        addTexte("Reçu pour le spectacle " + dossier.representation.spectacle.nom + " n°" + dossier.representation.spectacle.numero + " du "
                + dateFormat.format(dossier.representation.date) + "H");
        newLigne();
        addTexte("Dossier n°" + dossier.numero);
        newLigne();
        addTexte("Place achetée : ");
        newLigne();
        for (AchatPlace p : dossier.places) {
            addTexte("");
            addTexte("Place de la zone " + p.place.zone.numero + ", rang " + p.place.rang + ", numéro " + p.place.numero + ", au tarif : " + p.getPrix()
                    + "€ (n°" + p.numero + ")");
            newLigne();
        }
        addTexte("Prix total : " + dossier.getPrix() + "€");
        newLigne();
        dateFormat = new SimpleDateFormat("dd/MM/yy");
        addTexte("Payée le " + dateFormat.format(dossier.places.get(0).date) + " par " + dossier.compte.nom + " " + dossier.compte.prenom);
        newLigne();
        addBouton("OK", () -> ControleurReservationsAchats.getInstance().listeAchats());
    }
}
