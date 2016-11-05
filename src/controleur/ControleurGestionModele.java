
package controleur;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import modele.Modele;
import modele.TypeCompte;
import vue.InterfaceGraphique;
import vue.VueListeArchives;
import vue.VueStatistiques;

public class ControleurGestionModele {
    
    private static ControleurGestionModele instance;

    private Controleur controleur;
    
    /**
     * Singleton
     */
    public static ControleurGestionModele getInstance() {
        if (instance == null) {
            instance = new ControleurGestionModele();
        }
        return instance;
    }
    
    private ControleurGestionModele() {
        controleur = Controleur.getInstance();
    }
    
    public void saveModele() {
        controleur.modele.save();
    }

    public void showStatistiques() {
        if (controleur.verifieTypeCompte(TypeCompte.Responsable)) {
            File file = new File("archive");
            ArrayList<String> archives = new ArrayList<>();
            archives.add("courant");
            for (String f : file.list()) {
                archives.add(f);
            }
            InterfaceGraphique.getInstance().setVuePrincipale(new VueListeArchives(archives));
        }
    }

    public void showStatistiques(String archive) {
        if (controleur.verifieTypeCompte(TypeCompte.Responsable)) {
            Modele modele;
            if (archive.equals("courant")) {
                modele = controleur.modele;
            } else {
                modele = new Modele("archive/" + archive);
            }
            InterfaceGraphique.getInstance().setVuePrincipale(new VueStatistiques(modele));
        }
    }

    public void archiver() {
        if (controleur.verifieTypeCompte(TypeCompte.Admin)) {
            DateFormat format = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
            controleur.modele.save("archive/" + format.format(new Date()) + ".db");
            controleur.modele.delete();
            controleur.modele = new Modele();
            ControleurCompte.getInstance().deconnection();
        }
    }
}
