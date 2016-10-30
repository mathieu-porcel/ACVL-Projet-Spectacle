package vue;

import controleur.Controleur;

@SuppressWarnings("serial")
public class VueActionsClient extends AbstractVueActions {
    public VueActionsClient() {
        addAction("Liste des representations", () -> Controleur.getInstance().listeRepresentations());
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
