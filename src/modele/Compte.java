
package modele;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Compte implements Serializable {
    
    public String login;
    public String password;
    public TypeComte type;
    public String email;
    public String nom;
    public String prenom;

    private ArrayList<Reservation> reservations; 
    public ArrayList<Dossier> achats;
    
    public Compte(String login, String password, TypeComte type, String email, String nom, String prenom) {
        this.login = login;
        this.password = password;
        this.type = type;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        
        reservations = new ArrayList();
        achats = new ArrayList();
    }
    
    public ArrayList<Reservation> getReservations(){
        int i = 0;
        while (i<reservations.size()){
            if (reservations.get(i).representation.isEndReservation()){
                reservations.get(i).libereAll();
                reservations.remove(i);
            } else {
                i++;
            }
        }
        return reservations;
    }
    
    public void reserve(Reservation reservation){
        reservations.add(reservation);
    }
    
    public void libere(Reservation reservation){
        reservations.remove(reservation);
    }
    
    public void achat(Dossier dossier){
        achats.add(dossier);
    }
}
