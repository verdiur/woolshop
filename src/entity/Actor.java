package entity;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import collision.Collision;
import main.GamePanel;
import util.DirEnum;

/**
 * Entité avec mouvement et collision.
 */
public abstract class Actor extends Entity implements Collision
{
    protected HashMap<String, ArrayList<BufferedImage>> m_sprite_map;
    protected GamePanel m_gp;
    protected boolean m_can_move;
    protected boolean has_moved = false;

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

    public boolean canMove(ArrayList<Entity> collision_arr, DirEnum dir) {
        for (Entity e: collision_arr) {
            if (isAt(e, dir)) { return false; }
        }
        return true;

    }

    public void setX(int x) {
        m_x = x;
    }

    public void setY(int y) {
        m_y = y;
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
                return entity.getY() == m_y - 1 && entity.getX() == m_x;
            case down:
                return entity.getY() == m_y + 1 && entity.getX() == m_x;
            case left:
                return entity.getX() == m_x - 1 && entity.getY() == m_y;
            case right:
                return (entity.getX() == m_x + 1) && entity.getY() == m_y;
            default:
                return true;
        }
    }

    public void setMoved(boolean moved) {
        has_moved = moved;
    }
    
    /**
     * Se déplacer dans une direction. Pour l'instant move() fait se téléporter
     * d'une case.
     * @param dir
     */
    public void move(DirEnum dir) {
        switch(dir) {
            case up:
                if (!has_moved) {
                    m_y -= 1;
                    has_moved = true;
                }
                break;
            case down:
                if (!has_moved) {
                    m_y += 1;
                    has_moved = true;
                }
                break;
            case left:
                if (!has_moved) {
                    m_x -= 1;
                    has_moved = true;
                }
                break;
            case right:
                if (!has_moved) {
                    m_x += 1;
                    has_moved = true;
                }
                break;
            case no:
                break;
            default:
                break;
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(
            m_sprite_map.get("idle").get(0), 
            m_x * m_gp.TILE_SIZE, 
            m_y * m_gp.TILE_SIZE,
            m_gp.TILE_SIZE,
            m_gp.TILE_SIZE,
            null
        );
    }

    /**
     * Méthode d'update
     */
    @Override
    public abstract void update(ArrayList<Actor> actor_arr, ArrayList<Tile> tile_arr, ArrayList<Entity> collision_arr);
}