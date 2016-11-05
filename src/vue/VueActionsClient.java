package vue;

import controleur.Controleur;
import controleur.ControleurReservationsAchats;
import modele.Compte;

@SuppressWarnings("serial")
public class VueActionsClient extends AbstractVueActions {
    public VueActionsClient(Compte compte, boolean isAdmin) {
        super(compte, isAdmin);
        addAction("Liste des representations", () -> Controleur.getInstance().listeRepresentations());
        addAction("Achats", () -> ControleurReservationsAchats.getInstance().listeAchats());
        addAction("Reservations", () -> ControleurReservationsAchats.getInstance().listeReservations());
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}