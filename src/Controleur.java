import vue.InterfaceGraphique;
import vue.VueActionsNonConnecte;

public class Controleur {

    private static Controleur instance;

    /**
     * Singleton
     */
    public static Controleur getInstance() {
        if (instance == null) {
            instance = new Controleur();
        }
        return instance;
    }

    private Controleur() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsNonConnecte());
    }
}
