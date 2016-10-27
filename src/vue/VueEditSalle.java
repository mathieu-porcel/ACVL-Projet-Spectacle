package vue;

import java.util.List;

import modele.Place;
import modele.Zone;

@SuppressWarnings("serial")
public abstract class VueEditSalle extends VuePrincipale {
    public VueEditSalle(List<Zone> zones) {
        updateZones(zones);
    }

    public void updateZones(List<Zone> zones) {
        // TODO Link controller + categorie, tarif
        clear();
        for (Zone zone : zones) {
            addTexte("Zone n°" + zone.numero + ":");
            addTexte("Categorie");
            addTexte("TODO");
            newLigne();
            for (int rang = 0; rang < zone.places.size(); rang++) {
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
            addBouton("Ajouter rang", () -> {
                zone.addRang();
                updateZones(zones);
            });
            newLigne();
        }

        addBouton("Ajouter zone", () -> {
            Zone zone = new Zone(zones.size(), null);
            zone.addRang();
            zone.addNumero(0);
            zones.add(zone);
            updateZones(zones);
        });
    }

}
