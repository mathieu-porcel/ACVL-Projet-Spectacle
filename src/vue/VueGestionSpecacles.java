package vue;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.function.Supplier;

import controleur.ControleurSpectacle;
import modele.Representation;
import modele.Spectacle;

@SuppressWarnings("serial")
public class VueGestionSpecacles extends AbstractVuePrincipale {
    public VueGestionSpecacles(Collection<Spectacle> spectacles) {
        addTitre("Nouveau spectacle : ");
        addTexte("Nom");
        Supplier<String> nomSepctacle = addChampTexte();
        addBouton("Cr�er", () -> ControleurSpectacle.getInstance().addSpectacle(nomSepctacle.get()));
        newLigne();

        addTitre("Liste des spectacles : ");
        newLigne();
        for (Spectacle spectacle : spectacles) {
            addTexte("");
            addTexte("n�" + spectacle.numero + " : ");
            addTexte(spectacle.nom);
            newLigne();

            addTexte("");
            addTexte("");
            Supplier<String> date = addChampDate();
            addBouton("Nouvelle repr�sentation", () -> ControleurSpectacle.getInstance().addRepresentation(spectacle, date.get()));
            newLigne();

            for (Representation representation : spectacle.representations) {
                addTexte("");
                addTexte("");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH");
                addTexte("Repr�sentation � " + (representation.isAnnuler ? "(annul�e) " : "") + dateFormat.format(representation.date) + "H");
                addTexte(representation.getPlacesReserver().size() + " places reserv�es et " + representation.getPlacesAchetees().size() + " achet�es");
                if (new Date().getTime() <= representation.date.getTime() && !representation.isAnnuler) {
                    addBouton("Annuler", () -> ControleurSpectacle.getInstance().annulerRepresentation(representation));
                }
                newLigne();
            }
        }
    }
}
