package vue;

import controleur.ControleurReservationsAchats;
import java.text.SimpleDateFormat;
import java.util.Collection;

import modele.Dossier;

@SuppressWarnings("serial")
public class VueAchats extends AbstractVuePrincipale {
    public VueAchats(Collection<Dossier> dossiers) {
        for (Dossier dossier : dossiers) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH");
            addTexte(dossier.representation.spectacle.nom + " à " + dateFormat.format(dossier.representation.date) + "H");
            addBouton("Voir le reçu", () -> ControleurReservationsAchats.getInstance().voirRecut(dossier));
            newLigne();
        }
    }
}
