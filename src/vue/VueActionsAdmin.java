package vue;

import java.util.ArrayList;

import Controleur.Controleur;
import modele.Compte;
import modele.TypeComte;

@SuppressWarnings("serial")
public class VueActionsAdmin extends AbstractVueActions {
    public VueActionsAdmin() {
        // TODO: use controller
        addAction("Gestions des comptes", () -> Controleur.getInstance().gestionCompte());
        addAction("Modifier la salle", () -> Controleur.getInstance().editSalle());
        addAction("Modifier les tarifs", () -> Controleur.getInstance().editTarifs());
        addAction("Archiver la base de donnee", () -> {});
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
