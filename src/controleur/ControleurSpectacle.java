
package controleur;

public class ControleurSpectacle {
    
    private static ControleurSpectacle instance;
    
    private Controleur controleur;
    
    /**
     * Singleton
     */
    public static ControleurSpectacle getInstance() {
        if (instance == null) {
            instance = new ControleurSpectacle();
        }
        return instance;
    }
    
    private ControleurSpectacle() {
        controleur = Controleur.getInstance();
    }
    
}
