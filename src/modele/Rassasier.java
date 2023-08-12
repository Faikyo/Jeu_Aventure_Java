package modele;


import java.util.List;
import java.util.Random;

public class Rassasier extends EtatAnimal{

    public Rassasier(AnimalDomestique pet) {
        super(pet);
    }

    @Override
    public Carte seDeplace(Carte c) {

        // On fait une copie de la carte.
        Carte copy = c;

        int t = pet.getTourAnimal();
        int m = copy.tourAnimalRassasierMax;

        // Un animal rassasié sera de nouveau affamé au bout de tourAnimalRassasierMax .
        if(t == m){
            pet.setEtatCourant(new Affamer(pet));
            return copy;
        }
        else{
            pet.setTourEcureuil(t+1);
            int[] posA = copy.checkRefuge4 ( pet.getPosition()[0] , pet.getPosition()[1]);
            int[] posB = copy.checkAlentour4 ( pet.getPosition()[0] , pet.getPosition()[1] ,"B");
            int[] posD = copy.checkDanger4 ( pet.getPosition()[0] , pet.getPosition()[1]);

            // Si à moins de 4 cases, il voit un danger et :
            if( posD[0] != 0 && posD[1] != 0 ){
                // S'il y a un refuge : il court vers le refuge pour s'y réfugier, donc l'animal disparait.
                if( posA[0] != 0 && posA[1] != 0 ){
                    copy.disparait( pet.getPosition()[1] , pet.getPosition()[0]);
                    // on doit supprimer l'animal dans le contrôleur aussi.
                    return copy;
                }
                // S'il y a un buisson : il court vers le buisson pour s'y réfugier, donc l'animal disparait.
                else if( posB[0] != 0 && posB[1] != 0 ){
                    copy.disparait( pet.getPosition()[1] , pet.getPosition()[0] );
                    // on doit supprimer l'écureuil dans le contrôleur aussi
                    return copy;
                }
                // Sinon : il court dans le sens opposé au danger pour se placer dans la première case vide.
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
    }

    @Override
    public String toString() {
        return "Rassasier";
    }
}
