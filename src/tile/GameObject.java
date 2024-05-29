package tile;

import entity.Entity;
import entity.Tile;
import main.GamePanel;
import util.DirEnum;

import java.awt.image.BufferedImage;

import collision.Collision;

public abstract class GameObject extends Tile implements Collision {

    public GameObject(GamePanel a_gp, int x, int y) {
        super(a_gp, x, y);
        m_isPhysical = true;
        //les attributs de Tile sont accessibles ici
    }

    public GameObject(GamePanel a_gp, int x, int y, BufferedImage sprite) {
        super(a_gp, x, y, sprite);
    }

    @Override
    public boolean isColliding(Entity entity) {
        return (m_x == entity.getX() && m_y == entity.getY());
    }

    @Override
    public boolean isAt(Entity entity, DirEnum dir) {
        switch (dir) {
            case up:
                return entity.getY() == m_y - 1;
            case down:
                return entity.getY() == m_y + 1;
            case left:
                return entity.getX() == m_x - 1;
            case right:
                return entity.getX() == m_x + 1;
            default:
                return false;
        }
    }

    @Override
    public boolean isNext(Entity entity) {
        return (
            isAt(entity, DirEnum.left) || 
            isAt(entity, DirEnum.right) ||
            isAt(entity, DirEnum.up) ||
            isAt(entity, DirEnum.right)
        );
    }
}
