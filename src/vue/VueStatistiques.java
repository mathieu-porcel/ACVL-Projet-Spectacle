package vue;

import modele.Modele;
import modele.Spectacle;

@SuppressWarnings("serial")
public class VueStatistiques extends AbstractVuePrincipale {
    public VueStatistiques(Modele modele) {
        addTitre("Liste des spectacles");
        newLigne();
        for (Spectacle spectacle : modele.spectacles.values()) {
            addTexte("");
            addTexte(spectacle.nom + ": ");
            addTexte(spectacle.representations.size() + " représentations,");
            addTexte(spectacle.getPlacesAchetees() + " places vendues,");
            addTexte("bénéfice total de " + spectacle.getBenefices() + "€");
            newLigne();
        }
    }
}
