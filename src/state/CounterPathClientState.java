package state;

import java.util.ArrayList;

import entity.Entity;
import util.DirEnum;
import actor.Client;
import java.util.Random;

public class CounterPathClientState extends ClientState {

    private int m_x;
    private int m_y;
    private int m_xdest;
    private int m_ydest;
    private DirEnum m_dir = DirEnum.no;
    private Client m_client;
    Random rdm = new Random();
    
    public CounterPathClientState(int x, int y, Client client){
        m_x = x;
        m_y = y;
        m_xdest = 1;
        m_ydest = 3;
        super.m_name = "counter";
        super.m_transition = false;   
        m_client = client; 
        //System.out.println(m_xdest);
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
    public void Update(ArrayList<Entity> collision_arr){

        // System.out.println("a");
        if (m_x == m_xdest && m_y == m_ydest){
            super.m_transition = true;
            super.m_transition_name = "waiting";
            m_dir = DirEnum.no;
        } else {
            if (m_y < m_ydest && m_client.canMove(collision_arr, DirEnum.down)){
                m_y ++;

            } else if(m_y > m_ydest && m_client.canMove(collision_arr, DirEnum.up)){
                m_y --;

            } else if (m_x > m_xdest && m_client.canMove(collision_arr, DirEnum.left) && m_y == m_ydest){
                m_x --;

            } else if (m_x < m_xdest && m_client.canMove(collision_arr, DirEnum.right) && m_y == m_ydest){
                m_x ++;

            }else if (!m_client.canMove(collision_arr, DirEnum.up) && m_client.canMove(collision_arr, DirEnum.right)){
                m_x ++;

            } else if (m_x > m_xdest && m_client.canMove(collision_arr, DirEnum.left)){
                m_x --;

            } else if (m_x < m_xdest && m_client.canMove(collision_arr, DirEnum.right)){
                m_x ++;

            } else if(m_client.canMove(collision_arr, DirEnum.right)){
                m_x ++;

            } else if (m_client.canMove(collision_arr, DirEnum.left)){
                m_x --;

            } else if (m_client.canMove(collision_arr, DirEnum.down)){
                m_y ++;

            } else if(m_client.canMove(collision_arr, DirEnum.up)){
                m_y --;
            }
        }
        // System.out.println(m_dir);
    }

    public DirEnum getDir(){
        return m_dir;
    }

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
