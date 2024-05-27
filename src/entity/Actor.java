package entity;

import collision.Collision;
import main.GamePanel;
import utils.DirEnum;

public abstract class Actor extends Entity implements Collision
{
    protected GamePanel m_gp;

    public Actor(GamePanel a_gp, int x, int y) {
        super(x, y);
        m_gp = a_gp;
    }

    // @Override
    public boolean doesCollide(Entity entity, DirEnum signal) {
        if ((signal == DirEnum.up && isBelow(entity))
        || (signal == DirEnum.down && isAbove(entity))
        || (signal == DirEnum.left && isAtRight(entity))
        || (signal == DirEnum.right && isAtLeft(entity))) {
            return true;
        }
        return false;
    }

    public boolean isNext(Entity entity) {
        if (isAtLeft(entity) || 
        isAtRight(entity) ||
        isAbove(entity) ||
        isBelow(entity)) {
            return true;
        }
        return false;
    }

    public boolean isAtLeft(Entity entity) {
        return entity.getX() == this.getX()-1;
    }

    public boolean isAtRight(Entity entity) {
        return entity.getX() == this.getX()+1;
    }

    public boolean isAbove(Entity entity) {
        return entity.getY() == this.getY()+1;
    }

    public boolean isBelow(Entity entity) {
        return entity.getY() == this.getY()-1;
    }
    
    public void move(DirEnum signal) {
        switch(signal) {
            case up:
                if (getY() < m_gp.TILE_SIZE) {
                    m_y += 1;
				}
            case down:
                if (getY() > 0) {
					m_y -= 1;
				}
            case left:
                if (getX() < m_gp.TILE_SIZE) {
					m_x += 1;
				}
            case right:
                if (getY()>0) {
					m_x -= 1;
                }
        }
    }
}