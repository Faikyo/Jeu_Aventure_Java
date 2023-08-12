package modele;

import java.util.List;
import java.util.Random;

public class Ami extends EtatAnimal{

    public Ami(AnimalDomestique pet) {
        super(pet);
    }
    @Override
    public Carte seDeplace(Carte c) {

        // On fait une copie de la carte.
        Carte copy = c;
        // On récupère le personnage.
        Personnage p = copy.getPersonnage();
        // On récupère si le personnage ou un danger est au alentour de l'animal.
        int[] posP= c.checkAlentour4(pet.getPosition()[0],pet.getPosition()[1],"@");
        int[] posD= c.checkDanger4(pet.getPosition()[0],pet.getPosition()[1]);

        //S'il y a un danger à proximité.
        if(posD[0] != 0 && posD[1] != 0 ){

            // Si vous êtes amis et à proximité : il court vers votre poche.
            if(posP[0] != 0 && posP[1] != 0 ){
                p.setPoche(pet);
                copy.disparait2(pet.getPosition()[0],pet.getPosition()[1]);
                pet.setPosition(p.getPosition());
                return copy;
            }

            // Sinon : il court dans le sens oppose au danger pour se placer dans la première case vide.
            else{
                int[] pos = copy.posOpposeeDanger(pet.getPosition()[0], pet.getPosition()[1], posD[0], posD[1]);
                copy.prendCaPlace(pet.getPosition()[1], pet.getPosition()[0],pos[1],pos[0]);
                pet.setPosition(pos);
                return copy;
            }
        }
        //S'il n'y a pas de danger, il se déplace aléatoirement.
        else {
            int[] posE= pet.getPosition();
            List<int[]> posV = copy.listPosVide(posE[0], posE[1]);
            if(posV!=null){
                Random random = new Random();
                int nb = random.nextInt(posV.size());
                copy.prendCaPlace(posE[1], posE[0], posV.get(nb)[1], posV.get(nb)[0]);
                pet.setPosition(posV.get(nb));
                return copy;
            }
            else{
                return copy;
            }
        }
    }

    @Override
    public String toString() {
        return "Ami";
    }
}
