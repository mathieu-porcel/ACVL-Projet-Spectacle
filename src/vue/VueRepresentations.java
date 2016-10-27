package vue;

import java.text.SimpleDateFormat;
import java.util.List;

import modele.Representation;

@SuppressWarnings("serial")
public abstract class VueRepresentations extends VuePrincipale {
    public VueRepresentations(List<Representation> representations) {
        for (Representation representation : representations) {
            addTexte(representation.spectacle.nom);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy HH");
            addTexte(dateFormat.format(representation.date) + "H");
            addBouton("Acheter", () -> System.out.println("Achat: " + representation));
            addBouton("Réserver", () -> System.out.println("Réservation: " + representation));
            newLigne();
        }
    }
}
