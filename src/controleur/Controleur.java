package controleur;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import modele.Categorie;
import modele.Compte;
import modele.Dossier;
import modele.Modele;
import modele.Place;
import modele.Representation;
import modele.Reservation;
import modele.ReservationImpossible;
import modele.Spectacle;
import modele.TypeCompte;
import modele.Zone;
import vue.InterfaceGraphique;
import vue.VueAchats;
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
import vue.VueListeArchives;
import vue.VuePreAchat;
import vue.VueRecutAchat;
import vue.VueRepresentations;
import vue.VueReservations;
import vue.VueStatistiques;

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
        while (i < valeurs.length && valeurs[i] != null && (!(valeurs[i] instanceof String) || !"".equals(valeurs[i])) ) {
            i++;
        }
        return i == valeurs.length;
    }

    private boolean verifieTypeCompte(TypeCompte type) {
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
        InterfaceGraphique.getInstance().setVueActions(new VueActionsNonConnecte(currentUser, isAdmin));
        InterfaceGraphique.getInstance().setVuePrincipale(new VueConnexion());
    }

    private void defaultClient() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsClient(currentUser, isAdmin));
        listeRepresentations();
    }

    private void defaultResponsable() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsResponsable(currentUser, isAdmin));
        gestionSpectacles();
    }

    private void defaultAdmin() {
        InterfaceGraphique.getInstance().setVueActions(new VueActionsAdmin(currentUser, isAdmin));
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
                    Compte user = modele.addCompte(login, password, TypeCompte.Client, email, nom, prenom);
                    currentUser = user;
                    defaultClient();
                } else if (currentUser.type == TypeCompte.Admin) {
                    modele.addCompte(login, password, TypeCompte.Responsable, email, nom, prenom);
                    gestionCompte();
                }
            }
        }
    }

    public void deconnection() {
        if (isAdmin && currentUser.type != TypeCompte.Admin) {
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
        if (verifieTypeCompte(TypeCompte.Client) && verifieNotNull(representation, places) && places.size() >= 1
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

    public void annuleResevation(Reservation reservation) {
        if (verifieTypeCompte(TypeCompte.Client) && verifieNotNull(reservation) && reservation.compte == currentUser) {
            reservation.libereAll();
            defaultClient();
        }
    }

    public void achatDirect(Representation representation, ArrayList<Place> places) {
        if (verifieTypeCompte(TypeCompte.Client) && verifieNotNull(places) && places.size() >= 1 && verifiePlaceDisponible(representation, places)
                && new Date().getTime() <= representation.date.getTime()) {
            Dossier d = modele.createDossier(currentUser, places, representation);
            InterfaceGraphique.getInstance().setVuePrincipale(new VueRecutAchat(d));
        }
    }

    public void achatReservation(Reservation reservation) {
        if (verifieTypeCompte(TypeCompte.Client) && verifieNotNull(reservation) && reservation.compte == currentUser
                && new Date().getTime() <= reservation.representation.date.getTime() && reservation.representation.getReservations().contains(reservation)) {
            ArrayList<Place> places = (ArrayList<Place>) reservation.places.clone();
            Representation representation = reservation.representation;
            reservation.libereAll();
            Dossier d = modele.createDossier(currentUser, places, representation);
            InterfaceGraphique.getInstance().setVuePrincipale(new VueRecutAchat(d));
        }
    }

    public void listeRepresentations() {
        if (verifieTypeCompte(TypeCompte.Client)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueRepresentations(modele.spectacles.values()));
        }
    }

    public void choixPlaces(Representation representation, boolean isAchat) {
        if (verifieTypeCompte(TypeCompte.Client) && representation != null) {
            ArrayList<Place> occupe = representation.getPlacesAcheter();
            occupe.addAll(representation.getPlacesReserver());
            InterfaceGraphique.getInstance().setVuePrincipale(new VueChoixPlaces(representation, occupe, isAchat));
        }
    }

    public void listeAchats() {
        if (verifieTypeCompte(TypeCompte.Client)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueAchats(currentUser.achats));
        }
    }

    public void listeReservations() {
        if (verifieTypeCompte(TypeCompte.Client)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueReservations(currentUser.getReservations()));
        }
    }

    public void voirRecut(Dossier dossier) {
        if (dossier != null && verifieTypeCompte(TypeCompte.Client) && currentUser == dossier.compte) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueRecutAchat(dossier));
        }
    }

    public void preAchat(Representation representation, ArrayList<Place> places) {
        if (verifieNotNull(representation, places) && places.size() > 1) {
            preAchat(representation, places, null);
        }
    }

    public void preAchat(Reservation reservation) {
        if (verifieNotNull(reservation) && reservation.compte == currentUser) {
            ArrayList<Place> places = reservation.places;
            Representation representation = reservation.representation;
            preAchat(representation, places, reservation);
        }
    }

    private void preAchat(Representation representation, ArrayList<Place> places, Reservation reservation) {
        if (verifieTypeCompte(TypeCompte.Client)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VuePreAchat(representation, places, reservation));
        }
    }

    // Responsable

    public void gestionSpectacles() {
        if (verifieTypeCompte(TypeCompte.Responsable)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueGestionSpecacles(modele.spectacles.values()));
        }
    }

    public void addSpectacle(String nom) {
        if (verifieTypeCompte(TypeCompte.Responsable) && !modele.spectacles.containsKey(nom)) {
            modele.addSpectacle(nom);
            gestionSpectacles();
        }
    }

    public void addRepresentation(Spectacle spectacle, String date) {
        if (verifieTypeCompte(TypeCompte.Responsable) && verifieNotNull(spectacle, date)) {
            Date d = stringToDateWithHour(date);
            if (d != null /* && new Date().getTime()<=d.getTime() */) { // TODO comment for test/dev. Uncommetn for prod
                spectacle.addRepresentation(d, modele.salle);
                gestionSpectacles();
            }
        }
    }

    public void annulerRepresentation(Representation representation) {
        if (verifieTypeCompte(TypeCompte.Responsable) && representation != null && new Date().getTime() <= representation.date.getTime()) {
            representation.annuler();
            gestionSpectacles();
        }
    }

    // Admin

    public void editSalle() {
        if (verifieTypeCompte(TypeCompte.Admin)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueEditSalle(modele.salle.zones.values(), modele.categories.values()));
        }
    }

    public void showStatistiques() {
        if (verifieTypeCompte(TypeCompte.Responsable)) {
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
        if (verifieTypeCompte(TypeCompte.Responsable)) {
            Modele modele;
            if (archive.equals("courant")) {
                modele = this.modele;
            } else {
                modele = new Modele("archive/" + archive);
            }
            InterfaceGraphique.getInstance().setVuePrincipale(new VueStatistiques(modele));
        }
    }

    public void editTarifs() {
        if (verifieTypeCompte(TypeCompte.Admin)) {
            InterfaceGraphique.getInstance().setVuePrincipale(new VueEditTarifs(modele.categories.values()));
        }
    }

    public void addRang(Zone zone) {
        if (verifieTypeCompte(TypeCompte.Admin) && zone != null) {
            zone.addRang();
            editSalle();
        }
    }

    public void addNumero(Zone zone, int rang) {
        if (verifieTypeCompte(TypeCompte.Admin) && zone != null && zone.places.size() > rang) {
            zone.addNumero(rang);
            editSalle();
        }
    }

    public void addZone(Categorie categorie) {
        if (verifieTypeCompte(TypeCompte.Admin) && categorie != null) {
            modele.addZone(categorie);
            editSalle();
        }
    }

    public void addCategorie(String nom, String prix) {
        try {
            float tarif = Float.parseFloat(prix);
            if (verifieTypeCompte(TypeCompte.Admin) && nom != null && tarif > 0) {
                modele.addCategorie(nom, tarif);
                editTarifs();
            }
        } catch (NumberFormatException e) {}
    }

    public void gestionCompte() {
        if (verifieTypeCompte(TypeCompte.Admin)) {
            ArrayList<Compte> comptes = new ArrayList<>();
            for (Compte c : modele.comptes.values()) {
                if (c.type != TypeCompte.Admin) {
                    comptes.add(c);
                }
            }
            InterfaceGraphique.getInstance().setVuePrincipale(new VueGestionComptes(comptes));
        }
    }

    public void usurper(Compte usurpation) {
        if (verifieTypeCompte(TypeCompte.Admin) && usurpation != null && usurpation.type != TypeCompte.Admin) {
            currentUser = usurpation;
            if (verifieTypeCompte(TypeCompte.Client)) {
                defaultClient();
            } else if (verifieTypeCompte(TypeCompte.Responsable)) {
                defaultResponsable();
            }
        }
    }

    public void archiver() {
        if (verifieTypeCompte(TypeCompte.Admin)) {
            DateFormat format = new SimpleDateFormat("dd_MM_yy_HH_mm_ss");
            modele.save("archive/" + format.format(new Date()) + ".db");
            modele.delete();
            modele = new Modele();
            deconnection();
        }
    }
}
