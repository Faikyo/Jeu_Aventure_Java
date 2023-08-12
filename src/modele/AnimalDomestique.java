package modele;

import java.util.Arrays;

public class AnimalDomestique {

    private int[] position;

    private EtatAnimal etatCourant;

    private int tourAnimal;


    public AnimalDomestique(int[] position ) {
        this.position = position;
        etatCourant = new Rassasier(this);
    }

    public Carte seDeplace(Carte c){
        return etatCourant.seDeplace(c);
    }

    public void setEtatCourant(EtatAnimal etatCourant) {
        this.etatCourant = etatCourant;
    }

    public int[] getPosition() {
        return position;
    }

    public void setPosition(int[] position) {
        this.position = position;
    }

    public int getTourAnimal() {
        return tourAnimal;
    }

    public void setTourEcureuil(int tourAnimal) {
        this.tourAnimal = tourAnimal;
    }

    public EtatAnimal getEtatCourant() {
        return this.etatCourant;
    }

}
