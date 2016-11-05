
package controleur;

public class ControleurReservationsAchats {
    
    private static ControleurReservationsAchats instance;
    
    /**
     * Singleton
     */
    public static ControleurReservationsAchats getInstance() {
        if (instance == null) {
            instance = new ControleurReservationsAchats();
        }
        return instance;
    }
    
    private ControleurReservationsAchats() {}
}
