package controleur;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import modele.Categorie;
import modele.Compte;
import modele.Modele;
import modele.Place;
import modele.Representation;
import modele.Reservation;
import modele.ReservationImpossible;
import modele.Spectacle;
import modele.TypeComte;
import modele.Zone;
import vue.InterfaceGraphique;
import vue.VueActionsAdmin;
import vue.VueActionsClient;
import vue.VueActionsNonConnecte;
import vue.VueActionsResponsable;
import vue.VueChoixPlaces;
import vue.VueConnexion;
import vue.VueEditSalle;
import vue.VueEditTarifs;
import vue.VueGestionComptes;
import vue.VueGestionSpecacles;
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
        for (Place place : places) {
            if (!representation.salle.getAllPlace().contains(place) || representation.getPlacesReserver().contains(place)
                    || representation.getPlacesAcheter().contains(place)) {
                return false;
            }
        }
        return true;
    }

    private Date stringToDate(String date) {
        String[] jour = date.split("/");
        if (jour.length != 3) {
            return null;
        }
        try {
            return new GregorianCalendar(Integer.valueOf(jour[2]), Integer.valueOf(jour[1]) - 1, Integer.valueOf(jour[0])).getTime();
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Date stringToDateWithHour(String date) {
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

    public void saveModele() {
        modele.save();
    }

    private void defaultNonConnecte() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsNonConnecte());
        InterfaceGraphique.getInstance().setVuePrincipale(new VueConnexion());
    }

    private void defaultClient() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsClient());
        listeRepresentations();
    }

    private void defaultResponsable() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsResponsable());
        gestionSpectacles();
    }

    private void defaultAdmin() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsAdmin());
        gestionCompte();
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
                    gestionCompte();
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
            try {
                new Reservation(currentUser, places, representation);
                defaultClient();
            } catch (ReservationImpossible ex) {
                // Reservation impossible
                // CurrenteDate + h >= dateResa
            }
        }
    }

    public void annuleResevation(Reservation reservation, Place place) {
        if (verifieTypeCompte(TypeComte.Client) && verifieNotNull(reservation, place)) {
            reservation.libere(place);
            defaultClient();
        }
    }

    public void achatDirect(Representation representation, ArrayList<Place> places) {
        if (verifieTypeCompte(TypeComte.Client) && verifieNotNull(places) && places.size() >= 1 && verifiePlaceDisponible(representation, places) && new Date().getTime()<=representation.date.getTime()) {
            modele.createDossier(currentUser, places, representation);
            defaultClient();
        }
    }

    public void listeRepresentations() {
        if (verifieTypeCompte(TypeComte.Client)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueRepresentations(modele.spectacles.values()));
        }
    }
    
    public void choixPlaces(Representation representation, boolean isAchat){
        if (verifieTypeCompte(TypeComte.Client) && representation != null) {
            ArrayList<Place> occupe = representation.getPlacesAcheter();
            occupe.addAll(representation.getPlacesReserver());
            InterfaceGraphique.getInstance().setVuePrincipale(new VueChoixPlaces(representation, occupe, isAchat));
        }
    }

    // Responsable

    public void gestionSpectacles() {
        if (verifieTypeCompte(TypeComte.Responsable)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueGestionSpecacles(modele.spectacles.values()));
        }
    }

    public void addSpectacle(String nom) {
        if (verifieTypeCompte(TypeComte.Responsable) && !modele.spectacles.containsKey(nom)) {
            modele.addSpectacle(nom);
            gestionSpectacles();
        }
    }

    public void addRepresentation(Spectacle spectacle, String date) {
        if (verifieTypeCompte(TypeComte.Responsable) && verifieNotNull(spectacle, date)) {
            Date d = stringToDateWithHour(date);
            if (d != null /* && new Date().getTime()<=d.getTime() */) { // TODO comment for test/dev. Uncommetn for prod
                spectacle.addRepresentation(d, modele.salle);
                gestionSpectacles();
            }
        }
    }

    public void annullerRepresentation(Representation representation) {
        if (verifieTypeCompte(TypeComte.Responsable) && representation != null && new Date().getTime() <= representation.date.getTime()) {
            representation.annuler();
            gestionSpectacles();
        }
    }

    // Admin

    public void editSalle() {
        if (verifieTypeCompte(TypeComte.Admin)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueEditSalle(modele.salle.zones.values(), modele.categories.values()));
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

    public void gestionCompte() {
        if (verifieTypeCompte(TypeComte.Admin)) {
            ArrayList<Compte> comptes = new ArrayList<>();
            for (Compte c : modele.comptes.values()) {
                if (c.type != TypeComte.Admin) {
                    comptes.add(c);
                }
            }
            InterfaceGraphique.getInstance().setVuePrincipale(new VueGestionComptes(comptes));
        }
    }

    public void usurper(Compte usurpation) {
        if (verifieTypeCompte(TypeComte.Admin) && usurpation != null && usurpation.type != TypeComte.Admin) {
            currentUser = usurpation;
            if (verifieTypeCompte(TypeComte.Client)) {
                defaultClient();
            } else if (verifieTypeCompte(TypeComte.Responsable)) {
                defaultResponsable();
            }
        }
    }
}
