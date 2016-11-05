package vue;

import controleur.ControleurCompte;
import controleur.ControleurGestionModele;
import controleur.ControleurSalle;
import modele.Compte;

@SuppressWarnings("serial")
public class VueActionsAdmin extends AbstractVueActions {
    public VueActionsAdmin(Compte compte, boolean isAdmin) {
        super(compte, isAdmin);
        addAction("Gestions des comptes", () -> ControleurCompte.getInstance().gestionCompte());
        addAction("Modifier les tarifs", () -> ControleurSalle.getInstance().editTarifs());
        addAction("Modifier la salle", () -> ControleurSalle.getInstance().editSalle());
        addAction("Archiver et vider la base de donnee", () -> ControleurGestionModele.getInstance().archiver());
        addAction("Deconnexion", () -> ControleurCompte.getInstance().deconnection());
    }
}
