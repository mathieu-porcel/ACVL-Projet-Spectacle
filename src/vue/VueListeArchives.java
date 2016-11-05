package vue;

import java.util.Collection;
import java.util.function.Supplier;

import controleur.Controleur;
import controleur.ControleurGestionModele;

@SuppressWarnings("serial")
public class VueListeArchives extends AbstractVuePrincipale {
    public VueListeArchives(Collection<String> archives) {
        Supplier<?> archive = addListe(archives);
        addBouton("Afficher", () -> ControleurGestionModele.getInstance().showStatistiques((String) archive.get()));
    }
}
