package vue;

import java.text.SimpleDateFormat;
import java.util.Collection;

import modele.Representation;
import modele.Spectacle;

@SuppressWarnings("serial")
public class VueRepresentations extends AbstractVuePrincipale {
    public VueRepresentations(Collection<Spectacle> spectacles) {
        for (Spectacle spectacle : spectacles) {
            for (Representation representation : spectacle.representations) {
                addTexte(representation.spectacle.nom);
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy HH");
                addTexte(dateFormat.format(representation.date) + "H");
                // TODO controleur
                addBouton("Acheter", () -> {});
                addBouton("Reserver", () -> {});
                newLigne();
            }
        }
    }
}
