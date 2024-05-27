package collision;
import entity.Entity;

public interface Collision{

    //vérifie si un objet touche un "obstacle"
    public boolean doesCollide(Entity entity);
}