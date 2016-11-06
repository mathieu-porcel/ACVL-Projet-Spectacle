package vue;

import controleur.ControleurCompte;
import controleur.ControleurReservationsAchats;
import controleur.ControleurSpectacle;
import modele.Compte;

@SuppressWarnings("serial")
public class VueActionsClient extends AbstractVueActions {
    public VueActionsClient(Compte compte, boolean isAdmin) {
        super(compte, isAdmin);
        addAction("Liste des repr�sentations", () -> ControleurSpectacle.getInstance().listeRepresentations());
        addAction("Achats", () -> ControleurReservationsAchats.getInstance().listeAchats());
        addAction("R�servations", () -> ControleurReservationsAchats.getInstance().listeReservations());
        addAction("D�connexion", () -> ControleurCompte.getInstance().deconnection());
    }
}