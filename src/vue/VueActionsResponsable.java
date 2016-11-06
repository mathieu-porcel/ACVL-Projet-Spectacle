package vue;

import controleur.ControleurCompte;
import controleur.ControleurGestionModele;
import controleur.ControleurSpectacle;
import modele.Compte;

@SuppressWarnings("serial")
public class VueActionsResponsable extends AbstractVueActions {
    public VueActionsResponsable(Compte compte, boolean isAdmin) {
        super(compte, isAdmin);
        addAction("Gestion des spectacles", () -> ControleurSpectacle.getInstance().gestionSpectacles());
        addAction("Afficher statistiques", () -> ControleurGestionModele.getInstance().showStatistiques());
        addAction("Déconnexion", () -> ControleurCompte.getInstance().deconnection());
    }
}
