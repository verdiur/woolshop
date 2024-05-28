public class OtherState extends State {
    public OtherState() {
        super.m_name = "other";
        super.m_transition = false;
    }

    public void Enter() {
        System.out.println(super.m_name);
        System.out.println("entered");
    }

    public void Exit() {
        System.out.println("exited");
    }

    public void Update() {
        System.out.println("update");
        super.m_transition = true;
        super.m_transition_name = "other";
    }
}