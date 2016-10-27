
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
    
    public void addRang(){
        places.add(new ArrayList());
    }
    
    public void addNumero(int rang){
        places.get(rang).add(new Place(rang, numero));
    }
}
