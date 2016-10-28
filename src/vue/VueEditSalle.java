package vue;

import java.util.Collection;
import java.util.function.Supplier;

import Controleur.Controleur;
import modele.Categorie;
import modele.Zone;

@SuppressWarnings("serial")
public class VueEditSalle extends AbstractVuePrincipale {
    public VueEditSalle(Collection<Zone> zones, Collection<Categorie> categories) {
        addTitre("Nouvelle zone:");
        addTexte("Categorie");
        Supplier<?> categorie = addListe(categories);
        addBouton("Creer", () -> Controleur.getInstance().addZone((Categorie) categorie.get()));
        newLigne();

        addTitre("Liste des zones:");
        newLigne();
        for (Zone zone : zones) {
            addTexte("");
            addTexte("Zone n°" + (zone.numero + 1) + ":");
            addTexte(zone.categorie.nom);
            newLigne();
            for (int i = 0; i < zone.places.size(); i++) {
                int rang = i;
                addTexte("");
                addTexte("");
                addTexte("Rang n°" + (rang + 1) + " (" + zone.places.get(rang).size() + " places)");
                addBouton("Ajouter place", () -> Controleur.getInstance().addNumero(zone, rang));
                newLigne();
            }
            addTexte("");
            addTexte("");
            addBouton("Ajouter rang", () -> Controleur.getInstance().addRang(zone));
            newLigne();
        }
    }
}
