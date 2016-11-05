
package controleur;

public class ControleurSalle {
    
    private static ControleurSalle instance;
    
    private Controleur controleur;
    
    /**
     * Singleton
     */
    public static ControleurSalle getInstance() {
        if (instance == null) {
            instance = new ControleurSalle();
        }
        return instance;
    }
    
    private ControleurSalle() {
        controleur = Controleur.getInstance();
    }
}
