package modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class UtiliserCarte extends Carte{

    public UtiliserCarte() {
        super();
    }

    public void genereCarte() {}


    /**
     * Fonction qui permet d'utiliser la carte déjà fournie.
     */
    public void utiliserCarte() {

        // Il faut ajouter le chemin d'accès du fichier pour chaque ordinateur.
        String fileName = "/Users/abedelzubaidi/Desktop/university/L3/coo/Jeux_v19/Jeux/src/modele/carte.txt";


        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null) {
                // Ajouter tous les caractères de la ligne avec les espaces également.
                for (int col = 0; col < line.length(); col++) {
                    map[row][col] = String.valueOf(line.charAt(col));

                    // On met à jour les coordonnées du joueur.
                    if(map[row][col].equals("@")){
                        xJoueur = row;
                        yJoueur = col;
                    }

                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean mortPersonnage(int x, int y) {
        if(map[y+1][x].equals("R")) {
            return false;
        }
        else if (map[y-1][x].equals("R")) {
            return false;
        }
        else if (map[y][x+1].equals("R")) {
            return false;
        }
        else if (map[y][x-1].equals("R")) {
            return false;
        }
        else if (map[y+1][x-1].equals("R")) {
            return false;
        }
        else if (map[y-1][x-1].equals("R")) {
            return false;
        }
        else if (map[y+1][x+1].equals("R")) {
            return false;
        }
        else if (map[y-1][x+1].equals("R")) {
            return false;
        }
        return true;
    }

    @Override
    public int[] checkDanger4(int x, int y) {
        return  checkAlentour4(x,y,"R");
    }

    @Override
    public int[] checkAlentourAliment(int x, int y) {
        return checkAlentour(x,y,"G");
    }

    @Override
    public int[] checkRefuge4(int x, int y) {
        return checkAlentour(x,y,"A");
    }

    @Override
    public int[] checkAlentourAnimalDomestique(int xJoueur, int yJoueur) {

        return new int[0];
    }
}
