package vue;

import controleur.Controleur;
import modele.Compte;

@SuppressWarnings("serial")
public class VueActionsClient extends AbstractVueActions {
    public VueActionsClient(Compte compte, boolean isAdmin) {
        super(compte, isAdmin);
        addAction("Liste des representations", () -> Controleur.getInstance().listeRepresentations());
        addAction("Achats", () -> Controleur.getInstance().listeAchats());
        addAction("Réservations", () -> Controleur.getInstance().listeReservations());
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
