import controleur.Controleur;
import vue.Ihm;

public class Cataclysm {
    public static void main(String[] args) {
        Ihm ihm = new Ihm();
        Controleur c = new Controleur(ihm);

        // On demande au joueur de choisir s'il veut générer une carte ou en utiliser une déjà prête
        // pour lancer le jeu selon son choix.
        if (ihm.questionCartes() == 1) {
            c.play(1);
        }
        else {
            c.play(2);
        }


    }
}