package vue;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.function.Supplier;

import controleur.Controleur;
import modele.Representation;
import modele.Spectacle;

@SuppressWarnings("serial")
public class VueGestionSpecacles extends AbstractVuePrincipale {
    public VueGestionSpecacles(Collection<Spectacle> spectacles) {
        addTitre("Nouveau spectacle:");
        addTexte("Nom");
        Supplier<String> nomSepctacle = addChampTexte();
        addBouton("Creer", () -> Controleur.getInstance().addSpectacle(nomSepctacle.get()));
        newLigne();

        addTitre("Liste des spectacles");
        newLigne();
        for (Spectacle spectacle : spectacles) {
            addTexte("");
            addTexte(spectacle.nom);
            addTexte("(n�" + spectacle.numero + ")");
            addBouton("Nouvelle representation", () -> Controleur.getInstance().addRepresentation(spectacle, new Date())); // TODO: date
            newLigne();

            for (Representation representation : spectacle.representations) {
                addTexte("");
                addTexte("");
                addTexte("Representation");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy HH");
                addTexte("(" + dateFormat.format(representation.date) + "H)");
                addTexte(representation.getPlacesReserver().size() + " places reservee et "+representation.getPlacesAcheter().size()+" achetees");
                newLigne();
            }
        }
    }
}
