package vue;

import java.util.ArrayList;

@SuppressWarnings("serial")
public class VueActionsAdmin extends VueActions {
    public VueActionsAdmin() {
        addAction("Gestions des comptes", () -> {});
        addAction("Modifier la salle", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueEditSalle(new ArrayList<>()) {}));
        addAction("Modifier les tarifs", () -> {});
        addAction("Archiver la base de donnee", () -> {});
        addAction("Deconnexion", () -> Controleur.Controleur.getInstance().deconnection());
    }
}
