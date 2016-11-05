package vue;

import controleur.ControleurCompte;
import java.util.List;

import modele.Compte;

@SuppressWarnings("serial")
public class VueGestionComptes extends AbstractVuePrincipale {
    public VueGestionComptes(List<Compte> comptes) {
        addTitre("Ajouter un responsable:");
        addBouton("Creer", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueInscription()));
        newLigne();

        addTitre("Liste des comptes:");
        newLigne();
        for (Compte compte : comptes) {
            addTexte("");
            addTexte("[" + compte.type + "]");
            addTexte(compte.nom);
            addTexte(compte.prenom);
            addTexte("(" + compte.login + ")");
            addBouton("Usurper", () -> ControleurCompte.getInstance().usurper(compte));
            newLigne();
        }
    }
}
