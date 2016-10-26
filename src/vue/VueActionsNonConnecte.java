package vue;

@SuppressWarnings("serial")
public class VueActionsNonConnecte extends VueActions {
    public VueActionsNonConnecte() {
        addAction("Connexion", () -> {});
        addAction("Inscription", () -> {});
    }
}
