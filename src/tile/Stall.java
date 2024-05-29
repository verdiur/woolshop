package tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import actor.Player;
import entity.Entity;
import main.GamePanel;

public class Stall extends GameObject {
    private int m_wool = 0;
    private int m_max_wool = 6;

    public Stall(GamePanel a_gp, int x, int y, BufferedImage sprite) {
        super(a_gp, x, y, sprite);
    }

    public Stall(GamePanel a_gp, int x, int y) {
        super(a_gp, x, y);
    }



    @Override
    public void use() {
        ArrayList<Entity> collisions = m_gp.getCollisions();
        for (Entity element : collisions){
            if (element instanceof Player){
                if (m_wool > 0 && !((Player)element).getWool()) {
                    ((Player)element).setWool(true);
                    System.out.println(((Player)element).getWool());
                    m_wool -= 1;
                    break;
                }
                else if (((Player)element).getWool() && m_wool <= m_max_wool) {
                    ((Player)element).setWool(false);
                    System.out.println(((Player)element).getWool());
                    m_wool += 1;
                    break;
                }
            }
        }
        System.out.println(m_wool);
    }
}
