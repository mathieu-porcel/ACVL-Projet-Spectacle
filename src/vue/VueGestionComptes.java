package vue;

import java.util.List;

import controleur.ControleurCompte;
import modele.Compte;

@SuppressWarnings("serial")
public class VueGestionComptes extends AbstractVuePrincipale {
    public VueGestionComptes(List<Compte> comptes) {
        addTitre("Ajouter un responsable : ");
        addBouton("Cr�er", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueInscription()));
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
