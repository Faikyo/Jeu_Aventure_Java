package modele;

public abstract class EtatAnimal {

    public AnimalDomestique pet;

    public EtatAnimal(AnimalDomestique pet){this.pet=pet;}

    public abstract Carte seDeplace(Carte c);

    public abstract String toString();

}
