package vue;

public class Interface {

    private static Interface instance;

    public static Interface getInstance() {
        if (instance == null) {
            instance = new Interface();
        }
        return instance;
    }

    private Interface() {

    }
}
