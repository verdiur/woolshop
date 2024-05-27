package collision;

import entity.Entity;
import util.DirEnum;

public interface Collision{

    /**
     * Vérifier si on touche un "obstacle" dans une direction
     * @param entity entité à vérifier
     * @param dir direction
     * @return oui ou non
     */
    public boolean isColliding(Entity entity, DirEnum dir);

    /**
     * Détermine si this est à côté d'un Entity donné en paramètre.
     * @param entity
     * @return oui ou non
     */
    public boolean isNext(Entity entity);

    /**
     * Détermine si this est au-dessus (up), au-dessous (down), à gauche (left) ou à droite (right)
     * d'un Entity donné en paramètre.
     * @param entity entity à évaluer
     * @param dir position de this par rapport à entity.
     * @return oui ou non
     */
    public boolean isAt(Entity entity, DirEnum dir);
}