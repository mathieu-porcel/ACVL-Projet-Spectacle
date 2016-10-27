package Controleur;

import java.util.ArrayList;
import modele.Compte;
import modele.Modele;
import modele.Place;
import modele.Representation;
import modele.Reservation;
import modele.TypeComte;
import vue.InterfaceGraphique;
import vue.VueActionsAdmin;
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
    
    private boolean verifieNotNull(Object ... valeurs){
        int i = 0;
        while (i<valeurs.length && valeurs[i]!=null){
            i++;
        }
        return i==valeurs.length;
    }
    
    private boolean verifieTypeCompte(TypeComte type){
        return type == currentUser.type;
    }
    
    private boolean verifiePlaceDisponible(Representation representation, ArrayList<Place> places){
        int i=0;
        while (i<places.size() && representation.placesLibre.contains(places.get(i))){
            i++;
        }
        return i==places.size();
    }
    
    public void nonConnecte(){
        InterfaceGraphique.getInstance().setVueActions(new VueActionsNonConnecte());
    }
    
    public void connection(String login, String password){
        if (verifieNotNull(login, password)){
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
                        InterfaceGraphique.getInstance().setVueActions(new VueActionsAdmin());
                        InterfaceGraphique.getInstance().setVuePrincipale(null);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public void inscription(String login, String nom, String prenom, String email, String password){
        if (verifieNotNull(login, nom, prenom, password)){
            if (!modele.comptes.containsKey(login)){
                Compte user = modele.addCompte(login, password, TypeComte.Client, email, nom, prenom);
                currentUser = user;
                //TODO addView client
            }
        }
    }
    
    public void deconnection(){
        if (isAdmin){
            currentUser = modele.getCompte("admin");
            //TODO addView admin
        } else {
            nonConnecte();
        }
    }
    
    public void reservePlace(Representation representation, ArrayList<Place> places){
        if (verifieTypeCompte(TypeComte.Client) && verifieNotNull(representation, places) && places.size()>=1 && verifiePlaceDisponible(representation, places)){
            new Reservation(currentUser, places, representation);
            //TODO addView client
        }
    }
    
    public void annuleResevation(Reservation reservation, Place place){
        if(verifieTypeCompte(TypeComte.Client) && verifieNotNull(reservation, place)){
            reservation.libere(place);
            //TODO addView client
        }
    }
    
    public void achatDirect(Representation representation, ArrayList<Place> places){
        if(verifieTypeCompte(TypeComte.Client) && verifieNotNull(places) && places.size()>=1 && verifiePlaceDisponible(representation, places)){
            modele.createDossier(currentUser, places, representation);
            //TODO addView client
        }
    }
    
    
    
    
}
