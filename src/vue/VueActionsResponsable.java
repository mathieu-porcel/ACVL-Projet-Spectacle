package vue;

import controleur.Controleur;

@SuppressWarnings("serial")
public class VueActionsResponsable extends AbstractVueActions {
    public VueActionsResponsable() {
        addAction("Gestion des spectacles", () -> Controleur.getInstance().gestionSpectacles());
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
