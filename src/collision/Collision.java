package collision;
import entity.Entity;
import utils.DirEnum;

public interface Collision{

    /**
     * Vérifier si on touche un "obstacle" dans une direction
     * @param entity entité à vérifier
     * @param dir direction
     * @return oui ou non
     */
    public boolean isColliding(Entity entity, DirEnum dir);
}