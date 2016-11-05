package vue;

import java.util.Collection;
import java.util.function.Supplier;

import controleur.ControleurSalle;
import modele.Categorie;

@SuppressWarnings("serial")
public class VueEditTarifs extends AbstractVuePrincipale {

    public VueEditTarifs(Collection<Categorie> categories) {
        addTitre("Nouvelle categorie:");
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
        addBouton("Creer", () -> ControleurSalle.getInstance().addCategorie(nom.get(), tarif.get()));
        newLigne();

        addTitre("Liste des categories:");
        newLigne();

        for (Categorie categorie : categories) {
            addTexte("");
            addTexte(categorie.nom);
            addTexte("(" + categorie.tarif + "�)");
            newLigne();
        }
    }
}
