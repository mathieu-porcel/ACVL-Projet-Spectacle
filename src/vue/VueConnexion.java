package vue;

import java.util.function.Supplier;

import controleur.Controleur;
import controleur.ControleurCompte;

@SuppressWarnings("serial")
public class VueConnexion extends AbstractVuePrincipale {
    public VueConnexion() {
        addTexte("Login");
        Supplier<String> login = addChampTexte();
        newLigne();

        addTexte("Mot de passe");
        Supplier<String> motDePasse = addChampMotDePasse();
        newLigne();

        addTexte("");
        addBouton("Connexion", () -> ControleurCompte.getInstance().connection(login.get(), motDePasse.get()));
    }
}
