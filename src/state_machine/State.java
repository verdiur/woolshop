public abstract class State {
    /*

    Classe dont le rôle est de gerer le fonctionnement d'un etat à l'aide de
    3 méthodes principales.

    Enter() : actions et changements de paramètres de l'entité à l'entrée dans l'état
    Exit() : actions et changements de paramètres de l'entité à la sortie de l'état
    Update() : actions et changements de paramètres de l'entité à chaque Update

     */

    // Le nom de l'état qui sera appelé par la StateMachine
    // A définir dans le constructeur de la classe fille
    protected String m_name;
    // Le nom de l'état vers lequel on effectue une transition,
    // il est modifié lors de l'appel de la transition par l'update
    protected String m_transition_name;
    // Variable permettant à la StateMachine de savoir s'il y a une transition
    // A définir dans la classe fille
    protected boolean m_transition;

    // Definie l'ensemble des executions à l'entrée de l'etat
    // A définir dans la classe fille
    /**
     * Methode qui execute les actions correspondant à l'entrée dans l'etat
     */
    public void Enter(){}
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

    // Getter permettant de vérifier si on exécute une transition
    /**
     * Getter de la transition
     */
    public boolean isTransition() {
        return m_transition;
    }
    // Getter permettant de récupérer le nom de la classe
    /**
     * Getter du nom
     */
    public String get_name(){ return m_name; }

    // Methode permettant d'obtenir le nom de la classe vers laquelle on transition
    // On stop la transition avec "m_transition = false;"
    /**
     * Methode qui retourne le nom de l'etat vers lequel on effectue la transition
     * et met le boolean de la transition a false.
     */
    public String get_transition(){
        m_transition = false;
        return m_transition_name;
    }

}
