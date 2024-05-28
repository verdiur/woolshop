package actor;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import entity.Actor;
import entity.Entity;
import main.GamePanel;
import util.DirEnum;

/**
 * Joueur.
 */
public class Player
extends Actor 
{
    PlayerKeyAdapter m_ka;

    /**
     * Constructeur de classe avec un seul sprite. Le sprite sera chargé dans un ArrayList
     * avec comme clé "idle".
     * @param a_gp reference GamePanel
     * @param x position x en tiles
     * @param y position y en tiles
     * @param sprite unique sprite
     */
    public Player(GamePanel a_gp, int x, int y, BufferedImage sprite) {
        super(a_gp, x, y, sprite);
        m_ka = new PlayerKeyAdapter(this);
    }

    /**
     * Constructeur de classe avec un Hashmap d'ArrayList de Sprites.
     * @param a_gp reference GamePanel
     * @param x position x en tiles
     * @param y position y en tiles
     * @param sprite unique sprite
     */
    public Player(GamePanel a_gp, int x, int y, HashMap<String, ArrayList<BufferedImage>> sprite_map) {
        super(a_gp, x, y, sprite_map);
        m_ka = new PlayerKeyAdapter(this);
    }

    @Override
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

    @Override
    public void update() {
        DirEnum dir = m_ka.getDir();
        move(dir);
    }

    @Override
    public boolean isColliding(Entity entity) {
        // TODO Auto-generated method stub
        return false;
    }
}
