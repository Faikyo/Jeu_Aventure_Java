package modele;

import java.util.ArrayList;

public class Jungle extends Carte{
    public Jungle() {
        super();
        this.aliment = "b";
    }

    /**
     * Fonction pour générer une carte aléatoirement.
     */
    public void genereCarte() {

        // Les lettres qu'on utilise pour les éléments de la carte.
        String[] letters = {"S", "P", "B", "b", "C", "c", "s"};


        // On remplit le contour d'arbre.
        for (int x = 0; x < width; x++) {
            map[0][x] = "P";
        }
        for (int x = 0; x < height; x++){
            map[x][0] = "P";
        }
        for (int x = 0; x < width; x++){
            map[height-1][x] = "P";
        }
        for(int x = 0; x < height; x++){
            if (x != 6) {
                map[x][width-1] = "P";
            }
        }

        for (int y = 1; y < height-1; y++) {
            for (int x = 1; x < width-1; x++) {

                int roadStart = (int) (Math.random() * width);
                int roadEnd = roadStart + (int) (Math.random() * (width - roadStart));

                if (Math.random() < 0.9 || (roadStart <= x && x <= roadEnd)) {
                    // On remplit la map à 90% vide.
                    map[y][x] = " ";
                } else {
                    // On choisit une lettre aléatoirement.
                    String letter = letters[(int) (Math.random() * letters.length)];
                    map[y][x] = letter;
                }
            }
        }

        // Pour faire l'entrée sur la carte.
        for (int i = 0; i < 22; i++) {
            int x = 78;
            int y = 5;

            // La première et la deuxième barre pour l'entrée.
            map[y][x+i] = "P";
            map[y+2][x+i] = "P";

            // Mettre des espaces devant l'entrée.
            if (i < 4) {
                map[y][x+i] = " ";
                map[y+2][x+i] = " ";
            }

            // On ne met que le personnage sur cette ligne
            map[y+1][x+i] = " ";
            if (x+i == 87) {
                map[y+1][x+i] = "@";

                // On met à jour les coordonnées du joueur.
                xJoueur = y+1;
                yJoueur = x+i;

            }
        }
        // On remplit les singes.
        ArrayList<int[]> posSinge = this.getElement("S");
        for (int i = 0; i < posSinge.size(); i++) {
            // On récupère la position de chaque singe.
            int[] res = posSinge.get(i);
            AnimalDomestique e = new AnimalDomestique(new int[]{res[0], res[1]});
            animauxDomestique.add(e);
        }
        tourAnimalRassasierMax = 3;
        // On remplit la liste des serpents.
        ArrayList<int[]> posSerpents = this.getElement("s");
        for (int i = 0; i < posSerpents.size(); i++) {
            int[] res = posSerpents.get(i);
            AnimalSauvage s = new AnimalSauvage(new int[]{res[0], res[1]});
            animauxSauvages.add(s);
        }
    }

    public void utiliserCarte() {}

    public boolean mortPersonnage(int x, int y){
        if(map[y+1][x].equals("s")) {
            return false;
        }
        else if (map[y-1][x].equals("s")) {
            return false;
        }
        else if (map[y][x+1].equals("s")) {
            return false;
        }
        else if (map[y][x-1].equals("s")) {
            return false;
        }
        else if (map[y+1][x-1].equals("s")) {
            return false;
        }
        else if (map[y-1][x-1].equals("s")) {
            return false;
        }
        else if (map[y+1][x+1].equals("s")) {
            return false;
        }
        else if (map[y-1][x+1].equals("s")) {
            return false;
        }
        return true;
    }
    public int[] checkDanger4(int x, int y){
        return  checkAlentour4(x,y,"s");
    }

    public int[] checkAlentourAliment(int x, int y) {
        return checkAlentour(x,y,"b");
    }
    public int[] checkRefuge4(int x, int y) {
        return checkAlentour(x,y,"P");
    }

    @Override
    public int[] checkAlentourAnimalDomestique(int x, int y) {
        return checkAlentour(x,y,"S");
    }
}
