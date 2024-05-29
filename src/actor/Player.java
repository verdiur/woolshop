package actor;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import entity.Actor;
import entity.Entity;
import entity.Tile;
import main.GamePanel;
import util.DirEnum;

/**
 * Joueur.
 */
public class Player
extends Actor 
{
    PlayerKeyAdapter m_ka;
    int m_money;
    

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
        m_money = 0;
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
    public void update(ArrayList<Actor> actor_arr, ArrayList<Tile> tile_arr, ArrayList<Entity> collision_arr) {
        DirEnum dir = m_ka.getDir();
        if (canMove(collision_arr, dir)) {
            move(dir);
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    public KeyListener getKeyAdapter() {
        return m_ka;
    }

    public void addMoney(int money){
        m_money+=money;
    }

    public void setMoney(int money){
        m_money=money;
    }

    public int getMoney(){
        return m_money;
    }

}
