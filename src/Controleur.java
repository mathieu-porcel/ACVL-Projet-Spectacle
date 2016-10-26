
public class Controleur {

    private static Controleur instance;

    public static Controleur getInstance() {
        if (instance == null) {
            instance = new Controleur();
        }
        return instance;
    }

    private Controleur() {

    }
}
