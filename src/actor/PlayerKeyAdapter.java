package actor;

import java.awt.event.KeyListener;

import util.DirEnum;

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
    private int current_key;

    PlayerKeyAdapter(Player player) {
        m_player = player;
        m_dir = DirEnum.no;
    }

    Player getPlayer() {
        return m_player;
    }

    DirEnum getDir() {
        return m_dir;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        m_player.setMoved(false);
        m_dir = DirEnum.no;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        current_key = code;
        switch (code) {
            case 0x25:   {}   // left
                m_dir = DirEnum.left;
                break;
            case 0x27:      // right
                m_dir = DirEnum.right;
                break;
            case 0x26:      // up
                m_dir = DirEnum.up;
                break;
            case 0x28:
                m_dir = DirEnum.down;
                break;
            /// TODO ajouter ici des contrôles supplémentaires...
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public int getkey(KeyEvent e){
        return e.getKeyCode();
    }

    public int getKey(){return current_key;}
}
