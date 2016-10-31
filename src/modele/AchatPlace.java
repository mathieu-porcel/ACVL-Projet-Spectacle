
package modele;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class AchatPlace implements Serializable {
    
    public int numero;
    public Date date;
    public Place place;

    public AchatPlace(int numero, Place place) {
        this.numero = numero;
        this.place = place;
        date = new Date();
    }
    
    public float getPrix(){
        return place.zone.categorie.tarif;
    }
}
