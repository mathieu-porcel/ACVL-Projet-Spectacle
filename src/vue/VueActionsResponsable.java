package vue;

import controleur.Controleur;

@SuppressWarnings("serial")
public class VueActionsResponsable extends AbstractVueActions {
    public VueActionsResponsable() {
        addAction("TODO", () -> {});
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
