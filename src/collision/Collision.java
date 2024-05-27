package collision;
import entity.Entity;
import utils.DirEnum;

public interface Collision{

    /**
     * Vérifier si on touche un "obstacle"
     * @param entity entité à vérifier
     * @return oui ou non
     */
    public boolean doesCollide(Entity entity, DirEnum signal);
}