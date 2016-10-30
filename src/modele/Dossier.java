
package modele;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Dossier implements Serializable {
    
    public int numero;
    public Compte compte;
    public Representation representation;
    public ArrayList<AchatPlace> places;

    public Dossier(int numero, Compte user, Representation representation, ArrayList<AchatPlace> places) {
        representation.achat(this);
        user.achat(this);
        this.numero = numero;
        this.compte = user;
        this.representation = representation;
        this.places = places;
    }

    public ArrayList<Place> getPlaces(){
        ArrayList<Place> placesRet = new ArrayList<>();
        for (AchatPlace a : places){
            placesRet.add(a.place);
        }
        return placesRet;
    }
}
