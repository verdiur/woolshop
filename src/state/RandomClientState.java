package state;

import java.util.ArrayList;
import java.util.Random;

import entity.Entity;
import util.DirEnum;
import actor.Client;
import entity.Actor;

public class RandomClientState extends ClientState {

    private int m_x;
    private int m_y;
    private int m_time;
    private DirEnum m_dir;
    public static final int MAX_TEMP = 10;
    private Client m_client;
    Random rdm = new Random();

    public RandomClientState(int x, int y, Client client){
        m_x = x;
        m_y = y;
        m_client = client;
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
        m_client.setMoney(100);
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
    @Override
    public void Update(ArrayList<Entity> collision_arr){
        if (m_time != 0){

            ArrayList<Integer[]> neighb = neighbors(collision_arr);
            int k = rdm.nextInt(0,neighb.size());
            // System.out.println(k);
            switch(k){
                case 0:
                    m_x = neighb.get(0)[0];
                    m_y = neighb.get(0)[1];
                    break;
                case 1:
                    m_x = neighb.get(1)[0];
                    m_y = neighb.get(1)[1];
                    break;
                case 2:
                    m_x = neighb.get(2)[0];
                    m_y = neighb.get(2)[1];
                    break;
                case 3:
                    m_x = neighb.get(0)[0];
                    m_y = neighb.get(0)[1];
                    break;
            }
            // System.out.print("m_x = ");
            // System.out.print(m_x);
            // System.out.print("; m_y = ");
            // System.out.println(m_y);
            m_time --; 
        } else {
            super.m_transition = true;
            // System.out.println("coucou je transition");
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

    public int getX(){
        return m_x;
    }

    public int getY(){
        return m_y;
    }

    public ArrayList<Integer[]> neighbors(ArrayList<Entity> collision_arr){ 
        ArrayList<Integer[]> neighb = new ArrayList<Integer[]>();
        if (m_client.canMove(collision_arr, DirEnum.up)) {
           Integer[] m_coor = {m_x, m_y-1};
            neighb.add(m_coor);
        } 
        if(m_client.canMove(collision_arr, DirEnum.down) && m_y < 11){
            Integer[] m_coor = {m_x, m_y+1};
            neighb.add(m_coor);
        } 
        if(m_client.canMove(collision_arr, DirEnum.right)){
            Integer[] m_coor = {m_x+1, m_y};
            neighb.add(m_coor);
        }
        if(m_client.canMove(collision_arr, DirEnum.left)){
            Integer[] m_coor = {m_x-1, m_y};
            neighb.add(m_coor);
        }
        return neighb;
    }

}
