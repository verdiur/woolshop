package actor.state;

import state_machine.State;
import util.DirEnum;

public abstract class ClientState extends State{

    private DirEnum m_dir;
    
    public DirEnum getDir(){
        return 0;
    }

    public void setPose(int x, int y){
    }

    public int getX(){
        return 0;
    }

    public int getY(){
        return 0;
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
    public void Update(){}
    
}
