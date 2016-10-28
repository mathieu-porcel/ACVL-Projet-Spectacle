package modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("serial")
public class Modele implements Serializable {

    private static final String defaultSave = "database.db";

    private int id;

    public HashMap<String, Compte> comptes;
    public HashMap<Integer, Spectacle> spectacles;
    public HashMap<String, Categorie> categories;
    public HashMap<Integer, Zone> zones;

    public Modele() {
        load(defaultSave);
    }

    public Modele(String path) {
        load(path);
    }

    private void load(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                ObjectInputStream s = new ObjectInputStream(new FileInputStream(new File(path)));
                Modele modele = (Modele) s.readObject();
                id = modele.id;
                comptes = modele.comptes;
                spectacles = modele.spectacles;
                categories = modele.categories;
                zones = modele.zones;
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            id = 0;
            comptes = new HashMap<>();
            spectacles = new HashMap<>();
            categories = new HashMap<>();
            zones = new HashMap<>();
            comptes.put("admin", new Compte("admin", "admin", TypeComte.Admin, null, "Admin", "Admin"));
        }
    }

    public void save() {
        save(defaultSave);
    }

    public void save(String path) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File(path)));
            outputStream.writeObject(this);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int getFreeId() {
        id++;
        return id - 1;
    }

    public Compte addCompte(String login, String password, TypeComte type, String email, String nom, String prenom) {
        if (comptes.containsKey(login)) {
            return null;
        } else {
            Compte compte = new Compte(login, password, type, email, nom, prenom);
            comptes.put(login, compte);
            return compte;
        }
    }

    public Compte getCompte(String login) {
        return comptes.get(login);
    }

    public Spectacle addSpectacle(String nom) {
        Spectacle s = new Spectacle(getFreeId(), nom);
        spectacles.put(s.numero, s);
        return s;
    }

    public Spectacle getSpectacle(Integer numero) {
        return spectacles.get(numero);
    }

    public Categorie addCategorie(String nom, float tarif) {
        if (categories.containsKey(nom)) {
            return null;
        } else {
            Categorie c = new Categorie(nom, tarif);
            categories.put(nom, c);
            return c;
        }
    }

    public Categorie getCategorie(String nom) {
        return categories.get(nom);
    }

    public Zone addZone(Categorie categorie) {
        Zone zone = new Zone(zones.size(), categorie);
        zones.put(zone.numero, zone);
        return zone;
    }

    public Zone getZone(int numero) {
        return zones.get(numero);
    }

    public Dossier createDossier(Compte user, List<Place> places, Representation representation) {
        ArrayList<AchatPlace> achats = new ArrayList<>();
        for (Place p : places) {
            achats.add(new AchatPlace(getFreeId(), p));
        }
        return new Dossier(getFreeId(), user, representation, achats);
    }
}
