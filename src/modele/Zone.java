package modele;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Zone implements Serializable {

    public int numero;

    public Categorie categorie;
    public ArrayList<ArrayList<Place>> places;

    public Zone(int numero, Categorie categorie) {
        this.numero = numero;
        this.categorie = categorie;
        this.places = new ArrayList<>();
    }

    public void addRang() {
        places.add(new ArrayList<Place>());
    }

    public void addNumero(int rang) {
        places.get(rang).add(new Place(this, rang, places.get(rang).size()+1));
    }

    public ArrayList<Place> getPlace() {
        ArrayList<Place> ret = new ArrayList<>();
        for (ArrayList<Place> p : places) {
            ret.addAll(p);
        }
        return ret;
    }
}
