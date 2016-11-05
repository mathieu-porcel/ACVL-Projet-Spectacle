
package controleur;

public class ControleurCompte {
    
    private static ControleurCompte instance;
    
    private Controleur controleur;
    
    /**
     * Singleton
     */
    public static ControleurCompte getInstance() {
        if (instance == null) {
            instance = new ControleurCompte();
        }
        return instance;
    }
    
    private ControleurCompte() {
        controleur = Controleur.getInstance();
    }
    
}
