
package controleur;

import modele.Categorie;
import modele.TypeCompte;
import modele.Zone;
import vue.InterfaceGraphique;
import vue.VueEditSalle;
import vue.VueEditTarifs;

public class ControleurSalle {
    
    private static ControleurSalle instance;
    
    private Controleur controleur;
    
    /**
     * Singleton
     */
    public static ControleurSalle getInstance() {
        if (instance == null) {
            instance = new ControleurSalle();
        }
        return instance;
    }
    
    private ControleurSalle() {
        controleur = Controleur.getInstance();
    }

    public void addCategorie(String nom, String prix) {
        try {
            float tarif = Float.parseFloat(prix);
            if (controleur.verifieTypeCompte(TypeCompte.Admin) && nom != null && tarif > 0) {
                controleur.modele.addCategorie(nom, tarif);
                editTarifs();
            }
        } catch (NumberFormatException e) {
        }
    }

    public void editTarifs() {
        if (controleur.verifieTypeCompte(TypeCompte.Admin)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueEditTarifs(controleur.modele.categories.values()));
        }
    }

    public void addZone(Categorie categorie) {
        if (controleur.verifieTypeCompte(TypeCompte.Admin) && categorie != null) {
            controleur.modele.addZone(categorie);
            editSalle();
        }
    }

    // Admin
    public void editSalle() {
        if (controleur.verifieTypeCompte(TypeCompte.Admin)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueEditSalle(controleur.modele.salle.zones.values(), controleur.modele.categories.values()));
        }
    }

    public void addRang(Zone zone) {
        if (controleur.verifieTypeCompte(TypeCompte.Admin) && zone != null) {
            zone.addRang();
            editSalle();
        }
    }

    public void addNumero(Zone zone, int rang) {
        if (controleur.verifieTypeCompte(TypeCompte.Admin) && zone != null && zone.places.size() > rang) {
            zone.addNumero(rang);
            editSalle();
        }
    }
}
