package vue;

import java.util.ArrayList;

import controleur.Controleur;

@SuppressWarnings("serial")
public class VueActionsClient extends AbstractVueActions {
    public VueActionsClient() {
        addAction("Liste des representations", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueRepresentations(new ArrayList<>()) {}));
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
