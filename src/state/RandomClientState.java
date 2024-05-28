package state;

import java.util.Random;
import util.DirEnum;

public class RandomClientState extends ClientState {

    private int m_x;
    private int m_y;
    private int m_time;
    private DirEnum m_dir;
    public static final int MAX_TEMP = 10;
    Random rdm = new Random();

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
            
            int k = rdm.nextInt(0,4);
            System.out.println(k);
            switch(k){
                case 0:
                    m_x ++;
                    break;
                case 1:
                    m_x --;
                    break;
                case 2:
                    m_y --;
                    break;
                case 3:
                    m_y ++;
                    break;
            }
            System.out.print("m_x = ");
            System.out.print(m_x);
            System.out.print("; m_y = ");
            System.out.println(m_y);
            m_time --; 
        } else {
            super.m_transition = true;
            System.out.println("coucou je transition");
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
}
