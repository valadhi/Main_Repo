import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;


public class Voice_Msg extends SimpleBehaviour {

	Agent agent = new Agent();
	boolean active = true;
	
	public Voice_Msg(Agent a){
		agent = a;
	}
	public void turnoff(){
		active = false;
	}
	
	public void action() {
		
		ACLMessage incomingreq = agent.receive();
		if(incomingreq != null){
			System.out.println("MESAJ PRIMIT DE LA AGENT PRIM: "+incomingreq.getContent());
		}

	}
	
	public boolean done(){
		if(active)
			return false;
		else return true;
	}

}
