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

    @Override
    public boolean isColliding(Entity entity, DirEnum dir) {
        return (
            (dir == DirEnum.up && isAt(entity, DirEnum.down)) ||
            (dir == DirEnum.down && isAt(entity, DirEnum.up)) ||
            (dir == DirEnum.left && isAt(entity, DirEnum.right)) ||
            (dir == DirEnum.right && isAt(entity, DirEnum.left))
        );
    }
    
    /**
     * Détermine si this est à côté d'un Entity donné en paramètre.
     * @param entity
     * @return oui ou non
     */
    public boolean isNext(Entity entity) {
        return (
            isAt(entity, DirEnum.left) || 
            isAt(entity, DirEnum.right) ||
            isAt(entity, DirEnum.up) ||
            isAt(entity, DirEnum.right)
        );
    }

    /**
     * Détermine si this est au-dessus (up), au-dessous (down), à gauche (left) ou à droite (right)
     * d'un Entity donné en paramètre.
     * @param entity entity à évaluer
     * @param dir position de this par rapport à entity.
     * @return oui ou non
     */
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
    
    public void move(DirEnum dir) {
        // switch(dir) {
        //     case up:
        //         if (m_y < m_gp.TILE_SIZE) {
        //             m_y += 1;
		// 		}
        //     case down:
        //         if (m_y > 0) {
		// 			m_y -= 1;
		// 		}
        //     case left:
        //         if (m_x < m_gp.TILE_SIZE) {
		// 			m_x += 1;
		// 		}
        //     case right:
        //         if (m_x > 0) {
		// 			m_x -= 1;
        //         }
        // }
        switch(dir) {
            case up:
                m_y -= 1;
            case down:
                m_y += 1;
            case left:
                m_x += 1;
            case right:
                m_x -= 1;
        }
    }
}