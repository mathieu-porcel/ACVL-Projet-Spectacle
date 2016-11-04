package vue;

import java.util.Collection;
import java.util.function.Supplier;

import controleur.Controleur;

@SuppressWarnings("serial")
public class VueListeArchives extends AbstractVuePrincipale {
    public VueListeArchives(Collection<String> archives) {
        Supplier<?> archive = addListe(archives);
        addBouton("Afficher", () -> Controleur.getInstance().showStatistiques((String) archive.get()));
    }
}
