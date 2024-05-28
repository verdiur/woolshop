package actor;

import java.util.HashMap;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;
import java.util.Random;


import entity.Actor;
import main.GamePanel;
import tile.TileManager;
import util.DirEnum;
// import state_machine.StateMachine;
import state_machine.*;
import actor.state.*;
// import state_machine.State;


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
        Random rdm = new Random();
        m_xdest = rdm.nextInt(10);
        m_ydest = rdm.nextInt(10);
        Map<String, ClientState> map = Map.of("rdm", new RandomClientState(0,6), "path", new PathClientState(m_x, m_y, m_xdest, m_ydest));
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
        g2.drawImage(
            m_sprite_map.get("idle").get(0), 
            m_x * m_gp.TILE_SIZE, 
            m_y * m_gp.TILE_SIZE, 
            null
        );
    }

    public void update(){
        m_state.Update();
        move(m_state.getState().getDir());
        m_state.getState().setPose(m_x, m_y);
    }

    // public static int[][] FindNeighbors(){
    //     Client client = new Client();
    //     if (canMove(client, DirEnum.up)){ neighbors[0][0] = m_x; neighbors[0][1] = m_y + 1; }
    // }



    // public static List<Point> FindNeighbors(int[][] map, Point point) {
    //     List<Point> neighbors = new ArrayList<>();
    //     Point up = point.offset(0,  1);
    //     Point down = point.offset(0,  -1);
    //     Point left = point.offset(-1, 0);
    //     Point right = point.offset(1, 0);
    //     if (IsWalkable(map, up)) neighbors.add(up);
    //     if (IsWalkable(map, down)) neighbors.add(down);
    //     if (IsWalkable(map, left)) neighbors.add(left);
    //     if (IsWalkable(map, right)) neighbors.add(right);
    //     return neighbors;
    // }



    /**fonction search avec type generique  */

}

