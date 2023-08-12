package modele;

import java.util.List;
import java.util.Random;

public class Junkie extends EtatAnimal {

    public Junkie(AnimalDomestique pet) {
        super(pet);
    }

    @Override
    public Carte seDeplace(Carte c) {

        // On fait une copie de la carte.
        Carte copy = c;

        int t = pet.getTourAnimal();

        int m = copy.tourAnimalRassasierMax;

        // Un animal junkie sera de nouveau affamé au bout de tourAnimalRassasierMax.
        if (t == m) {
            pet.setEtatCourant(new Affamer(pet));
            return copy;
        }
        // Sinon il se déplace aléatoirement.
        else {
            int[] posE= pet.getPosition();
            List<int[]> posV = copy.listPosVide(posE[0], posE[1]);

            Random random = new Random();
            int nb = random.nextInt(posV.size());

            copy.prendCaPlace(posE[1], posE[0], posV.get(nb)[1], posV.get(nb)[0]);
            pet.setPosition(posV.get(nb));
            return copy;
        }
    }

    @Override
    public String toString() {
        return "Junkie";
    }
}
