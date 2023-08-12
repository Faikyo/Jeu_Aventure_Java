package modele;

import java.util.List;
import java.util.Random;

public class AnimalSauvage {

    private int[] pos;


    public AnimalSauvage(int[] pos){
        this.pos=pos;
    }

    public int[] getPosition() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }

    /**
     * Fonction qui permet au Renard de se déplacer et/ou manger un écureuil.
     * @param c
     * @return
     */
    public Carte seDeplace(Carte c){

        // On fait une copie de la carte.
        Carte copy = c;

        // On vérifie s'il y a un écureuil à côté du renard.
        int[] posE = copy.checkAlentour(this.getPosition()[0],this.getPosition()[1], "E");

        // On vérifie s'il y a un arbre à côté de l'écureuil pour qu'il puisse se cacher.
        int[] posA = copy.checkAlentour(posE[0],posE[1], "A");

        // S'il y a un écureuil à côté du renard.
        if(posE[0] != 0 && posE[1] != 0){

            // S'il y a un arbre à côté de l'écureuil, il se cache dedans et ne redescend plus jamais car il est traumatisé.
            if(posA[0] != 0 && posA[1] != 0){
                copy.disparait(posE[1],posE[0]);
                return copy;
            }
            else {
                copy.prendCaPlace(this.getPosition()[1], this.getPosition()[0], posE[1], posE[0]);
                this.setPos(posE);
                return copy;
            }
        }

        // Il n'y a pas d'écureuil à côté, il se déplace normalement.
        else {
            List<int[]> list = copy.listPosVide(this.getPosition()[0], this.getPosition()[1]);
            Random random = new Random();
            int[] posV = list.get(random.nextInt(list.size()));
            copy.prendCaPlace(this.getPosition()[1], this.getPosition()[0], posV[1], posV[0]);
            this.setPos(posV);
            return copy;
        }
    }
}
