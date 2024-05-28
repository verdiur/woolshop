public abstract class State {

    protected String m_name;
    protected String m_transition_name;
    protected boolean m_transition;

    public void Enter(){}
    public void Exit(){}
    public void Update(){}

    public boolean isTransition() {
        return m_transition;
    }
    public String get_name(){ return m_name; }

    public String get_transition(){
        m_transition = false;
        return m_transition_name;
    }

}
