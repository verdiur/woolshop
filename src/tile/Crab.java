package tile;

import java.awt.image.BufferedImage;

import actor.Player;
import entity.Entity;
import main.GamePanel;

public class Crab extends GameObject {

    public Crab(GamePanel a_gp, int x, int y, BufferedImage sprite) {
        super(a_gp, x, y, sprite);
    }

    public Crab(GamePanel a_gp, int x, int y) {
        super(a_gp, x, y);
    }

    @Override
    public void use() {
        for (Entity element : m_gp.getCollisions()){
            if (element instanceof Player){
                if (!((Player)element).getWool()) {
                    ((Player)element).setWool(true);
                    System.out.println(((Player)element).getWool());
                    break;
                }
                else if (((Player)element).getWool()) {
                    ((Player)element).setWool(false);
                    System.out.println(((Player)element).getWool());
                    break;
                }
            }
        }
    }
    
}
