package actor;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import entity.Actor;
import main.GamePanel;
import util.DirEnum;

/**
 * Joueur.
 */
public class Player
extends Actor 
implements KeyListener 
{
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
    }

    @Override
    public void draw(Graphics2D g2) {
        g2.drawImage(
            m_sprite_map.get("idle").get(0), 
            m_x * m_gp.TILE_SIZE, 
            m_y * m_gp.TILE_SIZE, 
            null
        );
    }

    /// TODO ajouter des animations et tout
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case 0x25:      // left
                move(DirEnum.left);
            case 0x27:      // right
                move(DirEnum.right);
            case 0x26:      // up
                move(DirEnum.up);
            case 0x28:
                move(DirEnum.down);
            /// TODO ajouter ici des contrôles supplémentaires...
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}
