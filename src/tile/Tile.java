package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import entity.Entity;

public class Tile extends Entity
{
    private BufferedImage m_sprite;

    /**
     * Constructeur de classe.
     * @param x
     * @param y
     * @param sprite
     */
    public Tile(int x, int y, BufferedImage sprite) {
        super(x, y);
        m_sprite = sprite;
    }

    public BufferedImage getSprite() {
        return m_sprite;
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(m_sprite, m_x, m_y, null);
    }
}
