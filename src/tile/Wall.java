package tile;

import main.GamePanel;
import java.awt.image.BufferedImage;

public class Wall extends GameObject {

    public Wall(GamePanel a_gp, int x, int y) {
        super(a_gp, x, y);
    }

    public Wall(GamePanel a_gp, int x, int y, BufferedImage sprite) {
        super(a_gp, x, y, sprite);
    }

    @Override
    public void use() {
        
    }
}
