
package controleur;

public class ControleurCompte {
    
    private static ControleurCompte instance;
    
    /**
     * Singleton
     */
    public static ControleurCompte getInstance() {
        if (instance == null) {
            instance = new ControleurCompte();
        }
        return instance;
    }
    
    private ControleurCompte() {}
    
}
