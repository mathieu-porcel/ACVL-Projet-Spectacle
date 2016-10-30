package vue;

import controleur.Controleur;

@SuppressWarnings("serial")
public class VueActionsAdmin extends AbstractVueActions {
    public VueActionsAdmin() {
        addAction("Gestions des comptes", () -> Controleur.getInstance().gestionCompte());
        addAction("Modifier les tarifs", () -> Controleur.getInstance().editTarifs());
        addAction("Modifier la salle", () -> Controleur.getInstance().editSalle());
        addAction("Archiver la base de donnee", () -> {/* TODO */});
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
