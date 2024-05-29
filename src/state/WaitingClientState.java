package state;

import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import actor.Client;
import entity.Entity;
import util.DirEnum;

import actor.Player;
import actor.*;

public class WaitingClientState extends ClientState {

    private Timer m_timer;
    private TimerTask mt_update;
    private Client m_client;
    private int m_x;
    private int m_y;
    

    public WaitingClientState( Client client){

        super.m_name = "waiting";
        super.m_transition = false;   

        m_client = client;

        m_timer = new Timer();


    }

    public void Enter(){

        mt_update = new TimerTask() {
			public void run(){
                divide();
			}
		};
		m_timer.scheduleAtFixedRate(mt_update, new Date(), 10000);

    }

    public void Exit(){
        m_timer.purge();
    }

    public void Update(ArrayList<Entity> collision_arr){
        for (Entity element : collision_arr){
            if (element instanceof Player){
                System.out.println(element);
                if (element.getX() == 1 && element.getY() == 1){
                    System.out.println((((Player) element).getKeyAdapter()).getkey());
                }
            } 
            
        }
        // Connection avec le joueur
        // check si la position du joueur est bien la position du contoir
        // Si oui ecoute le joueur

        // Si le joueur demande un contacte (action e)
        // donner l'argent
        // Appel de la transition
    }

    public void divide(){
        m_client.setMoney(m_client.getMoney()/2);
        System.out.println(m_client.getMoney());
        if (m_client.getMoney() <= 15){
            transition();
        }
    }

    public void transition(){
        super.m_transition = true;
        super.m_transition_name = "exit";
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
