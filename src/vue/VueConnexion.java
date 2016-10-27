package vue;

import Controleur.Controleur;
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
        addBouton("Connexion", () -> Controleur.getInstance().connection());
    }
}
