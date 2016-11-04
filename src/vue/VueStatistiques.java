package vue;

import modele.Modele;
import modele.Spectacle;

@SuppressWarnings("serial")
public class VueStatistiques extends AbstractVuePrincipale {
    public VueStatistiques(Modele modele) {
        addTexte("Nombre de spectacle creer: ");
        addTexte(String.valueOf(modele.spectacles.size()));
        newLigne();
        addTitre("Liste des spectacles");
        for (Spectacle spectacle : modele.spectacles.values()) {
            addTexte("");
            addTexte(spectacle.nom);
            newLigne();
        }
    }
}
