package collision;

public interface Collision extends Comparable<Collision>{

    //vérifie si un objet touche un "obstacle"
    public boolean doesCollide();
}