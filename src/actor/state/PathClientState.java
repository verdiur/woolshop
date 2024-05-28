package actor.state;

import state_machine.State;
import util.DirEnum;

public class PathClientState extends ClientState{

    private int m_x;
    private int m_y;
    private int m_xdest;
    private int m_ydest;
    
    public PathClientState(int x, int y, int x_dest, int y_dest){
        m_x = x;
        m_y = y;
        m_xdest = x_dest;
        m_ydest = y_dest;
        super.m_name = "path";
        super.m_transition = false;        
    }

    // Definie l'ensemble des executions à l'entrée de l'etat
    // A définir dans la classe fille
    /**
     * Methode qui execute les actions correspondant à l'entrée dans l'etat
     */
    public void Enter(){

    }
    // Definie l'ensemble des executions à la sortie de l'état
    // A définir dans la classe fille
    /**
     * Methode qui execute les actions correspondant à la sortie de l'etat
     */
    public void Exit(){}
    // Definie l'ensemble des exécutions à chaque Update de l'état
    // A définir dans la classe fille
    /**
     * Methode qui execute les actions correspondant à l'update de l'etat
     */
    public void Update(){
        if (m_x == m_xdest && m_y == m_ydest){
            super.m_transition = true;
            super.m_transition_name = "rdm";
        } else {
            if (m_x < m_xdest){
                m_x ++;
            } else if (m_x > m_xdest){
                m_x --;
            } else if (m_y < m_ydest){
                m_y ++;
            } else if(m_y > m_ydest){
                m_y --;
            }
        }
    }

    // public DirEnum getDir(){
    //     return m_dir;
    // }

    public void setPose(int x, int y){
        m_x = x;
        m_y = y;
    }

    public int getX(){
        return m_x;
    }

    public int getY(){
        return m_y;
    }
}
