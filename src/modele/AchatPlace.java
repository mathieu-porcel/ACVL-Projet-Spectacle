
package modele;

import java.util.Date;

public class AchatPlace {
    
    public int numero;
    public Date date;
    public Place place;

    public AchatPlace(int numero, Place place) {
        this.numero = numero;
        this.place = place;
        date = new Date();
    }
}
