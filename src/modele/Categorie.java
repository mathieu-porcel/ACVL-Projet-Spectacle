
package modele;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Categorie implements Serializable {
    
    public String nom;
    public float tarif;

    public Categorie(String nom, float tarif) {
        this.nom = nom;
        this.tarif = tarif;
    }

    @Override
    public String toString() {
        return nom;
    }
}
