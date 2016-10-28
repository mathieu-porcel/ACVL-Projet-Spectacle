package Controleur;

import java.util.ArrayList;

import modele.Categorie;
import modele.Compte;
import modele.Modele;
import modele.Place;
import modele.Representation;
import modele.Reservation;
import modele.TypeComte;
import modele.Zone;
import vue.InterfaceGraphique;
import vue.VueActionsAdmin;
import vue.VueActionsClient;
import vue.VueActionsNonConnecte;
import vue.VueActionsResponsable;
import vue.VueEditSalle;
import vue.VueEditTarifs;
import vue.VueRepresentations;

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

    private boolean verifieNotNull(Object... valeurs) {
        int i = 0;
        while (i < valeurs.length && valeurs[i] != null) {
            i++;
        }
        return i == valeurs.length;
    }

    private boolean verifieTypeCompte(TypeComte type) {
        return type == currentUser.type;
    }

    private boolean verifiePlaceDisponible(Representation representation, ArrayList<Place> places) {
        int i = 0;
        while (i < places.size() && representation.placesLibre.contains(places.get(i))) {
            i++;
        }
        return i == places.size();
    }

    public void saveModele() {
        modele.save();
    }

    private void defaultNonConnecte() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsNonConnecte());
        InterfaceGraphique.getInstance().setVuePrincipale(null);
    }

    private void defaultClient() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsClient());
        InterfaceGraphique.getInstance().setVuePrincipale(null);
    }

    private void defaultResponsable() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsResponsable());
        InterfaceGraphique.getInstance().setVuePrincipale(null);
    }

    private void defaultAdmin() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsAdmin());
        InterfaceGraphique.getInstance().setVuePrincipale(null);
    }

    // Rien

    public void connection(String login, String password) {
        if (verifieNotNull(login, password)) {
            Compte user = modele.getCompte(login);
            if (user != null && user.password.equals(password)) {
                currentUser = user;

                if (null != user.type)
                    switch (user.type) {
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

    public void inscription(String login, String nom, String prenom, String email, String password) {
        if (verifieNotNull(login, nom, prenom, password)) {
            if (!modele.comptes.containsKey(login)) {
                if (currentUser == null) {
                    Compte user = modele.addCompte(login, password, TypeComte.Client, email, nom, prenom);
                    currentUser = user;
                    defaultClient();
                } else if (currentUser.type == TypeComte.Admin) {
                    modele.addCompte(login, password, TypeComte.Responsable, email, nom, prenom);
                    defaultAdmin();
                }
            }
        }
    }

    public void deconnection() {
        if (isAdmin && currentUser.type != TypeComte.Admin) {
            currentUser = modele.getCompte("admin");
            defaultAdmin();
        } else {
            isAdmin = false;
            currentUser = null;
            defaultNonConnecte();
        }
    }

    // Client

    public void reservePlace(Representation representation, ArrayList<Place> places) {
        if (verifieTypeCompte(TypeComte.Client) && verifieNotNull(representation, places) && places.size() >= 1
                && verifiePlaceDisponible(representation, places)) {
            new Reservation(currentUser, places, representation);
            defaultClient();
        }
    }

    public void annuleResevation(Reservation reservation, Place place) {
        if (verifieTypeCompte(TypeComte.Client) && verifieNotNull(reservation, place)) {
            reservation.libere(place);
            defaultClient();
        }
    }

    public void achatDirect(Representation representation, ArrayList<Place> places) {
        if (verifieTypeCompte(TypeComte.Client) && verifieNotNull(places) && places.size() >= 1 && verifiePlaceDisponible(representation, places)) {
            modele.createDossier(currentUser, places, representation);
            defaultClient();
        }
    }

    // Admin

    public void editSalle() {
        if (verifieTypeCompte(TypeComte.Admin)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueEditSalle(modele.zones.values(), modele.categories.values()));
        }
    }

    public void editTarifs() {
        if (verifieTypeCompte(TypeComte.Admin)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueEditTarifs(modele.categories.values()));
        }
    }

    public void addRang(Zone zone) {
        if (verifieTypeCompte(TypeComte.Admin) && zone != null) {
            zone.addRang();
            editSalle();
        }
    }

    public void addNumero(Zone zone, int rang) {
        if (verifieTypeCompte(TypeComte.Admin) && zone != null && zone.places.size() > rang) {
            zone.addNumero(rang);
            editSalle();
        }
    }

    public void addZone(Categorie categorie) {
        if (verifieTypeCompte(TypeComte.Admin) && categorie != null) {
            modele.addZone(categorie);
            editSalle();
        }
    }

    public void addCategorie(String nom, String prix) {
        try {
            float tarif = Float.parseFloat(prix);
            if (verifieTypeCompte(TypeComte.Admin) && nom != null && tarif > 0) {
                modele.addCategorie(nom, tarif);
                editTarifs();
            }
        } catch (NumberFormatException e) {}
    }
}
