
package controleur;

public class ControleurSpectacle {
    
    private static ControleurSpectacle instance;
    
    /**
     * Singleton
     */
    public static ControleurSpectacle getInstance() {
        if (instance == null) {
            instance = new ControleurSpectacle();
        }
        return instance;
    }
    
    private ControleurSpectacle() {}
    
}
