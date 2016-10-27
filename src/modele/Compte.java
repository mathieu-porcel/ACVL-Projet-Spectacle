
package modele;

import java.util.ArrayList;

public class Compte {
    
    public String login;
    public String password;
    public TypeComte type;
    public String email;
    public String nom;
    public String prenom;

    public ArrayList<Reservation> reservations; 
    public ArrayList<Dossier> achats;
    
    public Compte(String login, String password, TypeComte type, String email, String nom, String prenom) {
        this.login = login;
        this.password = password;
        this.type = type;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
    }
    
    public void reserve(Place place){
        //TODO
    }
    
    public void libere(Place place){
        //TODO
    }
    
    public void achat(Place place){
        //TODO
    }
}
