package vue;

import java.util.Collection;
import java.util.function.Supplier;

import controleur.ControleurSalle;
import modele.Categorie;

@SuppressWarnings("serial")
public class VueEditTarifs extends AbstractVuePrincipale {

    public VueEditTarifs(Collection<Categorie> categories) {
        addTitre("Nouvelle catégorie : ");
        newLigne();
        addTexte("");
        addTexte("Nom");
        Supplier<String> nom = addChampTexte();
        newLigne();
        addTexte("");
        addTexte("Prix");
        Supplier<String> tarif = addChampTexte();
        newLigne();
        addTexte("");
        addTexte("");
        addBouton("Créer", () -> ControleurSalle.getInstance().addCategorie(nom.get(), tarif.get()));
        newLigne();

        addTitre("Liste des catégories : ");
        newLigne();

        for (Categorie categorie : categories) {
            addTexte("");
            addTexte(categorie.nom);
            addTexte("(" + categorie.tarif + "€)");
            newLigne();
        }
    }
}
