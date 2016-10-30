package vue;

import modele.Compte;

@SuppressWarnings("serial")
public class VueActionsNonConnecte extends AbstractVueActions {
    public VueActionsNonConnecte(Compte compte, boolean isAdmin) {
        super(compte, isAdmin);
        addAction("Connexion", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueConnexion()));
        addAction("Inscription", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueInscription()));
    }
}
