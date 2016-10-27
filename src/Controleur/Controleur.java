package Controleur;

import modele.Compte;
import modele.Compte;
import modele.Modele;
import modele.Modele;
import modele.TypeComte;
import vue.InterfaceGraphique;
import vue.VueActionsNonConnecte;

public class Controleur {

    private static Controleur instance;
    
    private boolean isAdmin;
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
        isAdmin = false;
        modele = new Modele();
        currentUser = null;
        nonConnecte();
    }
    
    private boolean verifieStringNotNull(String ... valeurs){
        int i = 0;
        while (i<valeurs.length && valeurs[i]!=null){
            i++;
        }
        return i==valeurs.length;
    }
    
    public void nonConnecte(){
        InterfaceGraphique.getInstance().setVueActions(new VueActionsNonConnecte());
    }
    
    public void connection(String login, String password){
        if (verifieStringNotNull(login, password)){
            Compte user = modele.getCompte(login);
            if (user!=null && user.password.equals(password)){
                currentUser = user;
                
                if (null != user.type)switch (user.type) {
                    case Client:
                        //TODO addView client
                        break;
                    case Responsable:
                        //TODO addView responsable
                        break;
                    case Admin:
                        isAdmin = true;
                        //TODO addView admin
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public void inscription(String login, String nom, String prenom, String email, String password){
        if (verifieStringNotNull(login, nom, prenom, password)){
            if (!modele.comptes.containsKey(login)){
                Compte user = modele.addCompte(login, password, TypeComte.Client, email, nom, prenom);
                currentUser = user;
                //TODO addView client
            }
        }
    }
}
