
package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Salle implements Serializable {
    public HashMap<Integer, Zone> zones;
    
    public Salle(){
        this.zones = new HashMap();
    }
    
    public ArrayList<Place> getAllPlace(){
        ArrayList<Place> ret = new ArrayList();
        for (Zone z : zones.values()){
            ret.addAll(z.getPlace());
        }
        return ret;
    }
}
