package vue;

import java.util.List;

import modele.Compte;

@SuppressWarnings("serial")
public class VueGestionComptes extends AbstractVuePrincipale {
    public VueGestionComptes(List<Compte> comptes) {
        addTitre("Ajouter un nouveau compte:");
        addBouton("Creer un compte responsable", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueInscription()));
        newLigne();

        addTitre("Liste des comptes:");
        newLigne();
        for (Compte compte : comptes) {
            addTexte("");
            addTexte("[" + compte.type + "]");
            addTexte(compte.nom);
            addTexte(compte.prenom);
            addTexte("(" + compte.login + ")");
            addBouton("Usurper", () -> controleur.Controleur.getInstance().usurper(compte));
            newLigne();
        }
    }
}
