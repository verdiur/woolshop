import java.util.Map;

public class StateMachine {

    private Map<String,State> m_states;
    private State m_current_state;

    public StateMachine(String initial_state, Map<String,State> states ){
        m_states = states;
        ready(m_states.get(initial_state));
    }

    public void ready(State state){
        m_current_state = state;
        m_current_state.Enter();
    }

    public State getState(){
        return m_current_state;
    }

    public void Update(){

        m_current_state.Update();
        if (m_current_state.isTransition()){
            child_transition(m_current_state.get_name(), m_current_state.get_transition());
        }
    };

    public void child_transition(String state, String new_state){
        if (m_current_state != m_states.get(state) ||  state == new_state ) { // A rajouter verifier que new_state est bien compris dans l'enum
            System.out.println("StateMachine error");
            return;
        }
        else
        {
            m_current_state.Exit();

            m_current_state = m_states.get(new_state);

            m_current_state.Enter();
        }


    }
}

