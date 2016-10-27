
package modele;

import java.util.ArrayList;

public class Zone {

    public int numero;
    
    public Categorie categorie;
    public ArrayList<ArrayList<Place>> places;
    
    public Zone(int numero, Categorie categorie) {
        this.numero = numero;
        this.categorie = categorie;
    }
    

    public void addPlace(int rang, int numero){
        while (rang>places.size()){
            places.add(new ArrayList());
        }
        places.get(rang).add(numero, new Place(rang, numero));
    }
}
