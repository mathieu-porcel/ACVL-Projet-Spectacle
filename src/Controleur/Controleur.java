package Controleur;

import java.util.ArrayList;
import modele.Compte;
import modele.Modele;
import modele.Place;
import modele.Representation;
import modele.Reservation;
import modele.TypeComte;
import modele.Zone;
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
        defaultNonConnecte();
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
    
    private void defaultNonConnecte(){
        InterfaceGraphique.getInstance().setVueActions(new VueActionsNonConnecte());
        InterfaceGraphique.getInstance().setVuePrincipale(null);
    }
    
    private void defaultClient(){
        //TODO
    }
    
    private void defaultResponsable(){
        //TODO
    }
    
    private void defaultAdmin(){
        InterfaceGraphique.getInstance().setVueActions(new VueActionsAdmin());
        InterfaceGraphique.getInstance().setVuePrincipale(null);
    }
    
    
    //Rien
    
    public void connection(String login, String password){
        if (verifieNotNull(login, password)){
            Compte user = modele.getCompte(login);
            if (user!=null && user.password.equals(password)){
                currentUser = user;
                
                if (null != user.type)switch (user.type) {
                    case Client:
                        defaultClient();
                        break;
                    case Responsable:
                        defaultResponsable();
                        break;
                    case Admin:
                        isAdmin = true;
                        defaultAdmin();
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
                if (currentUser == null){
                    Compte user = modele.addCompte(login, password, TypeComte.Client, email, nom, prenom);
                    currentUser = user;
                    defaultClient();
                } else if (currentUser.type == TypeComte.Admin){
                    modele.addCompte(login, password, TypeComte.Responsable, email, nom, prenom);
                    defaultAdmin();
                }                    
            }
        }
    }
    
    public void deconnection(){
        if (isAdmin && currentUser.type != TypeComte.Admin){
            currentUser = modele.getCompte("admin");
            defaultAdmin();
        } else {
            isAdmin = false;
            currentUser = null;
            defaultNonConnecte();
        }
    }
    
    
    //Client
    
    public void reservePlace(Representation representation, ArrayList<Place> places){
        if (verifieTypeCompte(TypeComte.Client) && verifieNotNull(representation, places) && places.size()>=1 && verifiePlaceDisponible(representation, places)){
            new Reservation(currentUser, places, representation);
            defaultClient();
        }
    }
    
    public void annuleResevation(Reservation reservation, Place place){
        if(verifieTypeCompte(TypeComte.Client) && verifieNotNull(reservation, place)){
            reservation.libere(place);
            defaultClient();
        }
    }
    
    public void achatDirect(Representation representation, ArrayList<Place> places){
        if(verifieTypeCompte(TypeComte.Client) && verifieNotNull(places) && places.size()>=1 && verifiePlaceDisponible(representation, places)){
            modele.createDossier(currentUser, places, representation);
            defaultClient();
        }
    }

    
    //Admin
    
    public void addRang(Zone zone){
        if (verifieTypeCompte(TypeComte.Admin) && zone!=null){
            zone.addRang();
        }
    }
    
    public void addNumero(Zone zone, int rang){
        if (verifieTypeCompte(TypeComte.Admin) && zone!=null && zone.places.size()<rang-1){
            zone.addNumero(rang);
        }
    }
    
}
