package vue;

import java.util.List;
import java.util.Scanner;

public class Ihm {

    // On initialise un scanner pour le jeu.
    Scanner sc = new Scanner(System.in);

    // Strings for foreground colors.
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_Brown = "\u001B[39m";

    // Strings for background colors
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m" ;
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_WHITE_BACKGROUND = "\033[47m";
    public static final String ANSI_ORANGE_BACKGROUND = "\u001B[49m";
    public static final String ANSI_BROWN_BACKGROUND = "\u001B[43m";


    /**
     * Fonction pour imprimer la valeur de type String.
     */
    public void valeur(String v) {
        System.out.print(v);
    }


    /**
     * Fonction pour faire un retour à la ligne.
     */
    public void ligne() {
        System.out.println();
    }



    // Pour la Forêt.
    /**
     * Fonction pour la couleur de l'écureuil rassasier.
     */
    public void couleurEcureuilRassasier(String s) {
        System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLUE + s +ANSI_RESET);
    }


    /**
     * Fonction pour la couleur de l'écureuil affamer.
     */
    public void couleurEcureuilAffamer(String s) {
        System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_BLACK + s +ANSI_RESET);
    }


    /**
     * Fonction pour la couleur de l'écureuil ami.
     */
    public void couleurEcureuilAmi(String s) {
        System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_PURPLE + s +ANSI_RESET);
    }


    /**
     * Fonction pour la couleur de l'écureuil junkie.
     */
    public void couleurEcureuilJunkie(String s) {
        System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_RED + s +ANSI_RESET);
    }


    /**
     * Fonction pour la couleur de l'arbre.
     */
    public void couleurArbre(String s) {
        System.out.print(ANSI_BLACK_BACKGROUND + ANSI_GREEN + s +ANSI_RESET);
    }


    /**
     * Fonction pour la couleur du Gland.
     */
    public void couleurGland(String s) {
        System.out.print(ANSI_BROWN_BACKGROUND + ANSI_BLACK + s +ANSI_RESET);
    }


    /**
     * Fonction pour la couleur du renard.
     */
    public void couleurRenard(String s) {
        System.out.print(ANSI_ORANGE_BACKGROUND + ANSI_BLACK + s +ANSI_RESET);
    }



    // Pour la Jungle.
    /**
     * Fonction pour la couleur du cocotier.
     */
    public void couleurCocotier(String s) { System.out.print(ANSI_BLACK_BACKGROUND + ANSI_GREEN + s +ANSI_RESET); }


    /**
     * Fonction pour la couleur de la banane.
     */
    public void couleurBanane(String s) { System.out.print(ANSI_BROWN_BACKGROUND + ANSI_BLACK + s + ANSI_RESET); }


    /**
     * Fonction pour la couleur du serpent.
     */
    public void couleurSerpent(String s) { System.out.print(ANSI_ORANGE_BACKGROUND + ANSI_BLACK + s +ANSI_RESET); }


    /**
     * Fonction pour la couleur du singe.
     */
    public void couleurSinge(String s) { System.out.print(ANSI_YELLOW_BACKGROUND + ANSI_PURPLE + s +ANSI_RESET); }



    // En commun.
    /**
     * Fonction pour la couleur du buisson.
     */
    public void couleurBuisson(String s) {
        System.out.print(ANSI_BLACK_BACKGROUND + ANSI_GREEN + s +ANSI_RESET);
    }


    /**
     * Fonction pour la couleur du Champignon.
     */
    public void couleurChampignon(String s) {
        System.out.print(ANSI_WHITE_BACKGROUND + ANSI_Brown + s +ANSI_RESET);
    }


    /**
     * Fonction pour la couleur du Champignon Venimeux.
     */
    public void couleurChampignonVenimeux(String s) {
        System.out.print(ANSI_RED_BACKGROUND + ANSI_YELLOW + s +ANSI_RESET);
    }


    /**
     * Fonction pour la couleur du personnage.
     */
    public void couleurPersonnage(String s) {
        System.out.print(ANSI_WHITE_BACKGROUND + ANSI_PURPLE + s +ANSI_RESET);
    }


    /**
     * Fonction pour la couleur de la zone vide.
     */
    public void couleurZoneVide(String s) { System.out.print(ANSI_GREEN_BACKGROUND + s +ANSI_RESET); }


    /**
     * Fonction qui permet au joueur de choisir s'il veut générer ou utiliser une carte.
     * @return un int qui le choix qu'il a fait pour la carte.
     */
    public int questionCartes(){
        int reponse = 0;
        while (reponse != 1 && reponse != 2) {
            System.out.println("Voulez-vous générer une carte ou utiliser une carte ?");
            System.out.println("1. Générer une carte");
            System.out.println("2. utiliser une carte");

            if (sc.hasNextInt()) {
                reponse = sc.nextInt();
            } else {
                sc.next();
            }
        }
        return reponse;
    }

    /**
     * Fonction qui permet au joueur de choisir s'il veut une carte de type Forêt ou de type jungle.
     * @return un int qui le choix qu'il a fait pour la carte.
     */
    public int themeCartes(){
        int reponse = 0;
        while (reponse != 1 && reponse != 2) {
            System.out.println("Voulez-vous générer une carte de type Forêt ou de type jungle ?");
            System.out.println("1. Forêt");
            System.out.println("2. Jungle");

            if (sc.hasNextInt()) {
                reponse = sc.nextInt();
            } else {
                sc.next();
            }
        }
        return reponse;
    }


    /**
     * Fonction qui permet de savoir comment le joueur veut se déplacer dans la carte
     * ou déposer un objet ou Frapper un animal :.
     * @return un int qui est le mouvement souhaité du joueur.
     */
    public int getCoup() {
        int coup = -1;
        while (coup < 0 || coup > 6) {
            System.out.println("\nVoici les commandes pour vous déplacer, déposer un animal ou Frapper un animal :\n" +
                    "1. Droite\n"  + "2. Haut\n" +
                    "3. Gauche\n" + "4. Bas\n" +
                    "5. Déposer\n" + "6. Frapper");
            if (sc.hasNextInt()) {
                coup = sc.nextInt();
            } else {
                sc.next();
            }
        }
        return coup;
    }


    /**
     * Fonction qui affiche le choix du joueur.
     * @param choix
     */
    public void mvChoix(String choix) {
        System.out.println("Vous avez choisit : " + choix + ".");
    }


      /**
     * Fonction qui affiche la direction choisit par le joueur.
     * @param mvJoueur
     */
    public void afficherDirectionJoueur(int mvJoueur) {

        // Nom des directions.
        String droite = "Droite";
        String haut = "Haut";
        String gauche = "Gauche";
        String bas = "Bas";

        switch (mvJoueur) {

            // Le personnage a choisi d'aller à droite.
            case 1:
                mvChoix(droite);
                break;

            // Le personnage a choisi d'aller en haut.
            case 2:
                mvChoix(haut);
                break;

            // Le personnage a choisi d'aller à gauche.
            case 3:
                mvChoix(gauche);
                break;

            // Le personnage a choisi d'aller en bas.
            case 4:
                mvChoix(bas);
                break;

                // Le personnage a choisi de déposer un objet.
            case 5:
                System.out.println("Vous avez choisi de déposer un objet.");
                break;

            // Le personnage a choisi de frapper un animal.
            default:
                System.out.println("Vous avez choisi de frapper un animal.");
                break;
        }
    }


    /**
     * Fonction qui affiche que le joueur ne peut pas choisir cette direction.
     */
    public void pasDeDeplacement() {
        System.out.println("\nVous ne pouvez pas vous déplacer dans cette direction.\nVeuillez choisir une autre direction.");
    }


    /**
     * Fonction qui demande au joueur s'il veut quitter ou continuer.
     * @return la saisie du joueur.
     */
    public String askJoueur() {
        String reponse = "";
        System.out.println("Appuyez sur q pour quitter ou n'importe quel touche pour continuer.");
        reponse = sc.next();

        return reponse;
    }


    /**
     * Fonction qui affiche que le personnage est mort.
     */
    public void mort() {
        System.out.println("\nVous êtes mort par un animal sauvage.");
    }


    /**
     * Fonction qui affiche que le personnage a quitté le jeu.
     */
    public void quit() {
        System.out.println("\nVous avez quitté le jeu.");
    }


    /**
     * Fonction qui affiche le contenue du sac du personnage.
     * @param sac
     */
    public void afficheSac(List<String> sac) {
        String res = "\nVoici votre sac : [";
        for (int i = 0; i < sac.size(); i++) {
            if (i == sac.size() - 1) {
                res = res + sac.get(i);
            }
            else {
                res = res + sac.get(i) + ", ";
            }

        }
        System.out.println(res +"]");
    }


    /**
     * Foncyion qui recupere ce que le joueur veut deposer comme objet.
     * @return
     */
    public int deposeObjet(int indiceMax) {
        int coup = -1;
        while (coup < 0 || coup > indiceMax) {
            System.out.println("\nQuel objet voulez-vous deposer ?\n" +
                    "Veuillez donner l'indice de l'objet que vous voulez depose : ");
            if (sc.hasNextInt()) {
                coup = sc.nextInt();
            } else {
                sc.next();
            }
        }
        return coup;
    }

    public int choixPlacement() {
        int coup = -1;
        while (coup < 0 || coup > 8) {
            System.out.println("\nOù voulez-vous deposer l'objet?\n" +
                    "1. Haut\n"  + "2. Haut Droite\n" +
                    "3. Droite\n" + "4. Bas Droite\n" +
                    "5. Bas\n"  + "6. Bas Gauche\n" +
                    "7. Gauche\n"  + "8. Haut Gauche\n" );
            if (sc.hasNextInt()) {
                coup = sc.nextInt();
            } else {
                sc.next();
            }
        }
        return coup;
    }


    /**
     * Fonction qui affiche qu'il n'y a rien dans le sac.
     */
    public void rienDansLeSac() {
        System.out.println("\nIl n'y a rien dans le sac.");
    }
}
