package vue;

import java.util.function.Supplier;

@SuppressWarnings("serial")
public class VueConnexion extends VuePrincipale {
    public VueConnexion() {
        addTexte("Login");
        Supplier<String> login = addChampTexte();
        newLigne();

        addTexte("Mot de passe");
        Supplier<String> motDePasse = addChampMotDePasse();
        newLigne();

        addTexte("");
        addBouton("Connexion", () -> {
            System.out.println("Connexion: " + login.get() + " " + motDePasse.get());
        });
    }
}
