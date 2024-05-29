package actor;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.Random;


import entity.Actor;
import entity.Entity;
import entity.Tile;
import main.GamePanel;
import state.*;
// import tile.TileManager;
// import state_machine.StateMachine;
import state_machine.*;


/**
* NPC1 -> client du magasin 
*/
public class Client extends Actor 
{

    private int m_xdest;
    private int m_ydest;

    private ClientStateMachine m_state;

    /**
     * Constructeur de classe avec un seul sprite. Le sprite sera chargé dans un ArrayList
     * avec comme clé "idle".
     * @param a_gp reference GamePanel
     * @param x position x en tiles
     * @param y position y en tiles
     * @param sprite unique sprite
     */
    public Client(GamePanel a_gp, int x, int y, BufferedImage sprite) {
        super(a_gp, x, y, sprite);
        super.m_x = x;
        super.m_y = y;
        Random rdm = new Random();
        int fst = rdm.nextInt(6,14);
        if(fst > 12){
            int scd = rdm.nextInt(13,14);
            m_ydest = 2;
            m_xdest = scd;
        } else {
            int scd = rdm.nextInt(1,4);
            switch(scd){
                case 1:
                    m_ydest = 2;
                    break;
                case 2:
                    m_ydest = 5;
                    break;
                case 3:
                    m_ydest = 7;
                    break;
                case 4:
                    m_ydest = 9;
                    break;
            }
            m_xdest = fst;
        }
        // Map<Integer, Integer[]> full_map = Map.of(                                                                                                
        //                                             );
        // int m_k = rdm.nextInt(1,2);
        // System.out.println(m_k);
        // Integer[] coor = full_map.get(m_k);
        // m_xdest = coor[0];
        // m_ydest = coor[1];
        // System.out.println(super.m_x);
        // System.out.println(super.m_y);
        Map<String, ClientState> map = Map.of("rdm", new RandomClientState(super.m_x,super.m_y,this), "path", new PathClientState(super.m_x, super.m_y, m_xdest, m_ydest, this));
        m_state = new ClientStateMachine("rdm", map);
    }

    /**
     * Constructeur de classe avec un Hashmap d'ArrayList de Sprites.
     * @param a_gp reference GamePanel
     * @param x position x en tiles
     * @param y position y en tiles
     * @param sprite unique sprite
     */
    public Client(GamePanel a_gp, int x, int y, HashMap<String, ArrayList<BufferedImage>> sprite_map){
        super(a_gp, x, y, sprite_map);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
    }

    public void update(ArrayList<Actor> actor_arr, ArrayList<Tile> tile_arr, ArrayList<Entity> collision_arr){
        m_state.Update(collision_arr);
        super.m_x = m_state.getState().getX();
        super.m_y = m_state.getState().getY();
        /*System.out.print("m_x = ");
        System.out.print(m_x);
        System.out.print("; m_y = ");
        System.out.println(m_y);*/
        m_state.getState().setPose(super.m_x, super.m_y);
    }
}

