package modele;


import java.util.ArrayList;
import java.util.List;


public abstract class Carte {

    public Personnage personnage;

    // Liste pour animaux domestiques.
    List<AnimalDomestique> animauxDomestique = new ArrayList<>();

    int tourAnimalRassasierMax;

    // Liste pour animaux sauvages.
    List<AnimalSauvage> animauxSauvages = new ArrayList<>();

    // Le refuge selon le theme.
    String aliment;

    // La matrice de la map vide.
    String[][] map = new String[35][100];

    // Les Coordonnées du joueur
    int xJoueur = 0;
    int yJoueur = 0;

    // Les dimensions de la carte.
    int width = 100;
    int height = 35;


    public int getxJoueur() {
        return this.xJoueur;
    }

    public int getyJoueur() {
        return this.yJoueur;
    }

    public int getWidth() { return width; }

    public int getHeight() { return height; }

    public String getValue(int y, int x) {
        return map[y][x];
    }

    public void setMap(String[][] map) {
        this.map = map;
    }

    // Liste contenant les éléments à enlever plus tard.
    List<int[]> listRemove = new ArrayList<>();

    public Carte() {
    }

    public List<AnimalDomestique> getAnimauxDomestiques() {
        return animauxDomestique;
    }

    public List<AnimalSauvage> getAnimauxSauvages() {
        return animauxSauvages;
    }



    /**
     * Fonction pour générer une carte aléatoirement.
     */
     public abstract void genereCarte();

    /**
     * Fonction
     * @param s
     * @return
     */
     public int[] getPosition(String s) {
         for (int y = 1; y < height-1; y++  ) {
             for (int x = 1; x < width-1; x++) {
                 if (map[y][x].equals(s)) {
                     return new int[]{x,y};
                 }
             }
         }
         return new int[]{0,0};
     }


    /**
     * Fonction qui permet d'utiliser la carte déjà fournie.
     */
    public abstract void utiliserCarte();


    /**
     * Fonction qui retourne la nouvelle position du joueur si on peut se déplacer
     * sinon il renvoie la même position dans un tableau d'int.
     * @param row où il se trouve sur l'axe des x.
     * @param col où il se trouve sur l'axe des y.
     * @param direction
     * @return un tableau de int.
     */
    public int[] movePlayer(int row, int col, int direction) {

        // On vérifie si on peut se déplacer vers le haut.
        if (direction == 2) {
            if ( (row > 0 && map[row-1][col].equals(" ")) ||
                    (row > 0 && map[row-1][col].equals(aliment)) ||
                    (row > 0 && map[row-1][col].equals("C")) ||
                    (row > 0 && map[row-1][col].equals("c")) ) {
                row -= 1;
            }
        }

        // On vérifie si on peut se déplacer vers le bas.
        else if (direction == 4) {
            if ( (row < map.length-1 && map[row+1][col].equals(" ")) ||
                    (row < map.length-1 &&  map[row+1][col].equals(aliment)) ||
                    (row < map.length-1 && map[row+1][col].equals("C")) ||
                    (row < map.length-1 && map[row+1][col].equals("c"))) {
                row += 1;
            }
        }

        // On vérifie si on peut se déplacer vers la gauche.
        else if (direction == 3) {
            if ( (col > 0 && map[row][col-1].equals(" ")) ||
                    (col > 0 && map[row][col-1].equals(aliment)) ||
                    (col > 0 && map[row][col-1].equals("C")) ||
                    (col > 0 && map[row][col-1].equals("c"))) {
                col -= 1;
            }
        }

        // On vérifie si on peut se déplacer vers la droite.
        else if (direction == 1) {
            if ( (col < map[0].length-1 && map[row][col+1].equals(" ")) ||
                    (col < map[0].length-1 && map[row][col+1].equals(aliment)) ||
                    (col < map[0].length-1 && map[row][col+1].equals("C")) ||
                    (col < map[0].length-1 && map[row][col+1].equals("c")) ) {
                col += 1;
            }
        }

        int[] newPos = {row, col};
        return newPos;
    }


    /**
     * Fonction qui renvoie true si les valeurs ont changé, c'est-à-dire que le personnage
     * peut se déplacer selon son choix, sinon renvoie false.
     * @param x est la valeur où se trouve le personnage sur l'axe des x.
     * @param y est la valuer où se trouve le personnage sur l'axe des y.
     * @param direction est la direction choisie par le personnage.
     * @return un boolean.
     */
    public boolean moveplayerPossible(int x, int y, int direction) {
        if (x == movePlayer(x, y, direction)[0] && y == movePlayer(x, y, direction)[1]) {
            return false;
        }
        return true;
    }


    /**
     * Fonction qui permet le déplacement du personnage ou d'un animal.
     * On échange la position de 2 éléments.
     * @param x1 Premier élément.
     * @param y1 Premier élément.
     * @param x2 Deuxième élément.
     * @param y2 Deuxième élément.
     */
    public void deplacement(int x1, int y1, int x2, int y2) {
        String oldElement = map[x2][y2];
        map[x2][y2] = map[x1][y1];
        map[x1][y1] = oldElement;
    }


    /**
     * Fonction qui vérifie aux alentours de la position donnée si elle contient le caractère donné.
     * @param x Premier élément.
     * @param y Deuxième élément.
     * @param s L'élément qu'on cherche aux alentours.
     */
    public int[] checkAlentour(int x, int y, String s){
        if(posDansCarte(x+1,y+1) && posDansCarte(x-1,y-1)) {
            if (map[y + 1][x].equals(s)) {
                return new int[]{x, y + 1};
            }
            else if (map[y - 1][x].equals(s)) {
                return new int[]{x, y - 1};
            }
            else if (map[y][x + 1].equals(s)) {
                return new int[]{x + 1, y};
            }
            else if (map[y][x - 1].equals(s)) {
                return new int[]{x - 1, y};
            }
            else if (map[y + 1][x - 1].equals(s)) {
                return new int[]{x - 1, y + 1};
            }
            else if (map[y - 1][x - 1].equals(s)) {
                return new int[]{x - 1, y - 1};
            }
            else if (map[y + 1][x + 1].equals(s)) {
                return new int[]{x + 1, y + 1};
            }
            else if (map[y - 1][x + 1].equals(s)) {
                return new int[]{x + 1, y - 1};
            }
            else{
                return new int[]{0,0};
            }
        }
        return new int[]{0,0};
    }


    /**
     * Fonction qui retourne toutes les positions vides autour de la position x, y.
     * @param x Position.
     * @param y Position.
     * @return une liste de tableau d'int.
     */
    public List<int[]> listPosVide(int x, int y) {

        // Liste res qu'on retourne à la fin.
        List<int[]> res = new ArrayList<>();

        if(map[y+1][x].equals(" ")) {
            res.add (new int[]{x, y+1});
        }
        else if (map[y-1][x].equals(" ")) {
            res.add(new int[]{x, y-1});
        }
        else if (map[y][x+1].equals(" ")) {
            res.add(new int[]{ x+1 , y});
        }
        else if (map[y][x-1].equals(" ")) {
            res.add(new int[]{ x-1 , y});
        }
        else if (map[y+1][x-1].equals(" ")) {
            res.add(new int[]{ x-1, y+1});
        }
        else if (map[y-1][x-1].equals(" ")) {
            res.add(new int[]{x - 1, y - 1});
        }
        else if (map[y+1][x+1].equals(" ")) {
            res.add(new int[]{x + 1, y + 1});
        }
        else if (map[y-1][x+1].equals(" ")) {
            res.add(new int[]{x + 1, y - 1});
        }
        return res;
    }


    /**
     * Fonction qui vérifie si les indices qu'on utilise sont dans le périmètre de la map.
     * @param x Position.
     * @param y Position.
     * @return un boolean.
     */
    public boolean posDansCarte(int x, int y){
        return x>0 && x<width && y>0 && y<height;
    }


    /**
     * Fonction qui vérifie aux alentours de la position donnée si elle contient le caractère donné.
     * @param x Premier élément.
     * @param y Deuxième élément.
     * @param s L'élément qu'on cherche aux alentours.
     */
    public int[] checkAlentour4(int x, int y, String s){
        int i = 1;
        while( i != 5 && posDansCarte(x+i,y+i) && posDansCarte(x-i,y-i)){
            if(map[y+i][x].equals(s)) {
                return new int[]{x, y+i};
            }
            else if (map[y-i][x].equals(s)) {
                return new int[]{x, y-i};
            }
            else if (map[y][x+i].equals(s)) {
                return new int[]{ x+i , y};
            }
            else if (map[y][x-i].equals(s)) {
                return new int[]{ x-i , y};
            }
            else if (map[y+i][x-i].equals(s)) {
                return new int[]{ x-i, y+i};
            }
            else if (map[y-i][x-i].equals(s)) {
                return new int[]{x - i, y - i};
            }
            else if (map[y+i][x+i].equals(s)) {
                return new int[]{x + i, y + i};
            }
            else if (map[y-i][x+i].equals(s)) {
                return new int[]{x + i, y - i};
            }
            i++;
        }
        return new int[]{0,0};
    }


    /**
     * Fonction qui permet de prendre la place indiquée.
     * @param x1 Premier élément.
     * @param y1 Premier élément.
     * @param x2 Deuxième élément.
     * @param y2 Deuxième élément.
     */
    public void prendCaPlace(int x1, int y1, int x2, int y2){
        map[x2][y2] = map[x1][y1];
        map[x1][y1] = " ";
    }


    /**
     * Fonction qui fait disparaitre l'élément à la position x, y.
     * @param x
     * @param y
     */
    public void disparait(int x, int y){
        map[x][y] = " ";
        listRemove.add(new int[]{y,x});
    }

    public void disparait2(int x, int y){
        map[x][y] = " ";
    }

    /**
     * Fonction qui retourne la liste des animaux domestiques à enlever de la map.
     * @return une liste de tableau d'int.
     */
    public List<int[]> removeAnimauxDomestiques() {
        return listRemove;
    }


    /**
     * Fonction qui permet de vérifier si dans la liste il y a la position donnée.
     * @param x
     * @param y
     * @param libre
     * @return un boolean.
     */
    public boolean posValide(int x, int y, List<int[]> libre) {
        for (int i = 0; i < libre.size(); i++) {
            if (x == libre.get(i)[0] && y == libre.get(i)[1] ) {
                return true;
            }
        }

        return false;
    }


    /**
     * Fonction qui donne la position de la direction opposée.
     * @param x1 Colonne écureuil.
     * @param y1 Ligne écureuil.
     * @param x2 Colonne renard.
     * @param y2 Ligne renard.
     * @return un tableau d'int.
     */
    public int[] posOpposeeDanger(int x1, int y1, int x2, int y2) {

        List<int[]> libre = listPosVide(x1, y1);

        // Pos écureuil > Pos renard / écureuil à droite et écureuil en dessous du renard.
        if (x1 > x2 && y1 > y2){
            // On vérifie s'il peut se déplacer dans la direction opposée.
            if ( posValide(x1 + 1, y1 + 1, libre) == true ) {
                return new int[] {x1 + 1, y1 + 1};
            }
            else {
                return new int[]{x1, y1};
            }
        }

        // Pos écureuil > Pos renard / écureuil à droite et sur la même ligne.
        else if (x1 > x2 && y1 == y2) {
            // On vérifie s'il peut se déplacer dans la direction opposée.
            if ( posValide(x1 + 1, y1, libre) == true ) {
                return new int[] {x1 + 1, y1};
            }
            else {
                return new int[]{x1, y1};
            }
        }

        // Pos écureuil > Pos renard / écureuil à droite et écureuil au-dessus du renard.
        else if (x1 > x2 && y1 < y2) {

            // On vérifie s'il peut se déplacer dans la direction opposée.
            if ( posValide(x1 + 1, y1 - 1, libre) == true ) {
                return new int[] {x1 + 1, y1 - 1};
            }
            else {
                return new int[]{x1, y1};
            }
        }

        // Pos écureuil < Pos renard/ renard à droite et renard au-dessus de l'écureuil.
        else if (x1 < x2 && y1 > y2) {
            // On vérifie s'il peut se déplacer dans la direction opposée.
            if ( posValide(x1 - 1, y1 - 1, libre) == true ) {
                return new int[] {x1 - 1, y1 - 1};
            }
            else {
                return new int[]{x1, y1};
            }
        }

        // Pos écureuil < Pos renard / renard à droite et sur la même ligne.
        else if (x1 < x2 && y1 == y2) {
            // On vérifie s'il peut se déplacer dans la direction opposée.
            if ( posValide(x1 - 1, y1, libre) == true ) {
                return new int[] {x1 - 1, y1};
            }
            else {
                return new int[]{x1, y1};
            }
        }

        // Pos écureuil < Pos renard / renard à droite et renard en dessous de l'écureuil.
        else if (x1 < x2 && y1 < y2) {
            // On vérifie s'il peut se déplacer dans la direction opposée.
            if ( posValide(x1 - 1, y1 + 1, libre) == true ) {
                return new int[] {x1 - 1, y1 + 1};
            }
            else {
                return new int[]{x1, y1};
            }
        }

        // Pos écureuil = Pos renard / écureuil est en dessous du renard.
        else if (x1 == x2 && y1 > y2) {
            // On vérifie s'il peut se déplacer dans la direction opposée.
            if ( posValide(x1, y1 + 1, libre) == true ) {
                return new int[] {x1, y1 + 1};
            }
            else {
                return new int[]{x1, y1};
            }
        }

        // Pos écureuil = Pos renard / écureuil est au-dessus du renard.
        else {
            // On vérifie s'il peut se déplacer dans la direction opposée.
            if ( posValide(x1, y1 - 1, libre) == true ) {
                return new int[] {x1, y1 - 1};
            }
            else {
                return new int[]{x1, y1};
            }
        }
    }


    /**
     * Fonction qui prend en paramètre l'élément qu'on souhaite rechercher ses positions dans la map.
     * @param s
     * @return une liste contenant toutes les positions de l'élément.
     */
    public ArrayList<int[]> getElement(String s) {
        ArrayList<int[]> res = new ArrayList<>();
        for (int y = 1; y < height-1; y++  ) {
            for (int x = 1; x < width-1; x++) {
                if (map[y][x].equals(s)) {
                    res.add(new int[]{x, y});
                }
            }
        }
        return res;
    }

    //Renvoie true si le Personnage est entouré d'un renard.
    public abstract boolean mortPersonnage(int x, int y);


    //Renvoie la position du danger.
    public abstract int[] checkDanger4(int x, int y);

    public Personnage getPersonnage() {
        return personnage;
    }

    public abstract int[] checkAlentourAliment(int x, int y);

    public abstract int[] checkRefuge4(int x, int y) ;

    public String[][] placement(String objet, int choixPlacement, int x, int y) {
        // Haut.
        if(choixPlacement == 1){
            map[y-1][x] = objet;
        }

        // Haut Droite.
        else if(choixPlacement == 2){
            map[y-1][x+1] = objet;
        }

        // Droite.
        else if (choixPlacement == 3){
            map[y][x+1] = objet;
        }

        // Bas Droite.
        else if (choixPlacement == 4){
            map[y+1][x+1] = objet;
        }

        // Bas.
        else if (choixPlacement == 5){
            map[y+1][x] = objet;
        }

        // Bas gauche.
        else if (choixPlacement == 6){
            map[y+1][x-1] = objet;
        }

        // Gauche.
        else if (choixPlacement == 7){
            map[y][x-1] = objet;
        }

        // Haut Gauche.
        else if (choixPlacement == 8){
            map[y-1][x-1] = objet;
        }

        return map;
    }

    public abstract int[] checkAlentourAnimalDomestique(int xJoueur, int yJoueur);
}
