package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import entity.Entity;

public class Tile extends Entity
{
    private BufferedImage m_sprite;

    /**
     * Constructeur de classe.
     * @param x position x en tiles
     * @param y position y en tiles
     * @param sprite sprite
     */
    public Tile(int x, int y, BufferedImage sprite) {
        super(x, y);
        m_sprite = sprite;
    }

    /**
     * Constructeur de classe. Nécessite de charger le sprite séparément.
     * @param x position x en tiles
     * @param y position y en tiles
     */
    public Tile(int x, int y) {
        super(x, y);
        m_sprite = null;
    }

    public BufferedImage getSprite() {
        return m_sprite;
    }

    public void setSprite(BufferedImage sprite) {
        m_sprite = sprite;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(m_sprite, m_x, m_y, null);
    }
}
