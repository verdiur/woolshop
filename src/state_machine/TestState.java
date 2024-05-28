package state_machine;

public class TestState extends State {

    public TestState(){
        super.m_name = "test";
        super.m_transition = false;
    }

    @Override
    public void Enter() {
        System.out.println(super.m_name);
        System.out.println("entered");
    }

    @Override
    public void Exit() {
        System.out.println("exited");
    }

    @Override
    public void Update() {
        System.out.println("update");
        super.m_transition = true;
        super.m_transition_name = "other";
    }


}
