package vue;

import controleur.Controleur;
import modele.Compte;

@SuppressWarnings("serial")
public class VueActionsAdmin extends AbstractVueActions {
    public VueActionsAdmin(Compte compte, boolean isAdmin) {
        super(compte, isAdmin);
        addAction("Gestions des comptes", () -> Controleur.getInstance().gestionCompte());
        addAction("Modifier les tarifs", () -> Controleur.getInstance().editTarifs());
        addAction("Modifier la salle", () -> Controleur.getInstance().editSalle());
        addAction("Archiver et vider la base de donnee", () -> Controleur.getInstance().archiver());
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
