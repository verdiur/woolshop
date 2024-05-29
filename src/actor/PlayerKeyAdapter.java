package actor;

import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import util.DirEnum;
import util.UseEnum;

import java.awt.event.KeyEvent;

/**
 * Observeur d'input attaché à un Player. 
 * ATTENTION: toujours appeler les méthodes de PlayerKeyAdapter depuis un Player.
 * https://stackoverflow.com/questions/31100724/java-classes-reference-each-other
 * Par pitié que ca fonctionne
 */
public class PlayerKeyAdapter implements KeyListener {
    private Player m_player;
    private DirEnum m_dir;
    private UseEnum m_use;
    private final Set<Integer> pressedKeys = new HashSet<Integer>();


    PlayerKeyAdapter(Player player) {
        m_player = player;
        m_dir = DirEnum.no;
        m_use = UseEnum.no_use;
    }

    Player getPlayer() {
        return m_player;
    }

    DirEnum getDir() {
        return m_dir;
    }

    UseEnum getUse() {
        return m_use;
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (pressedKeys.remove(keyCode)) {
            switch(keyCode) {
                case 0x25:      // left 
                    m_dir = DirEnum.no;
                    break;
                case 0x27:      // right
                    m_dir = DirEnum.no;
                    break;
                case 0x26:      // up
                    m_dir = DirEnum.no;
                    break;
                case 0x28:
                    m_dir = DirEnum.no;
                    break;
                case 32:
                    m_use = UseEnum.no_use;
                    break;
            }
        }
        // System.out.println(pressedKeys);
        // System.out.println(m_dir);
        // System.out.println(m_use);
        m_player.setMoved(false);
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
        if (!pressedKeys.isEmpty()) {
            for (Iterator<Integer> it = pressedKeys.iterator(); it.hasNext();) {
                switch (it.next()) {
                    case 32:
                        m_use = UseEnum.use;
                        // System.out.println("use");
                        break;
                    case 0x25:      // left 
                        m_dir = DirEnum.left;
                        // System.out.println("left");
                        break;
                    case 0x27:      // right
                        m_dir = DirEnum.right;
                        // System.out.println("right");
                        break;
                    case 0x26:      // up
                        m_dir = DirEnum.up;
                        // System.out.println("up");
                        break;
                    case 0x28:
                        m_dir = DirEnum.down;
                        // System.out.println("down");
                        break;
                }
            }

        }
        
        // System.out.println(pressedKeys);
        // System.out.println(m_dir);
        // System.out.println(m_use);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

}
