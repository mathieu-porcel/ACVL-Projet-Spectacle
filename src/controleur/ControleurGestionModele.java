
package controleur;

public class ControleurGestionModele {
    
    private static ControleurGestionModele instance;

    /**
     * Singleton
     */
    public static ControleurGestionModele getInstance() {
        if (instance == null) {
            instance = new ControleurGestionModele();
        }
        return instance;
    }
    
    private ControleurGestionModele() {}
}
