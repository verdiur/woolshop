package actor.state;

import state_machine.State;
import java.util.Random;
import entity.Actor;
import util.DirEnum;

public class RandomClientState extends ClientState{

    private int m_x;
    private int m_y;
    private int m_time;
    private DirEnum m_dir;
    public static final int MAX_TEMP = 10; 

    public RandomClientState(int x, int y){
        m_x = x;
        m_y = y;
        super.m_name = "rdm";
        super.m_transition = false; 
    }

        // Definie l'ensemble des executions à l'entrée de l'etat
    // A définir dans la classe fille
    /**
     * Methode qui execute les actions correspondant à l'entrée dans l'etat
     */
    public void Enter(){
        Random rdm = new Random();
        m_time = rdm.nextInt(MAX_TEMP);
    }
    // Definie l'ensemble des executions à la sortie de l'état
    // A définir dans la classe fille
    /**
     * Methode qui execute les actions correspondant à la sortie de l'etat
     */
    public void Exit(){
        return;
    }
    // Definie l'ensemble des exécutions à chaque Update de l'état
    // A définir dans la classe fille
    /**
     * Methode qui execute les actions correspondant à l'update de l'etat
     */
    public void Update(){
        if (m_time != 0){
            Random rdm = new Random();
            switch(rdm.nextInt(4)){
                case 0:
                    m_dir = DirEnum.up;
                case 1:
                    m_dir = DirEnum.down;
                case 2:
                    m_dir = DirEnum.left;
                case 3:
                    m_dir = DirEnum.right;
            }
            m_time --; 
        } else {
            super.m_transition = true;
            super.m_transition_name = "path";
        }
    }

    public void setPose(int x, int y){
        m_x = x;
        m_y = y;
    }

    public DirEnum getDir(){
        return m_dir;
    }
}
