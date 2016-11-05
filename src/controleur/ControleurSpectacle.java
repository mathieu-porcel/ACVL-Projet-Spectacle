
package controleur;

import java.util.Date;
import modele.Representation;
import modele.Spectacle;
import modele.TypeCompte;
import vue.InterfaceGraphique;
import vue.VueGestionSpecacles;
import vue.VueRepresentations;

public class ControleurSpectacle {
    
    private static ControleurSpectacle instance;
    
    private Controleur controleur;
    
    /**
     * Singleton
     */
    public static ControleurSpectacle getInstance() {
        if (instance == null) {
            instance = new ControleurSpectacle();
        }
        return instance;
    }
    
    private ControleurSpectacle() {
        controleur = Controleur.getInstance();
    }

    // Responsable
    public void gestionSpectacles() {
        if (controleur.verifieTypeCompte(TypeCompte.Responsable)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueGestionSpecacles(controleur.modele.spectacles.values()));
        }
    }

    public void annulerRepresentation(Representation representation) {
        if (controleur.verifieTypeCompte(TypeCompte.Responsable) && representation != null && new Date().getTime() <= representation.date.getTime()) {
            representation.annuler();
            gestionSpectacles();
        }
    }

    public void addSpectacle(String nom) {
        if (controleur.verifieTypeCompte(TypeCompte.Responsable) && !controleur.modele.spectacles.containsKey(nom)) {
            controleur.modele.addSpectacle(nom);
            gestionSpectacles();
        }
    }

    public void listeRepresentations() {
        if (controleur.verifieTypeCompte(TypeCompte.Client)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueRepresentations(controleur.modele.spectacles.values()));
        }
    }

    public void addRepresentation(Spectacle spectacle, String date) {
        if (controleur.verifieTypeCompte(TypeCompte.Responsable) && controleur.verifieNotNull(spectacle, date)) {
            Date d = controleur.stringToDateWithHour(date);
            if (d != null /* && new Date().getTime()<=d.getTime() */ ) {
                // TODO comment for test/dev. Uncommetn for prod
                spectacle.addRepresentation(d, controleur.modele.salle);
                gestionSpectacles();
            }
        }
    }
    
}
