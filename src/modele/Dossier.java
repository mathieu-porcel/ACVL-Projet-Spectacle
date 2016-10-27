
package modele;

import java.util.ArrayList;

public class Dossier {
    
    public int numero;
    public Compte compte;
    public Representation representation;
    public ArrayList<AchatPlace> places;

    public Dossier(int numero, Compte user, Representation representation, ArrayList<AchatPlace> places) {
        this.numero = numero;
        this.compte = user;
        this.representation = representation;
        this.places = places;
    }

}
