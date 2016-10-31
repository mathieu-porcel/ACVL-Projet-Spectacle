
package modele;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Place implements Serializable {
    
    public int rang;
    public int numero;
    public Zone zone;

    public Place(Zone zone, int rang, int numero) {
        this.rang = rang;
        this.numero = numero;
        this.zone = zone;
    }

}
