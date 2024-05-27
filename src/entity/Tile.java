package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

/**
 * Entité sans mouvement
 */
public class Tile extends Entity
{
    protected GamePanel m_gp;
    protected BufferedImage m_sprite;

    /**
     * Constructeur de classe.
     * @param a_gp reference GamePanel
     * @param x position x en tiles
     * @param y position y en tiles
     * @param sprite sprite
     */
    public Tile(GamePanel a_gp, int x, int y, BufferedImage sprite) {
        super(x, y);
        m_gp = a_gp;
        m_sprite = sprite;
    }

    /**
     * Constructeur de classe. Nécessite de charger le sprite séparément.
     * @param a_gp reference GamePanel
     * @param x position x en tiles
     * @param y position y en tiles
     */
    public Tile(GamePanel a_gp, int x, int y) {
        super(x, y);
        m_gp = a_gp;
        m_sprite = null;
    }

    public BufferedImage getSprite() {
        return m_sprite;
    }

    public void setSprite(BufferedImage sprite) {
        m_sprite = sprite;
    }

    /**
     * Méthode de dessin.
     */
    @Override
     public void draw(Graphics2D g2) {
        g2.drawImage(
            m_sprite, 
            m_x * m_gp.TILE_SIZE, 
            m_y * m_gp.TILE_SIZE, 
            null
        );
    }
    
    @Override
    public void update() {};
}
