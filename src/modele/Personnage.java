package modele;

import java.util.ArrayList;
import java.util.List;

public class Personnage {

    private List<String> sac = new ArrayList<>();
    private AnimalDomestique poche = null;
    private int[] position;

    public Personnage(int[] position) {
        this.position = position;
    }


    /**
     * Fonction qui permet au personnage de deposer un objet,
     * si n'y a pas cette objet dans le sac on ne fait rien.
     * @param i
     */
    public void deposeSac(int i) {
        sac.remove(i);
    }

    public List<String> getSac() {
        return this.sac;
    }

    public void frappe(AnimalDomestique a) {
        a.setEtatCourant(new Affamer(a));
        a.setTourEcureuil(0);
    }

    public boolean ramasse(String s) {
        if (!s.equals("@")) {
            this.sac.add(s);
            return true;
        }
        return false;
    }

    public void setPoche(AnimalDomestique poche) {
        this.poche = poche;
    }

    public int[] getPosition() {
        return position;
    }

    public String getSacO(int choixSac) {
        return sac.get(choixSac);
    }
}
