package state_machine;

import java.util.ArrayList;
import java.util.Map;

import entity.Entity;
import state.*;

public class ClientStateMachine{

    /*

    Classe dont le rôle est de réaliser des transitions entre les etats des objets.

    ready() : permet d'initialiser le premier etat

    Update() : permet d'effectuer l'update de la classe actuelle et de verifier si il y a une transition

    child_transition() : permet de realiser la transition entre différents état en
        appelant Exit() sur le current
        changant le current par le new
        appelant Enter() sur le new

     */

    // Map comprenant l'ensemble des etat ainsi que les String permettant de les appeler
    private Map<String, ClientState> m_states;
    // State definissant l'etat actuel (celui sur lequel Update est appeler)
    private ClientState m_current_state;

    // Appelle le constructeur de la StateMachine en definissant un premier current_state et la map comprenant tout les etats
    // La Map est à définire dans le constructeur
    /**
     * Constructeur de classe avec un Hashmap de String et de State
     * @param initial_state reference le nom de l'etat initial : String
     * @param states reference la Hashmap : Map<String,State>
     */
    public ClientStateMachine(String initial_state, Map<String, ClientState> states ){
        m_states = states;
        ready(m_states.get(initial_state));
    }

    // Methode appelée par le constructeur pour initialiser l'etat courrant
    /**
     * Methode qui initialise l'etat courrant
     * @param state reference l'etat initiale : State
     */
    private void ready(ClientState state){
        m_current_state = state;
        m_current_state.Enter();
    }

    // Getter permettant de recuperer l'etat actuel afin de pouvoir recuperer les données
    // voulues par l'entite
    /**
     * Getter de l'etat actuel
     */
    public ClientState getState(){
        return m_current_state;
    }

    // Methode permettant d'appeler l'update de l'etat
    // Si l'etat effectue uen transition alors on appele :
    // child_transition( nom de l'etat actuel, nom de l'etat suivant)
    // Qui permet d'effectuer la transition
    /**
     * Methode qui Update l'etat actuel
     */
    public void Update(ArrayList<Entity> collision_arr){

        m_current_state.Update(collision_arr);
        if (m_current_state.isTransition()){
            child_transition(m_current_state.get_name(), m_current_state.get_transition());
        }
    };

    // Méthode permettant d'effectuer une transition à l'aide du nom de l'etat courrant
    // et du nom de l'etat vers lequel on transitionne
    // appelle Exit
    // realise la transition (changement de m_current_state)
    // appelle Enter
    /**
     * Methode qui réalise la transition d'etat
     * @param state reference le nom de l'etat actuel
     * @param new_state reference le nom du nouvel etat
     */
    public void child_transition(String state, String new_state){
        // verifie si on transitionne bien de l'etat actuel vers un nouvel etat
        if (m_current_state != m_states.get(state) || state == new_state ) { // A rajouter verifier que new_state est bien compris dans l'enum
            System.out.println("StateMachine error");
            return;
        }
        else
        {
            //System.out.println("transition");
            //System.out.println(new_state);
            m_current_state.Exit();

            int x = m_current_state.getX();
            int y = m_current_state.getY();

            m_current_state = m_states.get(new_state);

            m_current_state.setPose(x, y); 
            m_current_state.Enter();
        }


    }
}


