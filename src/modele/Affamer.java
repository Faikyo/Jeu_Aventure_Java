package modele;

import java.util.List;
import java.util.Random;

public class Affamer extends EtatAnimal{

    public Affamer(AnimalDomestique pet) {
        super(pet);
    }

    /**
     * Fonction qui déplace l'animal qui est dans un état affamé.
     * @param c
     * @return
     */
    @Override
    public Carte seDeplace(Carte c) {

        // On fait une copie de la carte.
        Carte copy = c;

        // On récupère s'il y a un aliment, un champignon, un champignon venimeu autour.
        int [] posG = copy.checkAlentourAliment(pet.getPosition()[0],pet.getPosition()[1]);
        int [] posC = copy.checkAlentour(pet.getPosition()[0],pet.getPosition()[1], "C");
        int [] posCv = copy.checkAlentour(pet.getPosition()[0],pet.getPosition()[1], "c");
        //Position de l'animal avec la liste des positions vide autour de lui.
        int[] posE= pet.getPosition();
        List<int[]> posV = copy.listPosVide(posE[0], posE[1]);


        // S'il voit un aliment : peu importe le danger, il va le chercher et le mange. L’écureuil prend la place de l'aliment.
        if (posG[0] != 0 && posG[1] != 0) {
            int [] posJ = copy.checkAlentour(posG[0],posG[1], "@");
            //S'il y a un joueur à côté de cet aliment il devient son ami
            if(posJ[0] != 0 && posJ[1] != 0){
                copy.prendCaPlace(pet.getPosition()[1],pet.getPosition()[0],posG[1],posG[0]);
                pet.setPosition(posG);
                pet.setEtatCourant(new Ami(pet));
                pet.setTourEcureuil(0);
            }
            else{
                copy.prendCaPlace(pet.getPosition()[1],pet.getPosition()[0],posG[1],posG[0]);
                pet.setPosition(posG);
                pet.setEtatCourant(new Rassasier(pet));
                pet.setTourEcureuil(0);}
        }
        // S'il voit un champignon : il va le chercher et le mange. L’animal prend la place du champignon.
        else if (posC[0] != 0 && posC[1] != 0) {

            //S'il y a un joueur à côté de ce champignion il devient son ami
            int [] posJ = copy.checkAlentour(posC[0],posC[1], "@");
            if(posJ[0] != 0 && posJ[1] != 0){
                copy.prendCaPlace(pet.getPosition()[1],pet.getPosition()[0],posC[1],posC[0]);
                pet.setPosition(posC);
                pet.setEtatCourant(new Ami(pet));
                pet.setTourEcureuil(0);
            }
            else {
                copy.prendCaPlace(pet.getPosition()[1],pet.getPosition()[0],posC[1],posC[0]);
                pet.setPosition(posC);
                pet.setEtatCourant(new Rassasier(pet));
                pet.setTourEcureuil(0);}

        }

        // S'il voit un champignon venimeux : il va le chercher et le mange. L’animal prend la place du champignon.
        else if (posCv[0] != 0 && posCv[1] != 0) {
            copy.prendCaPlace(pet.getPosition()[1],pet.getPosition()[0],posCv[1],posCv[0]);
            pet.setPosition(posCv);
            pet.setEtatCourant(new Junkie(pet));
            pet.setTourEcureuil(0);
        }

        //S'il n'y a pas d'aliment, il se déplace aléatoirement.
        else if(posV!=null){
                Random random = new Random();
                int nb = random.nextInt(posV.size());
                copy.prendCaPlace(posE[1], posE[0], posV.get(nb)[1], posV.get(nb)[0]);
                pet.setPosition(posV.get(nb));
            }
        return copy;
    }

    @Override
    public String toString() {
        return "Affamer";


    }
}
