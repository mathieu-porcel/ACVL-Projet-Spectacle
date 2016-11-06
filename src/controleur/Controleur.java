package controleur;

import java.util.Date;
import java.util.GregorianCalendar;

import modele.Compte;
import modele.Modele;
import modele.TypeCompte;
import vue.InterfaceGraphique;
import vue.VueActionsAdmin;
import vue.VueActionsClient;
import vue.VueActionsNonConnecte;
import vue.VueActionsResponsable;
import vue.VueConnexion;

public class Controleur {

    private static Controleur instance;

    public boolean isAdmin;
    public Modele modele;
    public Compte currentUser;

    /**
     * Singleton
     */
    public static Controleur getInstance() {
        if (instance == null) {
            instance = new Controleur();
        }
        return instance;
    }

    private Controleur() {
        isAdmin = false;
        modele = new Modele();
        currentUser = null;
        defaultNonConnecte();
    }

    public boolean verifieNotNull(Object... valeurs) {
        int i = 0;
        while (i < valeurs.length && valeurs[i] != null && (!(valeurs[i] instanceof String) || !"".equals(valeurs[i])) ) {
            i++;
        }
        return i == valeurs.length;
    }

    public boolean verifieTypeCompte(TypeCompte type) {
        return type == currentUser.type;
    }

    public Date stringToDateWithHour(String date) {
        String[] split = date.split(" ");
        if (split.length != 2) {
            return null;
        }
        String[] jour = split[0].split("/");
        if (jour.length != 3) {
            return null;
        }
        String heure = split[1];
        try {
            return new GregorianCalendar(Integer.valueOf(jour[2]), Integer.valueOf(jour[1]) - 1, Integer.valueOf(jour[0]), Integer.valueOf(heure), 0).getTime();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void defaultNonConnecte() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsNonConnecte(currentUser, isAdmin));
        InterfaceGraphique.getInstance().setVuePrincipale(new VueConnexion());
    }

    public void defaultClient() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsClient(currentUser, isAdmin));
        ControleurSpectacle.getInstance().listeRepresentations();
    }

    public void defaultResponsable() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsResponsable(currentUser, isAdmin));
        ControleurSpectacle.getInstance().gestionSpectacles();
    }

    public void defaultAdmin() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsAdmin(currentUser, isAdmin));
        ControleurCompte.getInstance().gestionCompte();
    }

}
