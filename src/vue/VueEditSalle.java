package vue;

import java.util.ArrayList;
import java.util.List;

import modele.Place;
import modele.Zone;

@SuppressWarnings("serial")
public abstract class VueEditSalle extends AbstractVuePrincipale {
    public VueEditSalle(List<Zone> zones) {
        updateZones(zones);
    }

    public void updateZones(List<Zone> zones) {
        // TODO Link controller + categorie
        clear();

        addTitre("Nouvelle zone:");
        addTexte("Categorie");
        ArrayList<String> categories = new ArrayList<>();
        categories.add("test1");
        categories.add("test2");
        addListe(categories);
        addBouton("Creer", () -> {
            Zone zone = new Zone(zones.size(), null);
            zone.addRang();
            zone.addNumero(0);
            zones.add(zone);
            updateZones(zones);
        });
        newLigne();

        addTitre("Liste des zones:");
        newLigne();
        for (Zone zone : zones) {
            addTexte("");
            addTexte("Zone n°" + (zone.numero+1) + ":");
            addTexte("Categorie");
            addTexte("TODO");
            newLigne();
            for (int rang = 0; rang < zone.places.size(); rang++) {
                addTexte("");
                addTexte("");
                addTexte("Rang n°" + (rang + 1) + " (" + zone.places.get(rang).size() + " places)");
                final int r = rang;
                addBouton("Ajouter place", () -> {
                    zone.places.get(r).add(new Place(0, 0));
                    updateZones(zones);
                });
                newLigne();
            }
            addTexte("");
            addTexte("");
            addBouton("Ajouter rang", () -> {
                zone.addRang();
                updateZones(zones);
            });
            newLigne();
        }
    }

}
