import jade.core.Agent;


public class Composer extends Agent{
	
	Composer_Behaviour beh = new Composer_Behaviour(this);
	
	public void setup(){
		addBehaviour(beh);
	}

}
