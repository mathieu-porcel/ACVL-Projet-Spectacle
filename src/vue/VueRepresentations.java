package vue;

import controleur.ControleurReservationsAchats;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import modele.Representation;
import modele.Spectacle;

@SuppressWarnings("serial")
public class VueRepresentations extends AbstractVuePrincipale {
    public VueRepresentations(Collection<Spectacle> spectacles) {
        Date date = new Date();
        for (Spectacle spectacle : spectacles) {
            addTexte(spectacle.nom);
            newLigne();
            for (Representation representation : spectacle.representations) {
                if (date.getTime()<=representation.date.getTime() && !representation.isAnnuler){
                    addTexte("\t");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy HH");
                    addTexte(dateFormat.format(representation.date) + "H");
                    addBouton("Acheter", () -> ControleurReservationsAchats.getInstance().choixPlaces(representation, true));
                    if (!representation.isEndReservation()){
                        addBouton("Reserver", () -> ControleurReservationsAchats.getInstance().choixPlaces(representation, false));
                    }
                    newLigne();
                }
            }
        }
    }
}
