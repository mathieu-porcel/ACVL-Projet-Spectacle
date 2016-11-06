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
            addTexte(spectacle.representations.size() + " repr�sentations,");
            addTexte(spectacle.getPlacesAchetees() + " places vendues,");
            addTexte("b�n�fice total de " + spectacle.getBenefices() + "�");
            newLigne();
        }
    }
}
