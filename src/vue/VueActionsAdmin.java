package vue;

@SuppressWarnings("serial")
public class VueActionsAdmin extends VueActions {
    public VueActionsAdmin() {
        addAction("Gestions des comptes", () -> {});
        addAction("Modifier la salle", () -> {});
        addAction("Modifier les tarifs", () -> {});
        addAction("Archiver la base de donnée", () -> {});
        addAction("Deconnexion", () -> {});
    }
}
