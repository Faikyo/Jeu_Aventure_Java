package controleur;

import modele.*;
import vue.Ihm;

import java.util.*;

public class Controleur {
    Ihm ihm;

    // La carte qu'on va controler.
    Carte c1;

    // Dans cette liste qu'on récupère toutes les positions de tous les animauxDomestique dans la map.
    List<AnimalDomestique> animauxDomestiques = new ArrayList<>();

    // La liste des positions des animaux domestiques à enlever.
    List<int[]> enlever = new ArrayList<>();

    // Dans cette liste, on récupère toutes les positions de renard dans la map.
    List<AnimalSauvage> animauxSauvages = new ArrayList<>();

    // La map animauxEtEtat contient la position de l'animal en cle et un animal en valeur.
    Map<List<int[]>, String> animauxEtEtat2 = new HashMap<List<int[]>, String>();

    // Le personnage.
    Personnage p;

    int xJoueur;

    int yJoueur;

    int round;


    /**
     * Constructeur du Controleur.
     * @param ihm
     */
    public Controleur(Ihm ihm) {
        this.ihm = ihm;
    }


    /**
     * Fonction qui retourne le choix du Joueur concernant la carte.
     * @param choix
     * @return
     */
    public int choixCarte(int choix) {
        int res = 1;

        // On applique le choix du joueur et on affiche la carte.
        if (choix == 1) {

            // Le joueur a choisi le thème de la forêt.
            if (ihm.themeCartes() == 1) {
                res = 1;
            }
            // Le joueur a choisi le thème de la Jungle.
            else {
                res = 2;
            }
        }
        // Le joueur a choisi d'utiliser une carte.
        else {
            res = 3;
        }
        return res;
    }


    /**
     * Fonction play lance le jeu.
     * @param choix
     */
    public void play(int choix) {

        // On récupère le choix du joueur pour la carte.
        int choixCarteJoueur = choixCarte(choix);

        // Le joueur a choisi le thème de la forêt.
       if ( choixCarteJoueur == 1) {
           c1 = new Foret();
           c1.genereCarte();
           afficheCarte();
       }

       // Le joueur a choisi le thème de la Jungle.
       else if ( choixCarteJoueur == 2) {
           c1 = new Jungle();
           c1.genereCarte();
           afficheCarte();
       }

       // Le joueur a choisi d'utiliser une carte.
       else {
           c1 = new UtiliserCarte();
           c1.utiliserCarte();
           afficheCarte();

       }

        // On remplit les animaux domestiques.
        animauxDomestiques = c1.getAnimauxDomestiques();

        // On remplit la liste des animaux sauvages.
        animauxSauvages = c1.getAnimauxSauvages();

        // Les positions du joueur.
        xJoueur = c1.getxJoueur();
        yJoueur = c1.getyJoueur();

        // On demande au joueur s'il veut quitter.
        String reponse = ihm.askJoueur();

        // On stocke le choix de la direction choisit par le joueur.
        int mvJoueur;

        // On remplit cette liste avec les écureuils qu'il faut enlever de la liste écureuils.
        List<AnimalDomestique> removeListAnimauxDomestiques = new ArrayList<>();


        // S'assurer que le joueur choisit un déplacement correct et s'il n'est pas mort.
        while (!reponse.equals("q") && c1.mortPersonnage(yJoueur,xJoueur)) {
            // On est passes au premier tour.
            round = 1;

            // On récupère le personnage qui a ete cree dans la carte.
            p = c1.getPersonnage();
            ihm.afficheSac(p.getSac());


            // Le choix du joueur pour se déplacer sur la carte.
            mvJoueur = ihm.getCoup();


            // On affiche la direction qu'il a choisie.
            ihm.afficherDirectionJoueur(mvJoueur);

            // On vérifie d'abord si le personnage veut deposer un objet ou frapper un animal ou peut se déplacer.
            if (mvJoueur == 5 || mvJoueur == 6 || c1.moveplayerPossible(xJoueur, yJoueur, mvJoueur) == true ) {

                int[] posSuivant = c1.movePlayer(xJoueur, yJoueur, mvJoueur);

                if (c1.getValue(posSuivant[0],posSuivant[1]).equals(" ")) {

                    // Le personnage se déplace selon son choix.
                    c1.movePlayer(xJoueur, yJoueur, mvJoueur);

                    // Déplacement vers la droite.
                    if (mvJoueur == 1) {
                        c1.deplacement(xJoueur, yJoueur, xJoueur, yJoueur + 1);

                        // On met à jour la position du joueur.
                        yJoueur++;
                    }

                    // Déplacement vers le haut.
                    else if (mvJoueur == 2) {
                        c1.deplacement(xJoueur, yJoueur, xJoueur - 1, yJoueur);

                        // On met à jour la position du joueur.
                        xJoueur--;
                    }

                    // Déplacement vers la gauche.
                    else if (mvJoueur == 3) {
                        c1.deplacement(xJoueur, yJoueur, xJoueur, yJoueur - 1);

                        // On met à jour la position du joueur.
                        yJoueur--;
                    }
                    // Déplacement vers le bas.
                    else if (mvJoueur == 4) {
                        c1.deplacement(xJoueur, yJoueur, xJoueur + 1, yJoueur);

                        // On met à jour la position du joueur.
                        xJoueur++;
                    }
                }

                // Le joueur a choisi de déposer un objet ou de frapper un animal.
                else {

                    // On récupère l'emplacement de la position et le type de l'objet.
                    // Si c'est false on ne fait rien.
                    if (p.ramasse(c1.getValue(posSuivant[0], posSuivant[1]))) {
                        c1.prendCaPlace(xJoueur, yJoueur, posSuivant[0], posSuivant[1]);
                        miseAJoueur(mvJoueur);
                    }

                    // Dépose un objet du sac.
                    if (mvJoueur == 5) {

                        // Choix de l'objet que le personnage veut déposer.
                        int choixSac;

                        // Le contenu du sac du personnage.
                        List<String> sac = p.getSac();
                        System.out.println("sac size : " + sac.size());

                        // Si le sac n'est pas null.
                        if (sac.size() != 0) {

                            // On récupère l'indice de l'objet dans le sac que le personnage veut déposer.
                            choixSac = ihm.deposeObjet(sac.size());

                            // Le choix du joueur pour déposer l'objet autour de lui.
                            int choixPlacement = ihm.choixPlacement();

                            String objet = p.getSacO(choixSac);

                            c1.setMap(c1.placement(objet, choixPlacement, posSuivant[1], posSuivant[0]));
                            p.deposeSac(choixSac);
                        }

                        // Il n'y a rien dans le sac et le personnage rate son tour.
                        else {
                            ihm.rienDansLeSac();
                        }
                    }

                    // Frappe un animal Domestique.
                    else if (mvJoueur == 6) {
                        int[] posA = c1.checkAlentourAnimalDomestique( xJoueur ,yJoueur );
                        if(posA[1] != 0 && posA[0]!= 0){
                            AnimalDomestique a =getAnimalDomestique(posA);
                            if(a!=null){
                                if(a.toString() == "Ami"){
                                    ihm.valeur("Vous avez frappez votre animal de compagnie, il n'est plus votre ami\n");
                                    a.setEtatCourant(new Affamer(a));
                                    a.setTourEcureuil(0);
                                    afficheCarte();
                                }
                                else{
                                    ihm.valeur("Vous avez frappez un animal qui n'est pas votre ami, il devient junkie\n");
                                    a.setEtatCourant(new Junkie(a));
                                    a.setTourEcureuil(0);
                                    afficheCarte();
                                }
                            }
                        }
                       else{
                           ihm.valeur("Vous ne pouvez pas frappez\n");
                        }
                    }
                }


            } else {
                // Le joueur ne peut pas de déplacer dans la direction voulue donc on lui affiche.
                ihm.pasDeDeplacement();
            }


            // On remplit la liste avec les animaux domestiques supprimés.
            enlever = c1.removeAnimauxDomestiques();

            // On met à jour la liste animauxDomestique.
            for (AnimalDomestique i : animauxDomestiques) {
                int[] res = i.getPosition();
                for(int[] j : enlever) {
                    if ( j[0] == res[0] && j[1] == res[1] ) {
                        removeListAnimauxDomestiques.add(i);
                    }
                }
            }

            // On supprime les écureuils de la liste.
            int count = 0;
            while (count < removeListAnimauxDomestiques.size()) {
                animauxDomestiques.remove(removeListAnimauxDomestiques.get(count));
                count++;
            }

            // On appelle les fonctions qui font bouger les écureuils et les renards.
            tourAnimalDomestique();
            tourRenards();

            // On récupère tous les états des animaux domestique.
            remplirAnimauxEtat(choix);

            // On affiche la carte.
            afficheCarte();

            // On redemande au joueur.
            reponse = ihm.askJoueur();
        }
        if (c1.mortPersonnage(yJoueur,xJoueur) == false) {
            ihm.mort();
        }
        else {
            ihm.quit();
        }


    }


    /**
     * Fonction qui déplace tous les animaux domestiques.
     */
    public void tourAnimalDomestique() {
        for (AnimalDomestique a : animauxDomestiques) {
            c1 = a.seDeplace(c1);
        }
    }


    /**
     * Fonction qui déplace tous les animaux sauvages.
     */
    public void tourRenards(){
        for (AnimalSauvage s : animauxSauvages){
            c1 = s.seDeplace(c1);
        }
    }


    /**
     * Fonction qui remplie une map de type List<int[]>, String des etats des animaux.
     * @param choix
     */
    public void remplirAnimauxEtat(int choix) {

        // La map animauxEtEtat contient la position de l'animal en cle et un animal en valeur.
        Map<List<int[]>, String> animauxEtEtat = new HashMap<List<int[]>, String>();

        // Liste contenant tous les animaux domestiques.
        List<int[]> listeAnimalDomestiques = new ArrayList<>();

        // Animal domestique.
        AnimalDomestique a;

        // Si le choix est 1 alors le joueur a choisi la forêt comme carte.
        if (choix == 1) {
            for (int y = 0; y < c1.getHeight(); y++) {
                for (int x = 0; x < c1.getWidth(); x++) {
                    if (c1.getValue(y, x).equals("E")) {
                        int[] res = {y,x};
                        listeAnimalDomestiques.add(res);
                    }
                }
            }

        }
        else {
            for (int y = 0; y < c1.getHeight(); y++) {
                for (int x = 0; x < c1.getWidth(); x++) {
                    if (c1.getValue(y, x).equals("S")) {
                        int[] res = {y,x};
                        listeAnimalDomestiques.add(res);
                    }
                }
            }
        }


        // On remplie animauxEtEtat avec chaque animal domestique et son état.
        for (int i = 0; i < listeAnimalDomestiques.size() && i < animauxDomestiques.size(); i++) {

            // On met à jour l'animal domestique.
            a = animauxDomestiques.get(i);
            String etat = a.getEtatCourant().toString();

            List<int[]> r = new ArrayList<>();
            r.add( new int[]{listeAnimalDomestiques.get(i)[0], listeAnimalDomestiques.get(i)[1] } );

            // Chaque animal domestique est assigné à sa couleur.
            animauxEtEtat.put(r, etat);
        }

        // On met à jour l'état des animaux.
        animauxEtEtat2 = animauxEtEtat;
    }

    /**
     * Fonction qui permet d'afficher la carte.
     */
    public void afficheCarte() {
        String value;
        for (int y = 0; y < c1.getHeight(); y++) {
            for (int x = 0; x < c1.getWidth(); x++) {
                value = c1.getValue(y,x);

                switch (value) {

                    // Pour la forêt.
                    case "A":
                        ihm.couleurArbre(value);
                        break;

                    case "E" :
                        afficherAnimauXDomestiques(value,y,x);

                        break;

                    case "R":
                        ihm.couleurRenard(value);
                        break;

                    case "G":
                        ihm.couleurGland(value);
                        break;


                    // Pour la Jungle.
                    case "S" :
                        afficherAnimauXDomestiques(value,y,x);
                        break;

                    case "P" :
                        ihm.couleurCocotier(value);
                        break;

                    case "b" :
                        ihm.couleurBanane(value);
                        break;

                    case "s" :
                        ihm.couleurSerpent(value);
                        break;


                    // En commun.
                    case "B":
                        ihm.couleurBuisson(value);
                        break;

                    case "C":
                        ihm.couleurChampignon(value);
                        break;

                    case "c":
                        ihm.couleurChampignonVenimeux(value);
                        break;

                    case "@":
                        ihm.couleurPersonnage(value);
                        break;

                    default:
                        ihm.couleurZoneVide(value);
                        break;

                }

            }

            ihm.ligne();
        }
    }


    /**
     * Fonction qui affiche les animaux domestique avec la bonne couleur de leur etat.
     * @param value
     * @param y
     * @param x
     */
    public void afficherAnimauXDomestiques(String value, int y, int x) {
        // Pour le premiere tour, l'état est toujours rassasié.
        if (round == 0) {
            ihm.couleurEcureuilRassasier(value);
        }

        else {

            // Pour animal domestique dans la Map animauxEtEtat2, on vérifie son état pour lui donner la couleur
            // qui correspond à son état.
            for (Map.Entry<List<int[]>, String> entry : animauxEtEtat2.entrySet()) {
                List<int[]> key = entry.getKey();
                String str = Arrays.toString(entry.getKey().get(0));
                String val = entry.getValue();

                // On enlève tous les espaces.
                str = str.replace(" ", "");

                // On récupère des string qui sont les positions de l'animal.
                String int1Str = str.substring(1, str.indexOf(','));
                String int2Str = str.substring(str.indexOf(',') + 1, str.length() - 1);

                // Transformer les String des valuers en int.
                int int1 = Integer.parseInt(int1Str);
                int int2 = Integer.parseInt(int2Str);

                // On vérifie les coordonnées de l'animal domestique et son état pour l'affichage.
                if (y == int1 && x == int2) {

                    if (Objects.equals(val, "Rassasier")) {
                        ihm.couleurEcureuilRassasier(value);
                    }
                    else if (Objects.equals(val,"Affamer")) {
                        ihm.couleurEcureuilAffamer(value);
                    }
                    else if (Objects.equals(val,"Ami")) {
                        ihm.couleurEcureuilAmi(value);
                    }
                    else {
                        ihm.couleurEcureuilJunkie(value);
                    }
                }
            }
        }
    }


    /**
     * Fonction qui met a jour la position du joueur.
     * @param mvJoueur
     */
    public void miseAJoueur(int mvJoueur) {
        // Déplacement vers la droite.
        if (mvJoueur == 1) {
            yJoueur++;
        }

        // Déplacement vers le haut.
        else if (mvJoueur == 2) {
            xJoueur--;
        }

        // Déplacement vers la gauche.
        else if (mvJoueur == 3) {
            yJoueur--;
        }
        // Déplacement vers le bas.
        else  {
            xJoueur++;
        }
    }
    /**
     * Fonction qui retourne l'animal domestique à la position indiqué
     * @param pos
     */
    public AnimalDomestique getAnimalDomestique(int[] pos) {
        AnimalDomestique res =null;
        for (AnimalDomestique a : animauxDomestiques) {
            if (a.getPosition() == pos) {
                res=a;
            }
        }
        return res;
    }

}



