
package modele;

import java.util.ArrayList;

public class Zone {

    public int numero;
    
    public Categorie categorie;
    public ArrayList<Place> places;
    
    public Zone(int numero, Categorie categorie) {
        this.numero = numero;
        this.categorie = categorie;
    }
    

    public void addPlace(int rang, int numero){
        //TODO
    }
}
