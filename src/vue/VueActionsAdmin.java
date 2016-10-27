package vue;

import java.util.ArrayList;

import Controleur.Controleur;
import modele.Compte;
import modele.TypeComte;

@SuppressWarnings("serial")
public class VueActionsAdmin extends AbstractVueActions {
    public VueActionsAdmin() {
        // TODO: use controller
        addAction("Gestions des comptes", () -> {
            // TODO: get users from model
            ArrayList<Compte> comptes = new ArrayList<>();
            comptes.add(new Compte("admin", "admin", TypeComte.Admin, "", "admin", "admin"));
            InterfaceGraphique.getInstance().setVuePrincipale(new VueGestionComptes(comptes));
        });
        addAction("Modifier la salle", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueEditSalle(new ArrayList<>()) {}));
        addAction("Modifier les tarifs", () -> InterfaceGraphique.getInstance().setVuePrincipale(new VueEditTarifs(new ArrayList<>()) {}));
        addAction("Archiver la base de donnee", () -> {});
        addAction("Deconnexion", () -> Controleur.getInstance().deconnection());
    }
}
