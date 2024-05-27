package entity;

import collision.Collision;
import main.GamePanel;

public abstract class Actor extends Entity implements Collision {

    GamePanel m_gp;

    public Actor(GamePanel a_gp, float x, float y) {
        super(x, y);
        m_gp = a_gp;
    }

    @Override
    public boolean doesCollide(Entity entity) {
        if (entity.getX() == this.getX()-1 || 
        entity.getX() == this.getX()+1 ||
        entity.getY() == this.getY()-1 ||
        entity.getY() == this.getY()+1) {
            return true;
        }
        return false;
    }
    
    public void move(String signal) {
        switch(signal) {
            case "up" : {
                if (getY()<m_gp.TILE_SIZE) {
					setY(getY()+1);
				}
            }
            case "down" : {
                if (getY()>0) {
					setY(getY()-1);
				}
            }
            case "left" : {
                if (getX()<m_gp.TILE_SIZE) {
					setY(getY()+1);
				}
            }
            case "right" : {
                if (getY()>0) {
					setY(getY()-1);
				}
            }
        }
    }
}
