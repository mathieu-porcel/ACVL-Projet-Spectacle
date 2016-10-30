package vue;

import java.text.SimpleDateFormat;
import java.util.Collection;

import modele.Dossier;

@SuppressWarnings("serial")
public class VueAchats extends AbstractVuePrincipale {
    public VueAchats(Collection<Dossier> dossiers) {
        // TODO: controleur + prix + numero dossier + un ticket par place
        for (Dossier dossier : dossiers) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy HH");
            addTexte(dossier.representation.spectacle.nom + " a " + dateFormat.format(dossier.representation.date) + "H");
            newLigne();
        }
    }
}
