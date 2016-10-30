package vue;

import java.text.SimpleDateFormat;
import java.util.Collection;
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
            addTexte("n°" + spectacle.numero + ":");
            addTexte(spectacle.nom);
            newLigne();

            addTexte("");
            addTexte("");
            Supplier<String> date = addChampDate();
            addBouton("Nouvelle representation", () -> Controleur.getInstance().addRepresentation(spectacle, date.get()));
            newLigne();

            for (Representation representation : spectacle.representations) {
                addTexte("");
                addTexte("");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH");
                addTexte("Representation à " + dateFormat.format(representation.date) + "H");
                addTexte(representation.getPlacesReserver().size() + " places reservee et " + representation.getPlacesAcheter().size() + " achetees");
                newLigne();
            }
        }
    }
}
