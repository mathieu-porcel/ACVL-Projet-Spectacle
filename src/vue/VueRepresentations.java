package vue;

import java.text.SimpleDateFormat;
import java.util.List;

import modele.Representation;

@SuppressWarnings("serial")
public class VueRepresentations extends AbstractVuePrincipale {
    public VueRepresentations(List<Representation> representations) {
        for (Representation representation : representations) {
            addTexte(representation.spectacle.nom);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy HH");
            addTexte(dateFormat.format(representation.date) + "H");
            addBouton("Acheter", () -> System.out.println("Achat: " + representation));
            addBouton("Reserver", () -> System.out.println("Reservation: " + representation));
            newLigne();
        }
    }
}
