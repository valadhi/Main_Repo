import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.core.behaviours.SimpleBehaviour;


public class Voice_Behaviour extends SequentialBehaviour {
	
	Agent agent = new Agent();
	public Voice_Behaviour(Agent a){
		super(a);
		
		SimpleBehaviour b1 = new Voice_Receive_Message(agent);
		SimpleBehaviour b2 = new Melody_Generation(agent);
		
		//b1.setDataStore(getDataStore());
		//b2.setDataStore(b1.getDataStore());
		addSubBehaviour(b1);
		addSubBehaviour(b2);
	
	}
}
