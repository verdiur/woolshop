package entity;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import collision.Collision;
import main.GamePanel;
import tile.GameObject;
import util.DirEnum;

/**
 * Entité avec mouvement et collision.
 */
public abstract class Actor extends Entity implements Collision
{
    protected HashMap<String, ArrayList<BufferedImage>> m_sprite_map;
    protected GamePanel m_gp;
    protected boolean m_can_move;

    /**
     * Constructeur de classe. Nécessite de charger le sprite_map séparément.
     * @param a_gp reference GamePanel
     * @param x position x en tiles
     * @param y position y en tiles
     */
    public Actor(GamePanel a_gp, int x, int y) {
        super(x, y);
        m_isPhysical = true;
        m_gp = a_gp;
        m_sprite_map = new HashMap<String, ArrayList<BufferedImage>>();
    }

    /**
     * Constructeur de classe avec un seul sprite. Le sprite sera chargé dans un ArrayList
     * avec comme clé "idle".
     * @param a_gp reference GamePanel
     * @param x position x en tiles
     * @param y position y en tiles
     * @param sprite unique sprite
     */
    public Actor(GamePanel a_gp, int x, int y, BufferedImage sprite) {
        super(x, y);
        m_gp = a_gp;
        m_sprite_map = new HashMap<String, ArrayList<BufferedImage>>();
        m_sprite_map.put("idle", new ArrayList<BufferedImage>());
        m_sprite_map.get("idle").add(sprite);
    }

    /**
     * Constructeur de classe avec un Hashmap d'ArrayList de Sprites.
     * @param a_gp reference GamePanel
     * @param x position x en tiles
     * @param y position y en tiles
     * @param sprite unique sprite
     */
    public Actor(GamePanel a_gp, int x, int y, HashMap<String, ArrayList<BufferedImage>> sprite_map) {
        super(x, y);
        m_gp = a_gp;
        m_sprite_map = sprite_map;
    }

    public boolean  canMove(Entity ent, DirEnum dir) {
        return (
            (dir == DirEnum.up && isAt(ent, DirEnum.down)) ||
            (dir == DirEnum.down && isAt(ent, DirEnum.up)) ||
            (dir == DirEnum.left && isAt(ent, DirEnum.right)) ||
            (dir == DirEnum.right && isAt(ent, DirEnum.left))
        );
    }
    
    @Override
    public boolean isNext(Entity entity) {
        return (
            isAt(entity, DirEnum.left) || 
            isAt(entity, DirEnum.right) ||
            isAt(entity, DirEnum.up) ||
            isAt(entity, DirEnum.right)
        );
    }

    @Override
    public boolean isColliding(Entity entity) {
        return (m_x == entity.getX() && m_y == entity.getY());
    }

    @Override
    public boolean isAt(Entity entity, DirEnum dir) {
        switch (dir) {
            case up:
                return entity.getY() == m_y - 1;
            case down:
                return entity.getY() == m_y + 1;
            case left:
                return entity.getX() == m_x - 1;
            case right:
                return entity.getX() == m_x + 1;
            default:
                return false;
        }
    }
    
    /**
     * Se déplacer dans une direction. Pour l'instant move() fait se téléporter
     * d'une case.
     * TODO voir plus tard pour un mouvement plus fluide avec interpolation.
     * @param dir
     */
    public void move(DirEnum dir) {
        switch(dir) {
            case up:
                m_y -= 1;
            case down:
                m_y += 1;
            case left:
                m_x += 1;
            case right:
                m_x -= 1;
            case no:
        }
    }
}