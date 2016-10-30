
package vue;

import controleur.Controleur;
import java.util.ArrayList;
import java.util.function.Supplier;
import modele.Place;
import modele.Representation;
import modele.Zone;

public class VueChoixPlaces extends AbstractVuePrincipale {
    public VueChoixPlaces(Representation representation, ArrayList<Place> placesOccupe, boolean isAchat){
        ArrayList<Supplier<Boolean>> checkboxs = new ArrayList();
        ArrayList<Place> places = new ArrayList();
        for (Zone zone : representation.salle.zones.values()){
            addTexte(zone.categorie.nom +" ("+zone.categorie.tarif+") :");
            newLigne();
            for(ArrayList<Place> p1 : zone.places){
                addTexte("");
                for(Place p2 : p1){
                    if (placesOccupe.contains(p2)){
                        addCheckboxLock();
                    } else {
                        checkboxs.add(addCheckbox());
                        places.add(p2);
                    }
                }
                newLigne();
            }
            newLigne();
        }
        addBouton("Valider", () -> {
            ArrayList<Place> val = new ArrayList();
            int i=0;
            for (Supplier<Boolean> b : checkboxs){
                if (b.get()){
                    val.add(places.get(i));
                }
                i++;
            }
            if (isAchat){
                Controleur.getInstance().achatDirect(representation, val);
            } else {
                Controleur.getInstance().reservePlace(representation, val);                
            }
        });
    }
    
}
