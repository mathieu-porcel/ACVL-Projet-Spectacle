
package controleur;

import java.util.ArrayList;
import modele.Compte;
import modele.TypeCompte;
import vue.InterfaceGraphique;
import vue.VueGestionComptes;

public class ControleurCompte {
    
    private static ControleurCompte instance;
    
    private Controleur controleur;
    
    /**
     * Singleton
     */
    public static ControleurCompte getInstance() {
        if (instance == null) {
            instance = new ControleurCompte();
        }
        return instance;
    }
    
    private ControleurCompte() {
        controleur = Controleur.getInstance();
    }
    
    public void connection(String login, String password) {
        if (controleur.verifieNotNull(login, password)) {
            Compte user = controleur.modele.getCompte(login);
            if (user != null && user.password.equals(password)) {
                controleur.currentUser = user;

                if (null != user.type)
                    switch (user.type) {
                        case Client:
                            controleur.defaultClient();
                            break;
                        case Responsable:
                            controleur.defaultResponsable();
                            break;
                        case Admin:
                            controleur.isAdmin = true;
                            controleur.defaultAdmin();
                            break;
                        default:
                            break;
                    }
            }
        }
    }

    public void inscription(String login, String nom, String prenom, String email, String password) {
        if (controleur.verifieNotNull(login, nom, prenom, password)) {
            if (!controleur.modele.comptes.containsKey(login)) {
                if (controleur.currentUser == null) {
                    Compte user = controleur.modele.addCompte(login, password, TypeCompte.Client, email, nom, prenom);
                    controleur.currentUser = user;
                    controleur.defaultClient();
                } else if (controleur.currentUser.type == TypeCompte.Admin) {
                    controleur.modele.addCompte(login, password, TypeCompte.Responsable, email, nom, prenom);
                    gestionCompte();
                }
            }
        }
    }

    public void deconnection() {
        if (controleur.isAdmin && controleur.currentUser.type != TypeCompte.Admin) {
            controleur.currentUser = controleur.modele.getCompte("admin");
            controleur.defaultAdmin();
        } else {
            controleur.isAdmin = false;
            controleur.currentUser = null;
            controleur.defaultNonConnecte();
        }
    }

    public void usurper(Compte usurpation) {
        if (controleur.verifieTypeCompte(TypeCompte.Admin) && usurpation != null && usurpation.type != TypeCompte.Admin) {
            controleur.currentUser = usurpation;
            if (controleur.verifieTypeCompte(TypeCompte.Client)) {
                controleur.defaultClient();
            } else if (controleur.verifieTypeCompte(TypeCompte.Responsable)) {
                controleur.defaultResponsable();
            }
        }
    }

    public void gestionCompte() {
        if (controleur.verifieTypeCompte(TypeCompte.Admin)) {
            ArrayList<Compte> comptes = new ArrayList<>();
            for (Compte c : controleur.modele.comptes.values()) {
                if (c.type != TypeCompte.Admin) {
                    comptes.add(c);
                }
            }
            InterfaceGraphique.getInstance().setVuePrincipale(new VueGestionComptes(comptes));
        }
    }
    
}
