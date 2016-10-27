package Controleur;

import modele.Compte;
import modele.Compte;
import modele.Modele;
import modele.Modele;
import vue.InterfaceGraphique;
import vue.VueActionsNonConnecte;

public class Controleur {

    private static Controleur instance;
    
    private boolean idAdmin;
    private Modele modele;
    private Compte currentUser;

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
        idAdmin = false;
        modele = new Modele();
        currentUser = null;
        nonConnecte();
    }
    
    public void nonConnecte(){
        InterfaceGraphique.getInstance().setVueActions(new VueActionsNonConnecte());
    }
    
    public void connection(){
        System.out.println("connection");
    }
    
    public void inscription(){
        System.out.println("inscription");
    }
}
