package vue;

import controleur.Controleur;
import modele.Compte;

@SuppressWarnings("serial")
public class VueActionsResponsable extends AbstractVueActions {
    public VueActionsResponsable(Compte compte, boolean isAdmin) {
        super(compte, isAdmin);
        addAction("Gestion des spectacles", () -> Controleur.getInstance().gestionSpectacles());
        addAction("Afficher statistiques", () -> Controleur.getInstance().showStatistiques());
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
