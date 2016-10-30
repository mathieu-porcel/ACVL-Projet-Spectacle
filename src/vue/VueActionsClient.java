package vue;

import controleur.Controleur;

@SuppressWarnings("serial")
public class VueActionsClient extends AbstractVueActions {
    public VueActionsClient() {
        addAction("Liste des representations", () -> Controleur.getInstance().listeRepresentations());
        addAction("Achats", () -> Controleur.getInstance().listeAchats());
        addAction("R�servations", () -> Controleur.getInstance().listeReservations());
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
