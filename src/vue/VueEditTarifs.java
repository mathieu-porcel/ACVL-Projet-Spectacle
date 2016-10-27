package vue;

import java.util.List;
import java.util.function.Supplier;

import modele.Categorie;

@SuppressWarnings("serial")
public class VueEditTarifs extends AbstractVuePrincipale {
    public VueEditTarifs(List<Categorie> categories) {
        updateCategories(categories);
    }

    public void updateCategories(List<Categorie> categories) {
        // TODO controller
        clear();

        addTitre("Nouvelle categorie:");
        newLigne();
        addTexte("");
        addTexte("Nom");
        Supplier<String> nom = addChampTexte();
        newLigne();
        addTexte("");
        addTexte("Prix");
        Supplier<String> prix = addChampTexte();
        newLigne();
        addTexte("");
        addTexte("");
        addBouton("Creer", () -> {
            categories.add(new Categorie(nom.get(), Float.parseFloat(prix.get())));
            updateCategories(categories);
        });
        newLigne();

        addTitre("Liste des categories:");
        newLigne();

        for (Categorie categorie : categories) {
            addTexte("");
            addTexte(categorie.nom);
            addTexte("(" + categorie.tarif + "€)");
            newLigne();
        }
    }
}
