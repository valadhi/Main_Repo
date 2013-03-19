import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;


public class reqPart extends SimpleBehaviour {
	
	Agent agent = new Agent();
	boolean turnoff = false;
	
	public reqPart(Agent a){
		agent = a;
	}
	public void turnoff(){
		turnoff = true;
	}
	
	public void action() {
		
		ACLMessage reqpart = new ACLMessage(ACLMessage.INFORM);
		reqpart.addReceiver(new AID("a3", AID.ISLOCALNAME));
		reqpart.setContent("1");
		agent.send(reqpart);
	}

	public boolean done() {
		return turnoff;
	}

}
