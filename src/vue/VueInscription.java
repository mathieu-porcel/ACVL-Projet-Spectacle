package vue;

import Controleur.Controleur;
import java.util.function.Supplier;

@SuppressWarnings("serial")
public class VueInscription extends AbstractVuePrincipale {
    public VueInscription() {
        addTexte("Login");
        Supplier<String> login = addChampTexte();
        newLigne();

        addTexte("Nom");
        Supplier<String> nom = addChampTexte();
        newLigne();

        addTexte("Prenom");
        Supplier<String> prenom = addChampTexte();
        newLigne();

        addTexte("(Adresse mail)");
        Supplier<String> email = addChampTexte();
        newLigne();

        addTexte("Mot de passe");
        Supplier<String> motDePasse = addChampMotDePasse();
        newLigne();

        addTexte("");
        addBouton("Inscription", () -> Controleur.getInstance().inscription(login.get(), nom.get(), prenom.get(), email.get(), motDePasse.get()));
    }
}
