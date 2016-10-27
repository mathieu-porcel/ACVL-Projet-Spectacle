package vue;

@SuppressWarnings("serial")
public class VueActionsNonConnecte extends VueActions {
    public VueActionsNonConnecte() {
        addAction("Connexion", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueConnexion()));
        addAction("Inscription", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueInscription()));
    }
}
