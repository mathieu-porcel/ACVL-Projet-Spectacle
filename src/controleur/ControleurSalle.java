
package controleur;

public class ControleurSalle {
    
    private static ControleurSalle instance;
    
    /**
     * Singleton
     */
    public static ControleurSalle getInstance() {
        if (instance == null) {
            instance = new ControleurSalle();
        }
        return instance;
    }
    
    private ControleurSalle() {}
}
