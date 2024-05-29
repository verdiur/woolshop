package tile;

import java.awt.image.BufferedImage;

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
        for (Entity element : m_gp.getCollisions()){
            if (element instanceof Player){
                if (m_wool > 0 && !((Player)element).getWool()) {
                    ((Player)element).setWool(true);
                    System.out.println(((Player)element).getWool());
                    m_wool -= 1;
                }
                else if (((Player)element).getWool() && m_wool <= m_max_wool) {
                    ((Player)element).setWool(false);
                    System.out.println(((Player)element).getWool());
                    m_wool += 1;
                }
            }
        }
    }
}
