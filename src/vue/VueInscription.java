package vue;

import java.util.function.Supplier;

@SuppressWarnings("serial")
public class VueInscription extends VuePrincipale {
    public VueInscription() {
        addTexte("Login");
        Supplier<String> login = addChampTexte();
        newLigne();

        addTexte("Nom");
        Supplier<String> nom = addChampTexte();
        newLigne();

        addTexte("Prénom");
        Supplier<String> prenom = addChampTexte();
        newLigne();

        addTexte("(Adresse mail)");
        Supplier<String> email = addChampTexte();
        newLigne();

        addTexte("Mot de passe");
        Supplier<String> motDePasse = addChampMotDePasse();
        newLigne();

        addTexte("");
        addBouton("Inscription",
                () -> System.out.println("Inscription: " + login.get() + " " + nom.get() + " " + prenom.get() + " " + email.get() + " " + motDePasse.get()));
    }
}
